package gorunum;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jidesoft.swing.RangeSlider;

import CodeEditor.CodeEditorPanel;

import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import kontrol.AraclarPanelKontrol;
import kontrol.CalistirKontrol;
import kontrol.DynamicWindowApproach;
import kontrol.GridPanelKontrol;
import kontrol.PotentialFieldKontrol;
import pencereler.AlgoritmaOlusturPenceresi;


public class AraclarPanel extends JPanel {
	private AraclarPanel araclarPanel = null;
	private JButton calistirDugmesi;
	private JButton durdurDugmesi;
	private JButton temizleDugmesi;
	private JComboBox algoritmaSecComboBoks;
	private JToggleButton	algoritmaOlusturButonu;
	private JCheckBox	virtualForcecheckBoks;
	private JSlider rangeSlider;
	private JButton		noktaEkleDugmesi;
	private JButton		PRMBagla;

	private AlgoritmaOlusturPenceresi algoritmaOlusturPenceresi;

	public AraclarPanel() {
		araclarPanel = this;
		panelOlustur();

	}

	private void panelOlustur() {
		JLabel algoritmaSecEtiketi = new JLabel("Algoritma Seçiniz");
		araclarPanel.add(getPrmBaglaDugmesi());
		araclarPanel.add(getSlider());
		araclarPanel.add(getNoktaEkleDugmesi());
		araclarPanel.add(getVirtualForceCheckBoks());
		araclarPanel.add(algoritmaSecEtiketi);
		araclarPanel.add(getAlgoritmaSecComboBoks());
		araclarPanel.add(getCalistirDugmesi());
		araclarPanel.add(getDurdurDugmesi());
		araclarPanel.add(getTemizleDugmesi());
	//	araclarPanel.add(getBaslangicNoktasiBelirleButonu());
	//	araclarPanel.add(getBitisNoktasiBelirleButonu());
		araclarPanel.add(getAlgoritmaOlusturButonu());
	}

	public JButton getCalistirDugmesi() {
		if (calistirDugmesi == null) {
			calistirDugmesi = new JButton("Çalýþtýr");
			calistirDugmesi.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AraclarPanelKontrol.getInstance().calistirDugmesineTiklandi();
			
				}
			});
		}

		return calistirDugmesi;
	}

	public JButton getDurdurDugmesi() {
		if (durdurDugmesi == null) {
			durdurDugmesi = new JButton("Durdur");
			durdurDugmesi.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (getAlgoritmaSecComboBoks().getSelectedIndex() == 2) {
						KureselDegiskenler.setPotentialFieldBaslatidi(false);
						PotentialFieldKontrol.calismayiDurdur();
					}else if(getAlgoritmaSecComboBoks().getSelectedIndex() == 6){
						KureselDegiskenler.setDynamicWindowsApproachBaslatidi(false);
						DynamicWindowApproach.calismayiDurdur();
					}
					

				}
			});
		}

		return durdurDugmesi;
	}

	public JButton getTemizleDugmesi() {
		if (temizleDugmesi == null) {
			temizleDugmesi = new JButton("Temizle");
			temizleDugmesi.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AraclarPanelKontrol.getInstance().temizleDugmesisTiklandi();
				GlobalDegiskenler.GetGridPanel().getClosest().clear();
				}
			});
		}

		return temizleDugmesi;
	}

	public JComboBox getAlgoritmaSecComboBoks() {
		if (algoritmaSecComboBoks == null) {
			algoritmaSecComboBoks = new JComboBox();
			algoritmaSecComboBoks.addItem("A* Star");
			algoritmaSecComboBoks.addItem("Lee Algorithm");
			algoritmaSecComboBoks.addItem("Potential Field Algorithm");
			algoritmaSecComboBoks.addItem("Probabilistic Road Map");
			algoritmaSecComboBoks.addItem("Rapidly Expanding Random Tree");
			algoritmaSecComboBoks.addItem("Rapidly Expanding Random Tree Star");
			algoritmaSecComboBoks.addItem("Dynamic Window Approach");
			algoritmaSecComboBoks.addItem("Robotic Navigation Algorithm");
			algoritmaSecComboBoks.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						getPrmBaglaDugmesi().setVisible(false);
						switch ( algoritmaSecComboBoks
								.getSelectedIndex()) {
						case OrtakSabitler.ASTAR_INDEKS:
							AStarLeeAlgoritmaSecildi();
							break;
						case OrtakSabitler.LEE_INDEKS:
							AStarLeeAlgoritmaSecildi();
							break;
						case OrtakSabitler.POTENTIAL_FIELD_ALGORTHM_INDEKS:
							PotentialFieldDynamicWindowsSecildi();		
							break;
						case OrtakSabitler.PROBABILISTIC_ROAD_MAP:
							rapidlyAlgoritmaSecildi();
							getPrmBaglaDugmesi().setVisible(true);
							break;
						case OrtakSabitler.RAPIDLY_EXPANDING_RANDOM_TREE:
							rapidlyAlgoritmaSecildi();
							break;
						case OrtakSabitler.RAPIDLY_EXPANDING_RANDOM_TREE_STAR:
							rapidlyAlgoritmaSecildi();
							break;
						case OrtakSabitler.DYNAMIC_WINDIW_APPROACH:
							PotentialFieldDynamicWindowsSecildi();
							getVirtualForceCheckBoks().setVisible(false);
							break;
						case 7:
							PotentialFieldDynamicWindowsSecildi();
							getVirtualForceCheckBoks().setVisible(false);
							break;
							
						default:
							break;
						}
					}

				}
			});
		}

		return algoritmaSecComboBoks;
	}

	public JCheckBox getVirtualForceCheckBoks(){
		if (virtualForcecheckBoks == null) {
			virtualForcecheckBoks = new JCheckBox("Virtual Force");
			virtualForcecheckBoks.setHorizontalTextPosition(SwingConstants.LEFT);
			virtualForcecheckBoks.setVisible(false);
			virtualForcecheckBoks.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange()==ItemEvent.SELECTED){
						PotentialFieldKontrol.virtualForceSecildi();
					}else if(arg0.getStateChange()==ItemEvent.DESELECTED){
						PotentialFieldKontrol.virtualForceSecimKaldirildi();
					}
					
				}
			});
		}

		return virtualForcecheckBoks;
	}

	
	public JToggleButton getAlgoritmaOlusturButonu(){
		if (algoritmaOlusturButonu == null) {
			algoritmaOlusturButonu = new JToggleButton("Algoritma Oluþtur ");
			algoritmaOlusturButonu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CodeEditorPanel panel = new CodeEditorPanel();
					panel.setTitle("Algoritma Oluþtur");
					panel.setVisible(true);
					panel.setLocationRelativeTo(GlobalDegiskenler.getAnaPencere());
					panel.icerikAyarla(OrtakMetotlar.dosyaOku(OrtakSabitler.ALGORITMA_SCRIPT));

				}
			});
		}

		return algoritmaOlusturButonu;
	}

	public AlgoritmaOlusturPenceresi getAlgoritmaOlusturPenceresi() {
		if (algoritmaOlusturPenceresi == null) {
			algoritmaOlusturPenceresi = new AlgoritmaOlusturPenceresi();

		}

		return algoritmaOlusturPenceresi;
	}
	public JSlider getSlider(){
		if (rangeSlider == null) {
			rangeSlider = new JSlider(0,30);
			rangeSlider.setMajorTickSpacing(5);
			rangeSlider.setPaintLabels(true);
			rangeSlider.setPaintTrack(true);
			rangeSlider.setVisible(false);
			rangeSlider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent arg0) {
					GlobalDegiskenler.GetGridPanel().setRRtMultiplier(rangeSlider.getValue());
					
				}
			});
		}

		return rangeSlider;
	}
	public JButton getNoktaEkleDugmesi(){
		if (noktaEkleDugmesi == null) {
			noktaEkleDugmesi = new JButton("50 Nokta Ekle Dugmesi");
			noktaEkleDugmesi.setVisible(false);
			noktaEkleDugmesi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(getAlgoritmaSecComboBoks().getSelectedIndex()==3){
						GlobalDegiskenler.GetGridPanel().addPRM(50);
					}else if(getAlgoritmaSecComboBoks().getSelectedIndex()==4){
						GlobalDegiskenler.GetGridPanel().addRRT(50);
					}else if(getAlgoritmaSecComboBoks().getSelectedIndex()==5){
						GlobalDegiskenler.GetGridPanel().addRRTStar(50);
					}
					
				}
			});
		}

		return noktaEkleDugmesi;
	}
	public JButton getPrmBaglaDugmesi(){
		if (PRMBagla == null) {
			PRMBagla = new JButton("Prmleri Bagla");
			PRMBagla.setVisible(false);
			PRMBagla.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					KureselDegiskenler.setPrmBaglaTiklandi(true);
					GlobalDegiskenler.GetGridPanel().connect();
				}
			});
		}

		return PRMBagla;
	}
	private void AStarLeeAlgoritmaSecildi(){
		getNoktaEkleDugmesi().setVisible(false);
		getSlider().setVisible(false);
		getDurdurDugmesi().setEnabled(false);
		getVirtualForceCheckBoks().setVisible(false);
		GlobalDegiskenler.getAnaPencere().getEngelEklePanel().hareketliEngelPanelDurumlariDegistir(false);
	
	}
	
	private void PotentialFieldDynamicWindowsSecildi(){
		getDurdurDugmesi().setEnabled(true);
		getVirtualForceCheckBoks().setVisible(true);
		GlobalDegiskenler.getAnaPencere().getEngelEklePanel().hareketliEngelPanelDurumlariDegistir(true);
		getNoktaEkleDugmesi().setVisible(false);
		getSlider().setVisible(false);	
	}
	private void rapidlyAlgoritmaSecildi(){
		getNoktaEkleDugmesi().setVisible(true);
		getSlider().setVisible(true);
		getDurdurDugmesi().setEnabled(false);
		getVirtualForceCheckBoks().setVisible(false);
		GlobalDegiskenler.getAnaPencere().getEngelEklePanel().hareketliEngelPanelDurumlariDegistir(false);
	
	}
}

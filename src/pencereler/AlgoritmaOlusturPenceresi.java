package pencereler;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Ortak.OrtakMetotlar;

public class AlgoritmaOlusturPenceresi extends JDialog {
	
	private AlgoritmaOlusturPenceresi algoritmaOlusturPenceresi;
	private JPanel	algoritmaOlusturPanel;
	private JPanel algoritmaIcerikPanel;
	private JPanel dugmelerPanel;
	private JButton kaydetDugmesi;
	private JButton temizleDugmesi;
	private JButton kapatDugmesi;
	
	private JRadioButton hedefeDogruRadioButon;
	private JRadioButton kuzeyeDogruRadioButon;
	private JRadioButton guneyeDogruRadioButon;
	private JRadioButton batiyaDoðruRadioButon;
	private JRadioButton doguyaDogruRadioButon;
	private JRadioButton kuzeyDoguyaDogruRadioButon;
	private JRadioButton kuzeyBatiyaDogruRadioButon;
	private JRadioButton guneyDoguyaDogruRadioButon;
	private JRadioButton guneyBatiyaDogruRadioButon;
	
	private JRadioButton ustDuvarHedefeDogruRadioButon;	
	private JRadioButton ustDuvarGuneyDoguRadioButon;
	private JRadioButton ustDuvarGuneyRadioButon;
	private JRadioButton ustDuvarGuneyBatiRadioButon;
	
	private JRadioButton altDuvarHedefeDogruRadioButon;
	private JRadioButton altDuvarKuzeyDoguRadioButon;
	private JRadioButton altDuvarKuzeyRadioButon;
	private JRadioButton altDuvarKuzeyBatiRadioButon;
	
	private JRadioButton solDuvarHedefeDogruRadioButon;
	private JRadioButton solDuvarKuzeyDoguRadioButon;
	private JRadioButton solDuvarDoguRadioButon;
	private JRadioButton solDuvarGuneyDoguRadioButon;
	
	private JRadioButton sagDuvarHedefeDogruRadioButon;
	private JRadioButton sagDuvarKuzeyBatiRadioButon;
	private JRadioButton sagDuvarBatiRadioButon;
	private JRadioButton sagDuvarGuneyBatiRadioButon;
	
	
	private ButtonGroup butonGroupYonler;
	private ButtonGroup butonGroupUstDuvar;
	private ButtonGroup butonGroupAltDuvar;
	private ButtonGroup butonGroupSagDuvar;
	private ButtonGroup butonGroupSolDuvar;
	
	public AlgoritmaOlusturPenceresi(){
		algoritmaOlusturPenceresi= this;
		algoritmaOlusturPenceresi.setAlwaysOnTop(true);
		OrtakMetotlar.setBilesenBoyutu(algoritmaOlusturPenceresi, 300, 1000);
		setContentPane(getAlgoritmaOlusturPanel());
	}
	
	private JPanel getAlgoritmaOlusturPanel(){
		if (algoritmaOlusturPanel == null) {
			algoritmaOlusturPanel = new JPanel(new BorderLayout());
			algoritmaOlusturPanel.add(getDugmelerPanel(),BorderLayout.SOUTH);
			algoritmaOlusturPanel.add(getIcerikPanel(),BorderLayout.CENTER);
		}

		return algoritmaOlusturPanel;
	}
	private JPanel getDugmelerPanel(){
		if (dugmelerPanel == null) {
			dugmelerPanel = new JPanel();
			dugmelerPanel.add(getKaydetDugmesi());
			dugmelerPanel.add(getTemizleDugmesi());
			dugmelerPanel.add(getKapatDugmesi());
		}

		return dugmelerPanel;
	}
	private JPanel getIcerikPanel(){
		if (algoritmaIcerikPanel == null) {
			algoritmaIcerikPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(2, 2, 2, 2);
			gbc.gridy = 0;
			gbc.gridx = 0;
			JLabel hangiYoneGitsinEtiket = new JLabel("Yön Seciniz");
			algoritmaIcerikPanel.add(hangiYoneGitsinEtiket,gbc);
			gbc.gridy++;
			
			algoritmaIcerikPanel.add(getHedefeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getKuzeyeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getKuzeyBatiyaDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getBatiyaDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getGuneyBatiyaDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getGuneyeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getGuneyDoguyaDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getDoguyaDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getKuzeyDoguyaDogruRadioButon(),gbc);
			gbc.gridy++;
			JLabel ustDuvaraCarptiEtiket = new JLabel("Üst Duvara Çarpýnca Ne yapsýn");
			algoritmaIcerikPanel.add(ustDuvaraCarptiEtiket,gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getUstDuvarHedefeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getUstDuvarGuneyRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getUstDuvarGuneyBatiRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getUstDuvarGuneyDoguRadioButon(),gbc);
			gbc.gridy++;
			JLabel altDuvaraCarptiEtiket = new JLabel("Alt Duvara Çarpýnca Ne yapsýn");
			algoritmaIcerikPanel.add(altDuvaraCarptiEtiket,gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getALtDuvarHedefeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getAltDuvarKuzeyBatiRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getAltDuvarKuzeyDoguRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getAltDuvarKuzeyRadioButon(),gbc);
			gbc.gridy++;
			JLabel sagDuvaraCarptiEtiket = new JLabel("Sag Duvara Çarpýnca Ne yapsýn");
			algoritmaIcerikPanel.add(sagDuvaraCarptiEtiket,gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSagDuvarHedefeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSagDuvarBatiRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSagDuvarGuneyBatiyaRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSagDuvarKuzeyBatiRadioButon(),gbc);
			gbc.gridy++;
			JLabel solDuvaraCarptiEtiket = new JLabel("Sol Duvara Çarpýnca Ne yapsýn");
			algoritmaIcerikPanel.add(solDuvaraCarptiEtiket,gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSolDuvarHedefeDogruRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSolDuvarDoguRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSolDuvarGuneyDoguRadioButon(),gbc);
			gbc.gridy++;
			algoritmaIcerikPanel.add(getSolDuvarKuzeyDoguRadioButon(),gbc);
			
			butonGroupYonler = new ButtonGroup();
			butonGroupYonler.add(getHedefeDogruRadioButon());
			butonGroupYonler.add(getKuzeyeDogruRadioButon());
			butonGroupYonler.add(getKuzeyBatiyaDogruRadioButon());
			butonGroupYonler.add(getBatiyaDogruRadioButon());
			butonGroupYonler.add(getGuneyBatiyaDogruRadioButon());
			butonGroupYonler.add(getGuneyeDogruRadioButon());
			butonGroupYonler.add(getDoguyaDogruRadioButon());
			butonGroupYonler.add(getKuzeyDoguyaDogruRadioButon());
			butonGroupUstDuvar = new ButtonGroup();
			butonGroupUstDuvar.add(getUstDuvarHedefeDogruRadioButon());
			butonGroupUstDuvar.add(getUstDuvarGuneyRadioButon());
			butonGroupUstDuvar.add(getUstDuvarGuneyBatiRadioButon());
			butonGroupUstDuvar.add(getUstDuvarGuneyDoguRadioButon());
			butonGroupAltDuvar = new ButtonGroup();
			butonGroupAltDuvar.add(getALtDuvarHedefeDogruRadioButon());
			butonGroupAltDuvar.add(getAltDuvarKuzeyBatiRadioButon());
			butonGroupAltDuvar.add(getAltDuvarKuzeyDoguRadioButon());
			butonGroupAltDuvar.add(getAltDuvarKuzeyRadioButon());
			butonGroupSagDuvar = new ButtonGroup();
			butonGroupSagDuvar.add(getSagDuvarHedefeDogruRadioButon());
			butonGroupSagDuvar.add(getSagDuvarBatiRadioButon());
			butonGroupSagDuvar.add(getSagDuvarGuneyBatiyaRadioButon());
			butonGroupSagDuvar.add(getSagDuvarKuzeyBatiRadioButon());
			butonGroupSolDuvar = new ButtonGroup();
			butonGroupSolDuvar.add(getSolDuvarHedefeDogruRadioButon());
			butonGroupSolDuvar.add(getSolDuvarGuneyDoguRadioButon());
			butonGroupSolDuvar.add(getSolDuvarKuzeyDoguRadioButon());
			butonGroupSolDuvar.add(getSolDuvarDoguRadioButon());
		}

		return algoritmaIcerikPanel;
	}
	private JButton getTemizleDugmesi(){
		if (temizleDugmesi == null) {
			temizleDugmesi = new JButton("Temizle Dugmesi");
			
		}

		return temizleDugmesi;
	}
	
	private JButton getKapatDugmesi(){
		if (kapatDugmesi == null) {
			kapatDugmesi = new JButton("Kapat Dugmesi");
			kapatDugmesi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					algoritmaOlusturPenceresi.setVisible(false);
					
				}
			});
		}

		return kapatDugmesi;
	}
	private JButton getKaydetDugmesi(){
		if (kaydetDugmesi == null) {
			kaydetDugmesi = new JButton("Kaydet Dugmesi");
			
		}

		return kaydetDugmesi;
	}
	
	private JRadioButton getHedefeDogruRadioButon(){
		if (hedefeDogruRadioButon == null) {
			hedefeDogruRadioButon = new JRadioButton("Hedefe Dogru");
			hedefeDogruRadioButon.setSelected(true);
		}

		return hedefeDogruRadioButon;
	}
	private JRadioButton getKuzeyeDogruRadioButon(){
		if (kuzeyeDogruRadioButon == null) {
			kuzeyeDogruRadioButon = new JRadioButton("Kuzeye Dogru");
			
		}

		return kuzeyeDogruRadioButon;
	}
	private JRadioButton getGuneyeDogruRadioButon(){
		if (guneyeDogruRadioButon == null) {
			guneyeDogruRadioButon = new JRadioButton("Guneye Doðru");
			
		}

		return guneyeDogruRadioButon;
	}
	
	private JRadioButton getBatiyaDogruRadioButon(){
		if (batiyaDoðruRadioButon == null) {
			batiyaDoðruRadioButon = new JRadioButton("Batiya Dogru");
			
		}

		return batiyaDoðruRadioButon;
	}
	private JRadioButton getDoguyaDogruRadioButon(){
		if (doguyaDogruRadioButon == null) {
			doguyaDogruRadioButon = new JRadioButton("Doguya Dogru ");
			
		}

		return doguyaDogruRadioButon;
	}
	
	private JRadioButton getKuzeyDoguyaDogruRadioButon(){
		if (kuzeyDoguyaDogruRadioButon == null) {
			kuzeyDoguyaDogruRadioButon = new JRadioButton("Kuzey Doðuya Doðru");
			
		}

		return kuzeyDoguyaDogruRadioButon;
	}
	private JRadioButton getKuzeyBatiyaDogruRadioButon(){
		if (kuzeyBatiyaDogruRadioButon == null) {
			kuzeyBatiyaDogruRadioButon = new JRadioButton("Kuzey Batýya Doðru");
			
		}

		return kuzeyBatiyaDogruRadioButon;
	}
	
	private JRadioButton getGuneyDoguyaDogruRadioButon(){
		if (guneyDoguyaDogruRadioButon == null) {
			guneyDoguyaDogruRadioButon = new JRadioButton("Guney Doguya Doðru");
			
		}

		return guneyDoguyaDogruRadioButon;
	}
	
	private JRadioButton getGuneyBatiyaDogruRadioButon(){
		if (guneyBatiyaDogruRadioButon == null) {
			guneyBatiyaDogruRadioButon = new JRadioButton("Guney Batýya Doðru");
			
		}

		return guneyBatiyaDogruRadioButon;
	}
	
	private JRadioButton getUstDuvarHedefeDogruRadioButon(){
		if (ustDuvarHedefeDogruRadioButon == null) {
			ustDuvarHedefeDogruRadioButon = new JRadioButton("Hedefe Dogru");
			
		}

		return ustDuvarHedefeDogruRadioButon;
	}
	
	private JRadioButton getUstDuvarGuneyRadioButon(){
		if (ustDuvarGuneyRadioButon == null) {
			ustDuvarGuneyRadioButon = new JRadioButton("Guneye Doðru");
			
		}

		return ustDuvarGuneyRadioButon;
	}
	
	private JRadioButton getUstDuvarGuneyDoguRadioButon(){
		if (ustDuvarGuneyDoguRadioButon == null) {
			ustDuvarGuneyDoguRadioButon = new JRadioButton("Guney Doðuya Doðru");
			
		}

		return ustDuvarGuneyDoguRadioButon;
	}
	
	private JRadioButton getUstDuvarGuneyBatiRadioButon(){
		if (ustDuvarGuneyBatiRadioButon == null) {
			ustDuvarGuneyBatiRadioButon = new JRadioButton("Guney Batýya Doðru");
			
		}

		return ustDuvarGuneyBatiRadioButon;
	}
	
	private JRadioButton getALtDuvarHedefeDogruRadioButon(){
		if (altDuvarHedefeDogruRadioButon == null) {
			altDuvarHedefeDogruRadioButon = new JRadioButton("Hedefe Dogru");
			
		}

		return altDuvarHedefeDogruRadioButon;
	}
	
	private JRadioButton getAltDuvarKuzeyDoguRadioButon(){
		if (altDuvarKuzeyDoguRadioButon == null) {
			altDuvarKuzeyDoguRadioButon = new JRadioButton("Kuzey Doguya Dogru");
			
		}

		return altDuvarKuzeyDoguRadioButon;
	}
	private JRadioButton getAltDuvarKuzeyRadioButon(){
		if (altDuvarKuzeyRadioButon == null) {
			altDuvarKuzeyRadioButon = new JRadioButton("Kuzeye Doðru");
			
		}

		return altDuvarKuzeyRadioButon;
	}
	
	private JRadioButton getAltDuvarKuzeyBatiRadioButon(){
		if (altDuvarKuzeyBatiRadioButon == null) {
			altDuvarKuzeyBatiRadioButon = new JRadioButton("Kuzey Batiya Dogru");
			
		}

		return altDuvarKuzeyBatiRadioButon;
	}
	
	private JRadioButton getSolDuvarHedefeDogruRadioButon(){
		if (solDuvarHedefeDogruRadioButon == null) {
			solDuvarHedefeDogruRadioButon = new JRadioButton("Hedefe Doðru");
			
		}

		return solDuvarHedefeDogruRadioButon;
	}
	private JRadioButton getSolDuvarKuzeyDoguRadioButon(){
		if (solDuvarKuzeyDoguRadioButon == null) {
			solDuvarKuzeyDoguRadioButon = new JRadioButton("Kuzey Doðuya Doðru");
			
		}

		return solDuvarKuzeyDoguRadioButon;
	}
	private JRadioButton getSolDuvarDoguRadioButon(){
		if (solDuvarDoguRadioButon == null) {
			solDuvarDoguRadioButon = new JRadioButton("Doguya Dogru");
			
		}

		return solDuvarDoguRadioButon;
	}
	
	private JRadioButton getSolDuvarGuneyDoguRadioButon(){
		if (solDuvarGuneyDoguRadioButon == null) {
			solDuvarGuneyDoguRadioButon = new JRadioButton("Guney Doguya Doðru");
			
		}

		return solDuvarGuneyDoguRadioButon;
	}
	
	private JRadioButton getSagDuvarHedefeDogruRadioButon(){
		if (sagDuvarHedefeDogruRadioButon == null) {
			sagDuvarHedefeDogruRadioButon = new JRadioButton("Hedefe Dogru");
			
		}

		return sagDuvarHedefeDogruRadioButon;
	}
	private JRadioButton getSagDuvarBatiRadioButon(){
		if (sagDuvarBatiRadioButon == null) {
			sagDuvarBatiRadioButon = new JRadioButton("Batýya Doðru");
			
		}

		return sagDuvarBatiRadioButon;
	}
	
	private JRadioButton getSagDuvarKuzeyBatiRadioButon(){
		if (sagDuvarKuzeyBatiRadioButon == null) {
			sagDuvarKuzeyBatiRadioButon = new JRadioButton("Kuzey Batýya Doðru");
			
		}

		return sagDuvarKuzeyBatiRadioButon;
	}
	
	private JRadioButton getSagDuvarGuneyBatiyaRadioButon(){
		if (sagDuvarGuneyBatiRadioButon == null) {
			sagDuvarGuneyBatiRadioButon = new JRadioButton("Güney Batiya Dogru");
			
		}

		return sagDuvarGuneyBatiRadioButon;
	}
}

package gorunum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.jidesoft.pane.CollapsiblePane;
import com.jidesoft.swing.JideButton;
import com.jidesoft.swing.JideSwingUtilities;

import Mapping.GridEngel;
import Ortak.GlobalDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import dwa2.GlobalDegisken;
import izlekler.HareketliEngelThread;
import kontrol.GridPanelKontrol;
import kontrol.RobotKontrol;
import paneller.HareketliEngelPanel;

public class EngelEklePanel extends JPanel {

	private EngelEklePanel engelEklePanel;

	private JButton engelEkleButon;
	private JButton engelSilButon;
	private JButton hareketliEngelEkleButon;
	private JButton hareketliEngelSilButon;
	private JPanel dugmePanel;
	private JPanel icerikPanel;
	private JPanel hareketliDugmePanel;
	private JPanel hareketliIcerikPanel;
	private ButtonGroup sabitEngelButonGroup;
	private ButtonGroup hareketliEngelButonGroup;
	private JCheckBox	hareketliEngelCalissinMiCheckBoks;
	private JPanel sabitEngelPanel;
	private JPanel hareketliEngelPanel;
	private HareketliEngelThread hareketliEngelThread;
	private CollapsiblePane robotOzellikCollapsiblePane;
	private JideButton[] robotBilgiDugmeler = new JideButton[OrtakSabitler.MAKSIMUM_ROBOT_SAYISI]; 
	private RobotBilgiDialog[] robotDialogPaneller = new RobotBilgiDialog[OrtakSabitler.MAKSIMUM_ROBOT_SAYISI]; 
	
	public EngelEklePanel(){
		engelEklePanel = this;
		sabitEngelButonGroup = new ButtonGroup();
		hareketliEngelButonGroup = new ButtonGroup();
		robotBilgiDugmeleriOlustur();
		sabitEngellerRadioButonuDoldur();
		hareketliEngelPanelDoldur();
		panelOlustur();
	}
	private void robotBilgiDugmeleriOlustur() {
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			robotDialogPaneller[i] = new RobotBilgiDialog(i);
			switch (i) {
			case 0:
				robotBilgiDugmeler[i]=createHyperlinkButton("1", new ImageIcon(GridPanelKontrol.BIR_NUMARA_DOSYA_PATH));
				break;
			case 1:
				robotBilgiDugmeler[i]=createHyperlinkButton("2", new ImageIcon(GridPanelKontrol.IKI_NUMARA_DOSYA_PATH));
				break;
			case 2:
				robotBilgiDugmeler[i]=createHyperlinkButton("3", new ImageIcon(GridPanelKontrol.UC_NUMARA_DOSYA_PATH));
				break;
			case 3:
				robotBilgiDugmeler[i]=createHyperlinkButton("4", new ImageIcon(GridPanelKontrol.DORT_NUMARA_DOSYA_PATH));
				break;
			case 4:
				robotBilgiDugmeler[i]=createHyperlinkButton("5", new ImageIcon(GridPanelKontrol.BES_NUMARA_DOSYA_PATH));
				break;
			case 5:
				robotBilgiDugmeler[i]=createHyperlinkButton("6", new ImageIcon(GridPanelKontrol.ALTI_NUMARA_DOSYA_PATH));
				break;
			case 6:
				robotBilgiDugmeler[i]=createHyperlinkButton("7", new ImageIcon(GridPanelKontrol.YEDI_NUMARA_DOSYA_PATH));
				break;
			case 7:
				robotBilgiDugmeler[i]=createHyperlinkButton("8", new ImageIcon(GridPanelKontrol.SEKIZ_NUMARA_DOSYA_PATH));
				break;
			case 8:
				robotBilgiDugmeler[i]=createHyperlinkButton("9", new ImageIcon(GridPanelKontrol.DOKUZ_NUMARA_DOSYA_PATH));
				break;
			case 9:
				robotBilgiDugmeler[i]=createHyperlinkButton("10", new ImageIcon(GridPanelKontrol.ON_NUMARA_DOSYA_PATH));
				break;
			default:
				break;
			}
		}
		
	}
	private void hareketliEngelPanelDoldur() {
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ENGEL_SAYISI;i++){
			HareketliEngelPanel engelPanel = new HareketliEngelPanel(i);
			engelPanel.setVisible(false);
			hareketliEngelButonGroup.add(engelPanel.getEnselSecimRadioButon());
			GlobalDegiskenler.getHareketliEngelPanelleri().add(engelPanel);
		}
		
	}
	private void sabitEngellerRadioButonuDoldur() {
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ENGEL_SAYISI;i++){
			JRadioButton radioButton = new JRadioButton("Engel "+(i+1));
			radioButton.setVisible(false);
			GlobalDegiskenler.getEngelRadioButonlar().add(radioButton);
			sabitEngelButonGroup.add(radioButton);
		}
		
	}
	private void panelOlustur() {
		engelEklePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);

		engelEklePanel.add(getSabitEngelPanel(),gbc);
		gbc.gridy++;
		gbc.insets = new Insets(0, 0, 0, 0);
		engelEklePanel.add(getHareketliEngelPanel(),gbc);
		gbc.gridy++;
		engelEklePanel.add(getRobotOzellikCollapsiblePane(),gbc);

	}
	
	private JButton getEngelEkleButton(){
		if (engelEkleButon == null) {
			engelEkleButon = new JButton("Engel Ekle");
			engelEkleButon
	        .setBorder(BorderFactory.createLineBorder(Color.white));
			engelEkleButon.setMargin(new Insets(2, 2, 2, 2));

		//	engelEkleButon.setForeground(Color.WHITE);
		//	engelEkleButon.setBackground(Color.DARK_GRAY);
			engelEkleButon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int sayi = sayiBul();
					if(sayi<OrtakSabitler.MAKSIMUM_ENGEL_SAYISI){
						GlobalDegiskenler.getEngelRadioButonlar().get(sayi).setVisible(true);
					}

				}
			});
		}

		return engelEkleButon;
	}
	
	protected int sayiBul() {
		int sayi = 0;
		for(int i=0;i<GlobalDegiskenler.getEngelRadioButonlar().size();i++){
			if(GlobalDegiskenler.getEngelRadioButonlar().get(i).isVisible()){
				sayi++;
			}
		}
		return sayi;
	}
	protected int hareketliEngelSayiBul(){
		int sayi =0;
		for(int i=0;i<GlobalDegiskenler.getHareketliEngelPanelleri().size();i++){
			if(GlobalDegiskenler.getHareketliEngelPanelleri().get(i).isVisible()){
				sayi++;
			}
		}
		return sayi;
	}
	private JButton getEngelSilButon(){
		if (engelSilButon == null) {
			engelSilButon = new JButton("Engel Sil");
			engelSilButon
	        .setBorder(BorderFactory.createLineBorder(Color.white));
			engelSilButon.setMargin(new Insets(2, 2, 2, 2));

		//	engelSilButon.setForeground(Color.WHITE);
		//	engelSilButon.setBackground(Color.DARK_GRAY);
			engelSilButon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int sayi = sayiBul();
					if(sayi>0){
						GlobalDegiskenler.getEngelRadioButonlar().get(sayi-1).setVisible(false);
						engelleriSil(sayi);
						for (int i = 0; i < OrtakSabitler.GRID_BOY; i++) {
							for (int j = 0; j < OrtakSabitler.GRID_EN; j++) {
								GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].setIcon(null);
							}
						}
						GlobalDegiskenler.getEngelNoktalari().remove(sayi-1);
						GridPanelKontrol.sabitEngelInisleriniCiz();
					}
					GlobalDegiskenler.GetGridPanel().revalidate();
					GlobalDegiskenler.GetGridPanel().repaint();
				}
			});
		}

		return engelSilButon;
	}
	private JButton getHareketliEngelEkleButon(){
		if (hareketliEngelEkleButon == null) {
			hareketliEngelEkleButon = new JButton("Engel Ekle");
			hareketliEngelEkleButon
	        .setBorder(BorderFactory.createLineBorder(Color.white));
			hareketliEngelEkleButon.setMargin(new Insets(2, 2, 2, 2));
		//	hareketliEngelEkleButon.setForeground(Color.WHITE);
		//	hareketliEngelEkleButon.setBackground(Color.DARK_GRAY);
			hareketliEngelEkleButon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int sayi = hareketliEngelSayiBul();
					if(sayi>0){
						hareketliEngelleriSil(sayi);
					}
					if(sayi<OrtakSabitler.MAKSIMUM_ENGEL_SAYISI){
						GlobalDegiskenler.getHareketliEngelPanelleri().get(sayi).setVisible(true);
					}
					
				}
			});
		}

		return hareketliEngelEkleButon;
	
	}
	private JButton getHareketliEngelSilButon(){
		if (hareketliEngelSilButon == null) {
			hareketliEngelSilButon = new JButton("Engel Sil");
			hareketliEngelSilButon
	        .setBorder(BorderFactory.createLineBorder(Color.white));
			hareketliEngelSilButon.setMargin(new Insets(2, 2, 2, 2));
	//		hareketliEngelSilButon.setForeground(Color.WHITE);
	//		hareketliEngelSilButon.setBackground(Color.DARK_GRAY);
			hareketliEngelSilButon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int sayi = hareketliEngelSayiBul();
					if(sayi>0){
						GlobalDegiskenler.getHareketliEngelPanelleri().get(sayi-1).setVisible(false);
						
					}
					GlobalDegiskenler.GetGridPanel().revalidate();
					GlobalDegiskenler.GetGridPanel().repaint();
					
				}
			});
		}

		return hareketliEngelSilButon;
	}
	
	protected void engelleriSil(int sayi) {
		ArrayList<GridEngel> engelTemp = new ArrayList<GridEngel>();
		for(int i=0;i<GlobalDegiskenler.getEngelNoktalari().size();i++){
			if(GlobalDegiskenler.getEngelNoktalari().get(i).getEngelIndeks()!=sayi){
				engelTemp.add(GlobalDegiskenler.getEngelNoktalari().get(i));
			}
		}
		GlobalDegiskenler.setEngelNoktalari(engelTemp);
		
	}
	protected void hareketliEngelleriSil(int sayi){
		ArrayList<GridEngel> engelTemp = new ArrayList<GridEngel>();
		for(int i=0;i<GlobalDegiskenler.getHareketliEngelNoktalari().size();i++){
			if(GlobalDegiskenler.getHareketliEngelNoktalari().get(i).getEngelIndeks()!=sayi){
				engelTemp.add(GlobalDegiskenler.getHareketliEngelNoktalari().get(i));
			}
		}
		GlobalDegisken.obstacleDegisti();
		GlobalDegiskenler.setHareketliEngelNoktalari(engelTemp);
		
	
	}
	
	private JPanel getDugmePanel(){
		if (dugmePanel == null) {
			dugmePanel = new JPanel();
			dugmePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			dugmePanel.add(getEngelEkleButton());
			dugmePanel.add(getEngelSilButon());
		}

		return dugmePanel;
	}
	private JPanel getIcerikPanel(){
		if (icerikPanel == null) {
			icerikPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			OrtakMetotlar.setBilesenBoyutu(icerikPanel, 200, 200);
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(2, 2, 2, 2);

			gbc.gridy = 0;
			gbc.gridx = 0;
			for(int i=0;i<GlobalDegiskenler.getEngelRadioButonlar().size();i++){
				icerikPanel.add(GlobalDegiskenler.getEngelRadioButonlar().get(i),gbc);
				gbc.gridy++;
			}
		}

		return icerikPanel;
	}
	private JPanel getSabitEngelPanel(){
		if (sabitEngelPanel == null) {
			sabitEngelPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(1, 1, 1, 1);
			gbc.gridy = 0;
			gbc.gridx = 0;
			JLabel sabitEngelEtiket = new JLabel("Sabit Engel");
			sabitEngelPanel.add(sabitEngelEtiket,gbc);
			gbc.gridy++;
			sabitEngelPanel.add(getDugmePanel(),gbc);
			gbc.gridy++;
			sabitEngelPanel.add(getIcerikPanel(),gbc);
		}

		return sabitEngelPanel;
	}
	private JPanel getHareketliEngelPanel(){
		if (hareketliEngelPanel == null) {
			hareketliEngelPanel = new JPanel(new GridBagLayout());
			hareketliEngelPanel.setVisible(false);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(2, 2, 2, 2);
			gbc.gridy = 0;
			gbc.gridx = 0;
			JPanel hareketCalissinPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel hareketliEngelEtiket = new JLabel("Hareketli Engel");
			hareketCalissinPanel.add(hareketliEngelEtiket);
			hareketCalissinPanel.add(getHareketliEngelCalissinMiCheckBoks());
			hareketliEngelPanel.add(hareketCalissinPanel,gbc);
//			gbc.gridx++;
//			hareketliEngelPanel.add(getHareketliEngelCalissinMiCheckBoks(),gbc);
			gbc.gridx=0;
			gbc.gridy++;
			hareketliEngelPanel.add(getHareketliDugmePanel(),gbc);
			gbc.gridy++;
			hareketliEngelPanel.add(getHareketliIcerikPanel(),gbc);
		}

		return hareketliEngelPanel;
	}
	public JCheckBox getHareketliEngelCalissinMiCheckBoks() {
		if (hareketliEngelCalissinMiCheckBoks == null) {
			hareketliEngelCalissinMiCheckBoks = new JCheckBox("Hareket Etsin");
			hareketliEngelCalissinMiCheckBoks.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					hareketliEngelThread = new HareketliEngelThread();
					hareketliEngelThread.start();
				}
			});
		}

		return hareketliEngelCalissinMiCheckBoks;
	}
	private JPanel getHareketliDugmePanel(){
		if (hareketliDugmePanel == null) {
			hareketliDugmePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			hareketliDugmePanel.add(getHareketliEngelEkleButon());
			hareketliDugmePanel.add(getHareketliEngelSilButon());
		}

		return hareketliDugmePanel;
	}
	
	private JPanel getHareketliIcerikPanel(){
		if (hareketliIcerikPanel == null) {
			hareketliIcerikPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			OrtakMetotlar.setBilesenBoyutu(hareketliIcerikPanel, 200, 200);
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(0, 0, 0, 0);
			gbc.gridy = 0;
			gbc.gridx = 0;
			for(int i=0;i<GlobalDegiskenler.getHareketliEngelPanelleri().size();i++){
			
				hareketliIcerikPanel.add(GlobalDegiskenler.getHareketliEngelPanelleri().get(i),gbc);
				gbc.gridy++;
			}
		}

		return hareketliIcerikPanel;
	}
	public void hareketliEngelPanelDurumlariDegistir(boolean durum) {
		getHareketliEngelPanel().setVisible(durum);
		getSabitEngelPanel().setVisible(!durum);
	}
	
	private CollapsiblePane getRobotOzellikCollapsiblePane(){
		if (robotOzellikCollapsiblePane == null) {
			robotOzellikCollapsiblePane = new CollapsiblePane("Robot Bilgiler");
			robotOzellikCollapsiblePane.setName("Robot Bilgiler");
			robotOzellikCollapsiblePane.setTitleIcon(new ImageIcon(OrtakSabitler.ROBOT_BILGI_IKON_DOSYA_YOLU));
		       JPanel labelPanel = new JPanel();
		        labelPanel.setOpaque(false);
		        labelPanel.setLayout(new GridLayout(6, 1, 1, 0));
		        for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
		        	labelPanel.add(robotBilgiDugmeler[i]);
		        }
		        labelPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
		        robotOzellikCollapsiblePane.setContentPane(JideSwingUtilities.createTopPanel(labelPanel));
		        robotOzellikCollapsiblePane.setEmphasized(true);
		}

		return robotOzellikCollapsiblePane;
	}
	 private JideButton createHyperlinkButton(String name, Icon icon) {
	        final JideButton button = new JideButton(name, icon);
	        button.setName(name);
	        button.setButtonStyle(JideButton.HYPERLINK_STYLE);
	        button.setOpaque(false);
	        button.setHorizontalAlignment(SwingConstants.LEADING);
	        button.setRequestFocusEnabled(true);
	        button.setFocusable(true);
	        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (button.getName()) {
					case "1":
						robotDialogPaneller[0].setVisible(true);
						robotDialogPaneller[0].yenile();
						break;
					case "2":
						robotDialogPaneller[1].setVisible(true);
						robotDialogPaneller[1].yenile();
						break;
					case "3":
						robotDialogPaneller[2].setVisible(true);
						robotDialogPaneller[2].yenile();
						break;
					case "4":
						robotDialogPaneller[3].setVisible(true);
						robotDialogPaneller[3].yenile();
						break;
					case "5":
						robotDialogPaneller[4].setVisible(true);
						robotDialogPaneller[4].yenile();
						break;
					case "6":
						robotDialogPaneller[5].setVisible(true);
						robotDialogPaneller[5].yenile();
						break;
					case "7":
						robotDialogPaneller[6].setVisible(true);
						robotDialogPaneller[6].yenile();
						break;
					case "8":
						robotDialogPaneller[7].setVisible(true);
						robotDialogPaneller[7].yenile();
						break;
					case "9":
						robotDialogPaneller[8].setVisible(true);
						robotDialogPaneller[8].yenile();
						break;
					case "10":
						robotDialogPaneller[9].setVisible(true);
						robotDialogPaneller[9].yenile();
						break;
					default:
						break;
					}
					
				}
			});
	        return button;
	    }
	public RobotBilgiDialog[] getRobotDialogPaneller() {
		return robotDialogPaneller;
	}

}


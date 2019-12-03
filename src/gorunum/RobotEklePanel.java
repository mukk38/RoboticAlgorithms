package gorunum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.jidesoft.swing.JideButton;

import Ortak.GlobalDegiskenler;
import Ortak.OrtakSabitler;
import kontrol.RobotKontrol;

public class RobotEklePanel extends JPanel {

	private RobotEklePanel robotEklePanel;
	private RobotOzellikPanel robotOzellikPanel;
//	private ArrayList<JRadioButton> robotlarRadioButton;
	private JButton robotEkleButon;
	private JButton robotSilButon;
	private JPanel dugmePanel;
	private JPanel icerikPanel;
	public final static String BIR_NUMARA_DOSYA_PATH= ".//external//icon//1Numara.png";
	public final static String IKI_NUMARA_DOSYA_PATH= ".//external//icon//2Numara.png";
	public final static String UC_NUMARA_DOSYA_PATH= ".//external//icon//3Numara.png";
	public final static String DORT_NUMARA_DOSYA_PATH= ".//external//icon//4Numara.png";
	public final static String BES_NUMARA_DOSYA_PATH= ".//external//icon//5Numara.png";
	private ButtonGroup butonGroup;
	public RobotEklePanel(){
		robotEklePanel = this;
		robotOzellikPanel = new RobotOzellikPanel();
//		robotlarRadioButton = new ArrayList<JRadioButton>();
		butonGroup = new ButtonGroup();
		robotlarRadioButonuDoldur();
		
		panelOlustur();
	}
	private void robotlarRadioButonuDoldur() {
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			JRadioButton radioButton = new JRadioButton("Robot "+(i+1));
			//radioButton.setIcon(new ImageIcon(BIR_NUMARA_DOSYA_PATH));
			radioButton.setVisible(false);
			GlobalDegiskenler.getRobotRadioButonlar().add(radioButton);
			butonGroup.add(radioButton);
		}
		
	}
	private void panelOlustur() {
		robotEklePanel.setLayout(new BorderLayout());
		robotEklePanel.add(getDugmePanel(),BorderLayout.NORTH);
		robotEklePanel.add(getIcerikPanel(),BorderLayout.CENTER);
		robotEklePanel.add(robotOzellikPanel,BorderLayout.SOUTH);
	}
	
	private JButton getRobotEkleButton(){
		if (robotEkleButon == null) {
			robotEkleButon = new JButton("Robot Ekle");
			robotEkleButon
	        .setBorder(BorderFactory.createLineBorder(Color.white));
			robotEkleButon.setMargin(new Insets(2, 2, 2, 2));

			//robotEkleButon.setForeground(Color.WHITE);
			//robotEkleButon.setBackground(Color.DARK_GRAY);
			robotEkleButon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				//	int sayi = robotlarRadioButton.size();
					int sayi = sayiBul();
					if(sayi<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI){
						GlobalDegiskenler.getRobotRadioButonlar().get(sayi).setVisible(true);
					}
//					JRadioButton radioButton = RobotKontrol.yeniRadioButonOlustur(sayi);
//					butonGroup.add(radioButton);
//					robotlarRadioButton.add(radioButton);
			//		icerikPanelinIciDegisti();
				}
			});
		}

		return robotEkleButon;
	}
	
	protected int sayiBul() {
		int sayi = 0;
		for(int i=0;i<GlobalDegiskenler.getRobotRadioButonlar().size();i++){
			if(GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()){
				sayi++;
			}
		}
		return sayi;
	}
	private JButton getRobotSilButon(){
		if (robotSilButon == null) {
			robotSilButon = new JButton("Robot Sil");
			robotSilButon
	        .setBorder(BorderFactory.createLineBorder(Color.white));
			robotSilButon.setMargin(new Insets(2, 2, 2, 2));

		//	robotSilButon.setForeground(Color.WHITE);
		//	robotSilButon.setBackground(Color.DARK_GRAY);
			robotSilButon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int sayi = sayiBul();
					if(sayi>0){
						GlobalDegiskenler.getRobotRadioButonlar().get(sayi-1).setVisible(false);
						GlobalDegiskenler.getRobotRadioButonlar().get(sayi-1).setSelected(false);
					}
					GlobalDegiskenler.GetGridPanel().revalidate();
					GlobalDegiskenler.GetGridPanel().repaint();
//					int sayi = robotlarRadioButton.size();
//					if(sayi>0){
//						robotlarRadioButton.remove(sayi-1);
//					//	icerikPanelinIciDegisti();
//					}
				}
			});
		}

		return robotSilButon;
	}
	private JPanel getDugmePanel(){
		if (dugmePanel == null) {
			dugmePanel = new JPanel();
			dugmePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			dugmePanel.add(getRobotEkleButton());
			dugmePanel.add(getRobotSilButon());
		}

		return dugmePanel;
	}
	private JPanel getIcerikPanel(){
		if (icerikPanel == null) {
			icerikPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(2, 2, 2, 2);

			gbc.gridy = 0;
			gbc.gridx = 0;
			for(int i=0;i<GlobalDegiskenler.getRobotRadioButonlar().size();i++){
				icerikPanel.add(GlobalDegiskenler.getRobotRadioButonlar().get(i),gbc);
				gbc.gridy++;
			}
		}

		return icerikPanel;
	}
	


}

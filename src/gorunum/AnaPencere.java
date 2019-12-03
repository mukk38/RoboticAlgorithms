package gorunum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Ortak.GlobalDegiskenler;
import Ortak.OrtakMetotlar;

public class AnaPencere extends JDialog {

	private AnaPencere anaEkran = null;
	private AraclarPanel	araclarPanel;
	private RobotEklePanel robotEklePanel;
	private JPanel	araclarDugmePanel;
	private GridPanel	gridPanel;
	private JPanel anaPanel;
	private JPanel anaEkranPanel;
	private EngelEklePanel engelEklePanel;
	public AnaPencere(){
		setUndecorated(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		anaEkran = this;
		OrtakMetotlar.setBilesenBoyutu(this, OrtakMetotlar.getAnaEkranXBoy(), OrtakMetotlar.getAnaEkranYBoy());
		araclarPanel = new AraclarPanel();
		robotEklePanel = new RobotEklePanel();
		engelEklePanel = new EngelEklePanel();
		gridPanel = GlobalDegiskenler.GetGridPanel();
		setContentPane(getAnaEkranPanel());
		OrtakMetotlar.bilesenKonumunuMerkezle(this);
		anaEkran.getRootPane().setBorder(OrtakMetotlar.getBorder());
	}
	private JPanel getAnaEkranPanel() {
		if (anaEkranPanel == null) {
			anaEkranPanel = new JPanel(new BorderLayout());
		anaEkranPanel.add(getAnaPanel(),BorderLayout.CENTER);
		}
		return anaEkranPanel;
		
	}
	private JPanel getAnaPanel(){
		if (anaPanel == null) {
			anaPanel = new JPanel(new BorderLayout());
			anaPanel.add(araclarPanel,BorderLayout.NORTH);
			anaPanel.add(getMerkezPanel(),BorderLayout.CENTER);
			anaPanel.add(robotEklePanel,BorderLayout.WEST);
			anaPanel.add(engelEklePanel,BorderLayout.EAST);
		}

		return anaPanel;
	}
	

	private JPanel getMerkezPanel(){
		if (araclarDugmePanel == null) {
			araclarDugmePanel = new JPanel(new BorderLayout());
			araclarDugmePanel.add(gridPanel,BorderLayout.CENTER);
		}

		return araclarDugmePanel;
	}

	public GridPanel getGridPanel() {
		return gridPanel;
	}
	public AraclarPanel getAraclarPanel() {
		return araclarPanel;
	}
	public EngelEklePanel getEngelEklePanel() {
		return engelEklePanel;
	}

}

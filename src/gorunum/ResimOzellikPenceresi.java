package gorunum;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Enum.Algoritmalar;
import Ortak.OrtakMetotlar;
import model.AlgoritmaModel;

public class ResimOzellikPenceresi extends JDialog {
	private int robotIndeks;
	private Algoritmalar algoritmalar;
	private int algoritmaIndeks;
	private String imajDosyaYol;
	private JPanel anaPanel;
	private JPanel ozelliklerPanel;
	private JLabel acisalHizEtiket;
	private JLabel katedilenYolEtiket;
	private JLabel harcananZamanEtiket;
	private JLabel basariliMiEtiket;
	private JLabel hesaplananZamanEtiket;
	
	
	public ResimOzellikPenceresi(int robotIndeks_,Algoritmalar algo,int algoritmaIndeks_,String imajDosyaYol_){
		robotIndeks=robotIndeks_;
		algoritmalar=algo;
		algoritmaIndeks=algoritmaIndeks_;
		imajDosyaYol=imajDosyaYol_;
    	OrtakMetotlar.setBilesenBoyutu(this, 900, 900);
    	this.setContentPane(getAnaPanel());
	}


	private JPanel getAnaPanel() {
		if (anaPanel == null) {
			anaPanel = new JPanel(new BorderLayout());
			
			anaPanel.add(new JLabel(new ImageIcon(imajDosyaYol)),BorderLayout.CENTER);
			anaPanel.add(getOzelliklerPanel(),BorderLayout.SOUTH);
		}

		return anaPanel;
	}


	private JPanel getOzelliklerPanel() {
	if (ozelliklerPanel == null) {
		ozelliklerPanel = new JPanel(new GridBagLayout());
		ozelliklerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);

		ozelliklerPanel.add(new JLabel("Katedilen Yol: "),gbc);
		gbc.gridx++;
		ozelliklerPanel.add(getKatEdilenYolEtiket(),gbc);
		gbc.gridx=0;
		gbc.gridy++;
		gbc.insets = new Insets(5, 5, 5, 5);
		ozelliklerPanel.add(new JLabel("Harcanan Zaman: "),gbc);
		gbc.gridx++;
		ozelliklerPanel.add(getHarcananZamanEtiket(),gbc);
		gbc.gridx=0;
		gbc.gridy++;
		ozelliklerPanel.add(new JLabel("Açýsal Hýz: "),gbc);
		gbc.gridx++;
		ozelliklerPanel.add(getAcisalHizEtiket(),gbc);
		gbc.gridx=0;
		gbc.gridy++;
		ozelliklerPanel.add(new JLabel("Baþarýlý Mý: "),gbc);
		gbc.gridx++;
		ozelliklerPanel.add(getBasariOraniEtiket(),gbc);
		gbc.gridx=0;
		gbc.gridy++;
		ozelliklerPanel.add(new JLabel("Hesaplanan Zaman: "),gbc);
		gbc.gridx++;
		ozelliklerPanel.add(getHesaplananZamanEtiket(),gbc);
	}

	return ozelliklerPanel;
	}
	
	public JLabel getAcisalHizEtiket(){
		if (acisalHizEtiket == null) {
			acisalHizEtiket = new JLabel("--");
			
		}

		return acisalHizEtiket;
	}
	
	public JLabel getKatEdilenYolEtiket(){
		if (katedilenYolEtiket == null) {
			katedilenYolEtiket = new JLabel("--");
			
		}

		return katedilenYolEtiket;
	}
	public JLabel getHarcananZamanEtiket(){
		if (harcananZamanEtiket == null) {
			harcananZamanEtiket = new JLabel("--");
			
		}

		return harcananZamanEtiket;
	}
	
	public JLabel getBasariOraniEtiket(){
		if (basariliMiEtiket == null) {
			basariliMiEtiket = new JLabel("--");
			
		}

		return basariliMiEtiket;
	}
	public JLabel getHesaplananZamanEtiket(){
		if (hesaplananZamanEtiket == null) {
			hesaplananZamanEtiket = new JLabel("--");
			
		}

		return hesaplananZamanEtiket;
	}
	public void labellariGuncelle(AlgoritmaModel algoritmaModel){
		switch (algoritmalar) {
		case ASTAR:
			etiketleriGuncelle(algoritmaModel);
			break;
		case LEE:
			etiketleriGuncelle(algoritmaModel);
			break;
		case POTENTIAL_FIELD:
			etiketleriGuncelle(algoritmaModel);
			break;
		case RRT:
			getKatEdilenYolEtiket().setText(algoritmaModel.getRrtDeger());
			break;
		case RRTSTAR:
			getKatEdilenYolEtiket().setText(algoritmaModel.getRrtDeger());
			break;
		case DYNAMIC_WINDOWS_APPROACH:
			etiketleriGuncelle(algoritmaModel);
			break;
		default:
			break;
		}
		
	
	}
	
	private void etiketleriGuncelle(AlgoritmaModel algoritmaModel){
		getAcisalHizEtiket().setText(algoritmaModel.getAcisalHiz()+" m/ms");
		getHesaplananZamanEtiket().setText(algoritmaModel.getHesaplananZaman()+" ms");
		getHarcananZamanEtiket().setText(algoritmaModel.getHarcananZaman()+" ms");
		getKatEdilenYolEtiket().setText(algoritmaModel.getKatEdilenYol()+" metre");
	}

}

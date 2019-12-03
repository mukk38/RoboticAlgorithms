package pencereler;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;

public class FarkliKaydetIsimGirmePenceresi extends JDialog {

	private JTextField isimGirisAlani;
	private FarkliKaydetIsimGirmePenceresi farkliKaydetIsimGirmePenceresi;
	private JPanel alanGirisPanel;
	private JButton tamamDugmesi;
	private JPanel textPanel;
	private String kaydedilecekKatar;
	public FarkliKaydetIsimGirmePenceresi(String kaydedilecekScript_){
		kaydedilecekKatar=kaydedilecekScript_;
		farkliKaydetIsimGirmePenceresi = this;
		farkliKaydetIsimGirmePenceresi.setAlwaysOnTop(true);
		farkliKaydetIsimGirmePenceresi.setContentPane(getAlanGirisPanel());
		OrtakMetotlar.setBilesenBoyutu(farkliKaydetIsimGirmePenceresi, 600, 150);
	}
	
	private JPanel getAlanGirisPanel() {
		if (alanGirisPanel == null) {
			alanGirisPanel = new JPanel(new BorderLayout());
			alanGirisPanel.add(getTextPanel(),BorderLayout.CENTER);
			alanGirisPanel.add(getTamamDugmesi(),BorderLayout.SOUTH);
		}

		return alanGirisPanel;
	}

	private JButton getTamamDugmesi() {
		if (tamamDugmesi == null) {
			tamamDugmesi = new JButton("Tamam");
			tamamDugmesi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					BufferedWriter writer;
					try {
						writer = new BufferedWriter(new FileWriter(OrtakSabitler.ALGORITMA_SEC+"\\"+getIsimGirisAlani().getText()+".js"));
						writer.write(kaydedilecekKatar);
						writer.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					farkliKaydetIsimGirmePenceresi.setVisible(false);
				}
			});
		}

		return tamamDugmesi;
	}

	private JPanel getTextPanel() {
	if (textPanel == null) {
		textPanel = new JPanel(new FlowLayout());
		JLabel dizinAdi = new JLabel("Kaydedilecek Dosya Yeri "+OrtakSabitler.ALGORITMA_SEC);
		textPanel.add(dizinAdi);
		textPanel.add(getIsimGirisAlani());
		JLabel uzanti = new JLabel(".js");
		textPanel.add(uzanti);
	}

	return textPanel;
	}

	public JTextField getIsimGirisAlani(){
		if (isimGirisAlani == null) {
			isimGirisAlani = new JTextField("");
			OrtakMetotlar.setBilesenBoyutu(isimGirisAlani, 150, 30);
		}

		return isimGirisAlani;
	}
}

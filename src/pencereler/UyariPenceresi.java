package pencereler;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ortak.OrtakMetotlar;

public class UyariPenceresi extends JDialog {
private UyariPenceresi uyariPenceresi;
private JPanel icerikPanel;
private JLabel mesajEtiket;
	
	public UyariPenceresi(String mesaj){
		uyariPenceresi = this;
		OrtakMetotlar.setBilesenBoyutu(this, 100, 100);
		setAlwaysOnTop(true);
		setContentPane(getIcerikPanel());
		getMesajLabel().setText(mesaj);
	}

	private JPanel getIcerikPanel() {
		if (icerikPanel == null) {
			icerikPanel = new JPanel();
			icerikPanel.add(getMesajLabel());
		}

		return icerikPanel;
	}

	private JLabel getMesajLabel() {
		if (mesajEtiket == null) {
			mesajEtiket = new JLabel("");
			
		}

		return mesajEtiket;
	}
}

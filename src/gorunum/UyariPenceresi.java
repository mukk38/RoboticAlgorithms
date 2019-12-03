package gorunum;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Ortak.OrtakMetotlar;

public class UyariPenceresi extends JDialog {

	public UyariPenceresi(String uyari){
		setAlwaysOnTop(true);
		OrtakMetotlar.setBilesenBoyutu(this, 70, 70);
		panelOlustur(uyari);
	}

	private void panelOlustur(String Uyari) {
		JLabel uyariEtiket = new JLabel(Uyari);
		this.add(uyariEtiket);
		
	}
}

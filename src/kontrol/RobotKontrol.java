package kontrol;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public final class RobotKontrol {
	public final static String BIR_NUMARA_DOSYA_PATH= ".//external//icon//1Numara.png";
	public final static String IKI_NUMARA_DOSYA_PATH= ".//external//icon//2Numara.png";
	public final static String UC_NUMARA_DOSYA_PATH= ".//external//icon//3Numara.png";
	public final static String DORT_NUMARA_DOSYA_PATH= ".//external//icon//4Numara.png";
	public final static String BES_NUMARA_DOSYA_PATH= ".//external//icon//5Numara.png";
private RobotKontrol(){
	
}

public static JRadioButton yeniRadioButonOlustur(int sayi) {
	JRadioButton radioButton = new JRadioButton();
	radioButton.setIcon(new ImageIcon(sayidanPathGetir(sayi)));
	return radioButton;
}

private static String sayidanPathGetir(int sayi) {
	switch (sayi) {
	case 0:
		return BIR_NUMARA_DOSYA_PATH;
	case 1:
		return IKI_NUMARA_DOSYA_PATH;
	case 2:
		return UC_NUMARA_DOSYA_PATH;
	case 3:
		return DORT_NUMARA_DOSYA_PATH;
	case 4:
		return BES_NUMARA_DOSYA_PATH;
	default:
		return BIR_NUMARA_DOSYA_PATH;
		
	}
}
}

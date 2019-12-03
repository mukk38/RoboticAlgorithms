package kontrol;

public final class GridLabelKontrol {
	public final static String BIR_NUMARA_DOSYA_PATH= ".//external//icon//1Numara24.png";
	public final static String IKI_NUMARA_DOSYA_PATH= ".//external//icon//2Numara24.png";
	public final static String UC_NUMARA_DOSYA_PATH= ".//external//icon//3Numara24.png";
	public final static String DORT_NUMARA_DOSYA_PATH= ".//external//icon//4Numara24.png";
	public final static String BES_NUMARA_DOSYA_PATH= ".//external//icon//5Numara24.png";
	public final static String ALTI_NUMARA_DOSYA_PATH= ".//external//icon//6Numara24.png";
	public final static String YEDI_NUMARA_DOSYA_PATH= ".//external//icon//7Numara24.png";
	public final static String SEKIZ_NUMARA_DOSYA_PATH= ".//external//icon//8Numara24.png";
	public final static String DOKUZ_NUMARA_DOSYA_PATH= ".//external//icon//9Numara24.png";
	public final static String ON_NUMARA_DOSYA_PATH= ".//external//icon//10Numara24.png";
	
	public final static String BITIS_NOKTASI_PATH= ".//external//icon//bitisNoktasi25.png";
 private GridLabelKontrol(){
	 
 }
 public static String imageDosyaYoluGetir(int seciliIndeks){

		switch (seciliIndeks) {
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
		case 5:
			return ALTI_NUMARA_DOSYA_PATH;
		case 6:
			return YEDI_NUMARA_DOSYA_PATH;
		case 7:
			return SEKIZ_NUMARA_DOSYA_PATH;
		case 8:
			return DOKUZ_NUMARA_DOSYA_PATH;
		case 9:
			return ON_NUMARA_DOSYA_PATH;

		default:
			return BIR_NUMARA_DOSYA_PATH;
			
		}

 }
}

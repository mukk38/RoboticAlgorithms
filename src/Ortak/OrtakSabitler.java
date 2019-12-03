package Ortak;

import java.io.FileReader;

import Enum.Yonler;

public class OrtakSabitler {
	public final static String KARAKTERLER_DOSYA_DIZINI ="./external/icon/karakter";
	public final static String BITIS_NOKTASI_DOSYA_PATH= ".//external//icon//bitisNoktasiTrans.png";
	public final static String ENGEL_DOSYA_PATH= ".//external//icon//EngelYeni.jpg";
	public final static String GEZILEN_YER_DOSYA_PATH =".//external//icon//GidilenYol.png";
	public final static String GEZILEN_YER_DOSYA_PATH2 =".//external//icon//GidilenYol2.png";
	public final static String GEZILEN_YER_DOSYA_PATH3 =".//external//icon//GidilenYol3.png";
	public final static String GEZILEN_YER_DOSYA_PATH4 =".//external//icon//GidilenYol4.png";
	public final static String GEZILEN_YER_DOSYA_PATH5 =".//external//icon//GidilenYol5.png";
	public final static String GEZILEN_YER_DOSYA_PATH6 =".//external//icon//GidilenYol6.png";
	public final static String GEZILEN_YER_DOSYA_PATH7 =".//external//icon//GidilenYol7.png";
	public final static String GEZILEN_YER_DOSYA_PATH8 =".//external//icon//GidilenYol8.png";
	public final static String GEZILEN_YER_DOSYA_PATH9 =".//external//icon//GidilenYol9.png";
	public final static String GEZILEN_YER_DOSYA_PATH10 =".//external//icon//GidilenYol10.png";
	
	public final static String ROBOT_BILGI_IKON_DOSYA_YOLU = ".//external//icon//file.png";
	public final static int MAKSIMUM_ROBOT_SAYISI = 10;
	public final static int MAKSIMUM_ENGEL_SAYISI = 5;
	public final static int GRID_LABEL_BOY = 5;
	
	public final static int HAREKETLI_ENGEL_OFSET=5;
	
	public final static int ASTAR_INDEKS = 0;
	public final static int LEE_INDEKS = 1;
	public final static int POTENTIAL_FIELD_ALGORTHM_INDEKS = 2;
	public final static int PROBABILISTIC_ROAD_MAP = 3;
	public final static int RAPIDLY_EXPANDING_RANDOM_TREE = 4;
	public final static int RAPIDLY_EXPANDING_RANDOM_TREE_STAR = 5;
	public final static int DYNAMIC_WINDIW_APPROACH = 6;
	public final static Yonler VARSAYILAN_YON = Yonler.KUZEY;
	 
	public final static String ALGORITMA_SCRIPT =".\\external\\scripts\\algoritmaYeni.js";
	
	public final static String ALGORITMA_SEC =".\\external\\scripts";
	
	public final static int GRID_EN = OrtakMetotlar.getGridXsayisi();
	public final static int GRID_BOY = OrtakMetotlar.getGridYsayisi();
	
	public final static int NUM_OF_CONNECTIONS = 5;
	public final static int OPTIMISI_DISTANCE = 40;
	
	public final static String ROBOT_1_SCREEN_DOSYA_YOLU =".//external//screen//robot1//";
	public final static String ROBOT_2_SCREEN_DOSYA_YOLU =".//external//screen//robot2//";
	public final static String ROBOT_3_SCREEN_DOSYA_YOLU =".//external//screen//robot3//";
	public final static String ROBOT_4_SCREEN_DOSYA_YOLU =".//external//screen//robot4//";
	public final static String ROBOT_5_SCREEN_DOSYA_YOLU =".//external//screen//robot5//";
	public final static String ROBOT_6_SCREEN_DOSYA_YOLU =".//external//screen//robot6//";
	public final static String ROBOT_7_SCREEN_DOSYA_YOLU =".//external//screen//robot7//";
	public final static String ROBOT_8_SCREEN_DOSYA_YOLU =".//external//screen//robot8//";
	public final static String ROBOT_9_SCREEN_DOSYA_YOLU =".//external//screen//robot9//";
	public final static String ROBOT_10_SCREEN_DOSYA_YOLU =".//external//screen//robot10//";
	
	public final static String SCREEN_ANA_DOSYA_YOLU=".//external//screen//";
}

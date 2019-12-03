package Main;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jidesoft.utils.Lm;

import Ortak.GlobalDegiskenler;
import Ortak.OrtakSabitler;
import gorunum.AnaPencere;

public class Main {

	public static void main(String[] args) {
//		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels(); 
//        for (UIManager.LookAndFeelInfo look : looks) { 
//            System.out.println(look.getClassName()); 
//        }
		
		Lm.verifyLicense("Simsoft Bilgi Teknolojileri A.S.","SHU","4ORxReK0IJuiIH3uIJmJoEdzNaPjJjT");
	//	com.jidesoft.utils.lm.verifyLicense("Simsoft Bilgi Teknolojileri A.S","SHU","4ORxReK0IJuiIH3uIJmJoEdzNaPjJjT");
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		KonfigurasyonIslemler.getInstance().konfigurasyonIslemleriYap();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dizindekiTumDosyalariSil(); 
		AnaPencere anaPencere = GlobalDegiskenler.getAnaPencere();
		anaPencere.setVisible(true);
	}
	
	public static void dizindekiTumDosyalariSil(){
		File robotDizin1 = new File(OrtakSabitler.ROBOT_1_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin1);
		File robotDizin2 = new File(OrtakSabitler.ROBOT_2_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin2);
		File robotDizin3 = new File(OrtakSabitler.ROBOT_3_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin3);
		File robotDizin4 = new File(OrtakSabitler.ROBOT_4_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin4);
		File robotDizin5 = new File(OrtakSabitler.ROBOT_5_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin5);
		File robotDizin6 = new File(OrtakSabitler.ROBOT_6_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin6);
		File robotDizin7 = new File(OrtakSabitler.ROBOT_7_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin7);
		File robotDizin8 = new File(OrtakSabitler.ROBOT_8_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin8);
		File robotDizin9 = new File(OrtakSabitler.ROBOT_9_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin9);
		File robotDizin10 = new File(OrtakSabitler.ROBOT_10_SCREEN_DOSYA_YOLU);
		screenleriSil(robotDizin10);
		
	}
	public static void screenleriSil(File dizin){
		for(File file: dizin.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
	}

}

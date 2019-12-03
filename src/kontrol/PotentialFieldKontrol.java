package kontrol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import Mapping.Engel;
import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import PotentialField.Obstacle;
import PotentialField.PotentialFieldConstant;
import PotentialField.Robot;
import izlekler.PotentialFieldThread;

public final class PotentialFieldKontrol {
	private static Engel[] engeller =null;
	private static Engel[] hareketliEngeller;
	private static ArrayList<Robot> robotlar =new ArrayList<Robot>();
	private static ArrayList<Thread> threadler = new ArrayList<Thread>();
	public static void algoritmaHesaplaVeriyiCiz(Point[] robotPoint, Point[] robotHedefPoint,
			Engel[] hareketliEngelHedefleri, Engel[] engelHedefleri) {
		engeller = engelHedefleri;
		hareketliEngeller = hareketliEngelHedefleri;
		
		threadler.clear();
		sabitEngelleriKaldir();
		robotlar.clear();

		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
					&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null 
					&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
		AraclarPanelKontrol.takeSnapShot(GlobalDegiskenler.GetGridPanel(),i);
				Robot r = new Robot(robotPoint[i], herHedefIcinEngelleriGuncelle(robotHedefPoint[i],i),
						PotentialFieldConstant.ROBOT_BASLANGIC_DT, PotentialFieldConstant.ROBOT_BASLANGIC_MASS,
						PotentialFieldConstant.ROBOT_BASLANGIC_FMAX, PotentialFieldConstant.ROBOT_BASLANGIC_DIAM);
				robotlar.add(r);
				PotentialFieldThread thread = new PotentialFieldThread(r, robotHedefPoint, i);
				thread.start();
			}
			}
	}


	public static ArrayList<Obstacle> herHedefIcinEngelleriGuncelle(Point hedefNoktasi,int index) {
		Obstacle hedef = new Obstacle(hedefNoktasi, PotentialFieldConstant.HEDEF_CHARGE,
				PotentialFieldConstant.HEDEF_DIAM);
		
		 ArrayList<Obstacle> obstacleslar = new ArrayList<Obstacle>(); 
		obstacleslar.add(hedef);
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ENGEL_SAYISI; i++) {
			if (hareketliEngeller[i] != null && GlobalDegiskenler.getHareketliEngelPanelleri().get(i).isVisible()) {
				for(int j=hareketliEngeller[i].getBaslamaNoktasi().getX();j<hareketliEngeller[i].getBaslamaNoktasi().getX()+hareketliEngeller[i].getWeight();j++){
					for(int k=hareketliEngeller[i].getBaslamaNoktasi().getY();k<hareketliEngeller[i].getBaslamaNoktasi().getY()+hareketliEngeller[i].getHeight();k++){
						Point hareketliEngelNoktasi = new Point(j,k);
						obstacleslar.add(new Obstacle(hareketliEngelNoktasi));
					}
				}
			}
//			if (engeller[i] != null && GlobalDegiskenler.getEngelRadioButonlar().get(i).isVisible()) {				
//				int baslamaNoktasiX = engeller[i].getBaslamaNoktasi().getX();
//				int baslamaNoktasiY = engeller[i].getBaslamaNoktasi().getY();
//				int bitisNoktasiX = engeller[i].getBitisNoktasi().getX();
//				int bitisNoktasiY = engeller[i].getBitisNoktasi().getY();
//				for(int j =baslamaNoktasiX;j<bitisNoktasiX;j++) {
//					int a[] = {j,baslamaNoktasiY};
//					Point EngelNoktasi = new Point(j,baslamaNoktasiY);
//					obstacleslar.add(new Obstacle(EngelNoktasi));
//					Point EngelNoktasi1 = new Point(j,bitisNoktasiY);
//					obstacleslar.add(new Obstacle(EngelNoktasi1));					
//				}
//				for(int j =baslamaNoktasiY;j<bitisNoktasiY;j++) {
//					int a[] = {j,baslamaNoktasiY};
//					Point EngelNoktasi = new Point(baslamaNoktasiX,j);
//					obstacleslar.add(new Obstacle(EngelNoktasi));
//					Point EngelNoktasi1 = new Point(bitisNoktasiX,j);
//					obstacleslar.add(new Obstacle(EngelNoktasi1));					
//				}				
//			}
		}if(robotlar.size()>index){
		robotlar.get(index).setTarget(hedef);
		robotlar.get(index).setObstacle(obstacleslar);
		}
		return obstacleslar;
	}


	public static ArrayList<Robot> getRobotlar() {
		return robotlar;
	}


	public static void calismayiDurdur() {
		if(threadler.size()>0){
			for(Thread t :threadler){
				t.stop();
				t.suspend();
				t.interrupt();
			
			}
		}
		
	}
	
	public static void sabitEngelleriKaldir(){
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
	
				for (int j = 0; j < GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size(); j++) {
					GlobalDegiskenler.GetGridPanel().getGridBorder()[GlobalDegiskenler.getEngelNoktalari().get(i)
							.getNoktalar().get(j).getX()][GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar()
									.get(j).getY()].setIcon(null);
				}
			
		}
	}

	public static void virtualForceSecildi() {
		for(Robot robot :robotlar){
			robot.virtualforce = true;
		}
		
	}

	public static void virtualForceSecimKaldirildi() {
		for(Robot robot :robotlar){
			robot.virtualforce = false;
		}
		
	}

}

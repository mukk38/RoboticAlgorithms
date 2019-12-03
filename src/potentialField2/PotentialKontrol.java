package potentialField2;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import Mapping.Engel;
import Ortak.GlobalDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import PotentialField.PotentialFieldConstant;
import PotentialField.Robot;
import geometry.IntPoint;
import izlekler.PotentialFieldThread;
import kontrol.AraclarPanelKontrol;
import renderables.Renderable;
import renderables.RenderableRectangle;

public final class PotentialKontrol {

	private static Engel[] engeller =null;
	private static Engel[] hareketliEngeller;
	private static ArrayList<PotentialFieldsRobot> robotlar =new ArrayList<PotentialFieldsRobot>();
	public static ArrayList<PotentialYeniThread> threadler = new ArrayList<PotentialYeniThread>();
	public static void algoritmaHesaplaVeriyiCiz(Point[] robotPoint, Point[] robotHedefPoint,
			Engel[] hareketliEngelHedefleri, Engel[] engelHedefleri) {
		engeller = engelHedefleri;
		hareketliEngeller = hareketliEngelHedefleri;
		ArrayList<Renderable>obstacles = new ArrayList<Renderable>();
		for(Engel engel : engeller){
			if(engel!=null){
			RenderableRectangle r7 = new RenderableRectangle(engel.getBaslamaNoktasi().getX(), engel.getBaslamaNoktasi().getY(), engel.getWeight(), engel.getHeight());
			r7.setProperties(Color.DARK_GRAY, 1f, true, false);
			obstacles.add(r7);
			}
		}
		for(Engel engel : hareketliEngeller){
			if(engel!=null){
			RenderableRectangle r7 = new RenderableRectangle(engel.getBaslamaNoktasi().getX(), engel.getBaslamaNoktasi().getY(), engel.getWeight(), engel.getHeight());
			r7.setProperties(Color.DARK_GRAY, 1f, true, false);
			obstacles.add(r7);
			}
		}
		

		threadler.clear();
	//	sabitEngelleriKaldir();
		robotlar.clear();

		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
					&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null 
					&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
		AraclarPanelKontrol.takeSnapShot(GlobalDegiskenler.GetGridPanel(),i);
		
		PotentialFieldsRobot rob = new PotentialFieldsRobot( new IntPoint(robotPoint[i].x, robotPoint[i].y), new IntPoint(robotHedefPoint[i].x, robotHedefPoint[i].y), PotentialYeniSabitler.ROBOT_RADIUS, PotentialYeniSabitler.ROBOT_SENSOR_RANGE, 
				PotentialYeniSabitler.ROBOT_SENSOR_DENSITY, PotentialYeniSabitler.GOAL_RADIUS, obstacles, PotentialYeniSabitler.ROBOT_INIT_HEADING);
		
				robotlar.add(rob);
				PotentialYeniThread thread = new PotentialYeniThread(rob, robotHedefPoint, i);
				threadler.add(thread);
				thread.start();
			}
			}
	}
	public static void obstacleDegisti() {
		hareketliEngeller = GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri();
		ArrayList<Renderable>obstacles = new ArrayList<Renderable>();
		for(Engel engel : hareketliEngeller){
			if(engel!=null){
			RenderableRectangle r7 = new RenderableRectangle(engel.getBaslamaNoktasi().getX(), engel.getBaslamaNoktasi().getY(), engel.getWeight(), engel.getHeight());
			r7.setProperties(Color.DARK_GRAY, 1f, true, false);
			obstacles.add(r7);
			}
		}
		for(PotentialFieldsRobot robot :robotlar){
			robot.obstacleDegisti(obstacles);
		}
		
	}
	
}

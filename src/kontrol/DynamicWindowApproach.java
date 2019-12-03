package kontrol;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import Mapping.Engel;
import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import Ortak.OrtakSabitler;
import PotentialField.PotentialFieldConstant;
import PotentialField.Robot;
import dwa2.RobotState;
import dwa2.Sabitler;
import izlekler.DynamicWindowsApproachThread;
import izlekler.PotentialFieldThread;

public class DynamicWindowApproach {
	
	private static Engel[] engeller =null;
	private static Engel[] hareketliEngeller;
	private static ArrayList<RobotState> robotlar =new ArrayList<RobotState>();
	private static ArrayList<Thread> threadler = new ArrayList<Thread>();
	public static void algoritmaHesaplaVeriyiCiz(Point[] robotPoint, Point[] robotHedefPoint,
			Engel[] hareketliEngelHedefleri, Engel[] engelHedefleri) {
		engeller = engelHedefleri;
		hareketliEngeller = hareketliEngelHedefleri;
		threadler.clear();

		robotlar.clear();
		KureselDegiskenler.getDynamicWindowsPathler().clear();
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
					&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null 
					&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
				System.out.println("RobotStateler Tanýmlandi");
				Vector<RobotState> path = new Vector<RobotState>();
				KureselDegiskenler.getDynamicWindowsPathler().add(path);
				System.out.println("BaslamaX "+ GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi().getX()+" BaslamaY "+GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi().getY()
						+"BitisX "+GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi().getX()+" BitisY "+GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi().getY());
		//		RobotState currentState = new RobotState( GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi().getX(),  GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi().getY(),Sabitler.M_PI / 2, 0, 0 );
				RobotState currentState = new RobotState( robotPoint[i].x,  robotPoint[i].y,Sabitler.M_PI / 2, 0, 0 );
				currentState.bitisXPosition =robotHedefPoint[i].x;
				currentState.bitisYPosition = robotHedefPoint[i].y;
				robotlar.add(currentState);
				int goal[] = { currentState.bitisXPosition, currentState.bitisYPosition};
				DynamicWindowsApproachThread thread = new DynamicWindowsApproachThread(robotlar,i,goal);
				System.out.println("Threadler baslatiliyor");
				thread.start();
			}
			}
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
	public static ArrayList<RobotState> getRobotlar() {
		return robotlar;
	}

}

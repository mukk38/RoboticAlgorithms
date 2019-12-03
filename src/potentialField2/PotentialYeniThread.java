package potentialField2;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import PotentialField.Obstacle;
import PotentialField.Robot;
import kontrol.GridPanelKontrol;
import kontrol.PotentialFieldKontrol;
import model.AlgoritmaModel;
import model.Modeller;
import renderables.RenderablePolyline;

public class PotentialYeniThread extends Thread {
	private PotentialFieldsRobot rob;
	private Point[] robotHedefPoint;
	private  int index;
	public RenderablePolyline path = new RenderablePolyline();
	public ArrayList<Point> yollar = new ArrayList<>();
	public PotentialYeniThread(PotentialFieldsRobot r_,Point[] robotHedefPoint_,int index_){
		rob=r_;
		robotHedefPoint=robotHedefPoint_;
		index = index_;
	}
	@Override
	public void run() {
		int l=0;
		//Loop until the robot reaches the goal or gets stuck
		while (!rob.inGoal()) {
			try {
				Thread.sleep(1000/PotentialYeniSabitler.ROBOT_SPEED);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean move = rob.move(false); //Move 1 step
			
			//If move==false then rob has crashed
			if(move == false) {
				break; //Stop if no actions are available
			}
		//	synchronized(yollar){
			yollar.add(new Point(rob.getPosition().x, rob.getPosition().y));
		//	}
			//Draw the path from start to Rob's position
			path.addPoint(rob.getPosition().x, rob.getPosition().y);
			GlobalDegiskenler.GetGridPanel().getRobotPoint()[index] = new Point(rob.getPosition().x, rob.getPosition().y);
			GlobalDegiskenler.GetGridPanel().revalidate();
			GlobalDegiskenler.GetGridPanel().repaint();

		}
		
	}

}

package izlekler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.SwingUtilities;

import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import PotentialField.Robot;
import dwa2.Dwa;
import dwa2.GlobalDegisken;
import dwa2.RobotState;
import dwa2.Sabitler;
import kontrol.AraclarPanelKontrol;
import kontrol.GridPanelKontrol;
import model.AlgoritmaModel;
import model.Modeller;

public class DynamicWindowsApproachThread extends Thread {
	private ArrayList<RobotState> robot;
	public int indeks;
	Vector<RobotState> path = new Vector<RobotState>();
	private int goal[];
	public DynamicWindowsApproachThread(ArrayList<RobotState> robot_, int indeks_,int goal_[]) {
		robot = robot_;
		indeks = indeks_;
		goal= goal_;
	}

	@Override
		public void run() {
		Long baslamaZamani = System.currentTimeMillis();
		 while (KureselDegiskenler.isDynamicWindowsApproachBaslatidi())
			{
									// ?????????
				if (robot.get(indeks).xPosition < (goal[0] + 0.1) && robot.get(indeks).xPosition > (goal[0] - 0.1) && robot.get(indeks).yPosition < (goal[1] + 0.1) && robot.get(indeks).yPosition > (goal[1] - 0.1))

					{

						break;
					}

					Vector<Double> selectedVelocity = Dwa.DynamicWindowApproach(robot.get(indeks), goal);

					// ?????
					robot.set(indeks, Dwa.Motion(robot.get(indeks), selectedVelocity.get(0), selectedVelocity.get(1)));
					path.add(robot.get(indeks));
				//	System.out.println(" X Position "+robot.xPosition+" Y position "+robot.yPosition);
				//	KureselDegiskenler.getDynamicWindowsPathler().get(indeks).add(robot);
				//	System.out.println("VELOCITY "+robot.get(indeks).velocity+" Orientation "+robot.get(indeks).orientation+" Omega "+robot.get(indeks).omega+" X "+robot.get(indeks).xPosition+" Y "+robot.get(indeks).yPosition);
					GlobalDegiskenler.GetGridPanel().revalidate();
					GlobalDegiskenler.GetGridPanel().repaint();
					try {
						Thread.sleep(1);
					} catch (InterruptedException ex) {
						System.out.println(ex);
					}
				}
		 Long bitisZamani = System.currentTimeMillis();
		 int pathSize = GridPanelKontrol.getDynamicWindowsYollar().get(indeks).size();
		 AraclarPanelKontrol.takeSnapShot(GlobalDegiskenler.GetGridPanel(), indeks);
		 Long gecenZaman =(bitisZamani-baslamaZamani);
		 double acisalHiz =(double)pathSize/gecenZaman;
		 Modeller.dynamicWindowsApproachRobotModel.get(indeks).getAlgoritmaModel().add(new AlgoritmaModel(bitisZamani-baslamaZamani,pathSize,acisalHiz));
				System.out.println("Bitti");

		
			}
	 }


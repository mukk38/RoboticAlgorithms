package izlekler;

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

public class PotentialFieldThread extends Thread {
	private Robot r;
	private Point[] robotHedefPoint;
	private  int index;
	 public PotentialFieldThread(Robot r_,Point[] robotHedefPoint_,int index_) {
		r=r_;
		robotHedefPoint=robotHedefPoint_;
		index = index_;
	}
	
	@Override
	public void run() {
		long hesaplananBaslangicZamani =System.currentTimeMillis();
		while (hedefNoktasiKontrolEt(r.x,r.y,robotHedefPoint[index])&&KureselDegiskenler.isPotentialFieldBaslatidi()) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					r.updatePosition();
					ArrayList<Obstacle> obstacles=		PotentialFieldKontrol.herHedefIcinEngelleriGuncelle(robotHedefPoint[index],index);
				r.setObstacle(obstacles);
					GlobalDegiskenler.GetGridPanel().revalidate();
					GlobalDegiskenler.GetGridPanel().repaint();
					
				}
			});
		
			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				System.out.println(ex);
			}
		}
		long hesaplananBitisZamani =System.currentTimeMillis();
		double acisalHiz = (double)(GridPanelKontrol.getPotentialFieldYollar().get(index).size())/(hesaplananBitisZamani-hesaplananBaslangicZamani);
		Modeller.potentialFieldRobotModel.get(index).getAlgoritmaModel().add(new AlgoritmaModel(hesaplananBitisZamani-hesaplananBaslangicZamani,GridPanelKontrol.getPotentialFieldYollar().get(index).size(),acisalHiz));
	}
	
	protected  boolean hedefNoktasiKontrolEt(double x, double y, Point hedefNoktasi) {
		if((x>(hedefNoktasi.getX()-20)&&x<(hedefNoktasi.getX()+20))&&(y>(hedefNoktasi.getY()-20)&&y<(hedefNoktasi.getY()+20))){
			return false;
		}
		return true;
	}
}

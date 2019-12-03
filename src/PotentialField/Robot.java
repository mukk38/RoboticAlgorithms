package PotentialField;

import java.util.*;
import java.awt.*;

import java.awt.*;
import java.util.*;

public class Robot {
    public double x;
	public double y;
 public   double vx, vy;
    double dt;
    double m;
    double fMax;
    ArrayList obstacles;
    public double diam;
    Obstacle target;
   public boolean virtualforce = false;
   private Robot robot;
    public Robot(Point p, ArrayList obstacles, double dt, double m, double fMax, double diam) {
    	robot = this;
        this.diam = diam;
        this.fMax = fMax;
        this.m = m;
        this.dt = dt;
        vx = vy = 0;
        this.x = p.x;
        this.y = p.y;
        this.obstacles = obstacles;
        this.target = (Obstacle) obstacles.get(0);
 //       this.target = hedef;
    }

    public synchronized void updatePosition() {
        double dirX = 0, dirY = 0;
        double minS = 200;
        Iterator iter = obstacles.iterator();

        while (iter.hasNext()) {
            Obstacle ob = (Obstacle) iter.next();
            double distSq =ob.distanceSq(robot);
       //     System.out.println("Dist sq "+distSq);
            if (distSq < 1)
                Math.sin(1);
            double dx = ob.charge * (ob.p.x - x) / distSq;
            double dy = ob.charge * (ob.p.y - y) / distSq;
            dirX += dx;
            dirY += dy;
     //       System.out.println("DirX "+dirX+" DirY "+dirY+" Dx "+dx+" Dy "+dy);
        }

        double norm = Math.sqrt(dirX*dirX+dirY*dirY);
        dirX = dirX / norm;
        dirY = dirY / norm;
     //   System.out.println("Norm "+norm+" DirX "+dirX+" DirY "+dirY);
        iter = obstacles.iterator();
        while (iter.hasNext()) {
            Obstacle ob = (Obstacle) iter.next();
       //     System.out.println("Range Obstacle "+ob.distanceSq(this));
            if(!range(ob, 4000)) continue;
            double distSq =ob.distanceSq(this);
       //     System.out.println("DistSq "+distSq);
            double dx = (ob.p.x - x);
            double dy = (ob.p.y - y);
     //       System.out.println("Obstacle dx "+dx+" dy "+dy);
            //add normal noise to simulate the sonar effect
            dx = addNoise(dx, 0, 1);
            dy = addNoise(dy, 0 ,1);
          
            double safety = distSq / ((dx * dirX+dy*dirY));
   //         System.out.println("Safety "+safety+"Obstacle2 dx "+dx+" dy "+dy);
            if ((safety > 0) &&(safety < minS)){
                minS = safety;
     //       System.out.println("minS=safety");
            }
        }
        if (minS < 5) {
            double oc = target.charge;
            target.charge*=minS/5;
        //    System.out.println(oc +" DOWN TO "+ target.charge);
        }

        if (minS > 100) {
            double oc = target.charge;
            target.charge*=minS/100;
         //   System.out.println(oc +" UP TO "+ target.charge);
        }


        double vtNorm = minS/2;
        double vtx = vtNorm * dirX;
        double vty = vtNorm * dirY;
        double fx = m * (vtx - vx) / dt;
        double fy = m * (vty - vy) / dt;
        double fNorm =  Math.sqrt(fx * fx + fy * fy);
     //   System.out.println("Vt Norm "+vtNorm+" vtx "+vtx+" vty "+vty+" Fx "+fx+" Fy "+fy+" FNorm "+fNorm+" FMax "+fMax);
        if (fNorm > fMax ) {
            fx *=  fMax / fNorm;
            fy *=  fMax / fNorm;
       //     System.out.println("fNorm > fMax "+" Fx "+fx+" Fy "+fy);
        }
        vx += (fx * dt) / m;
        vy += (fy * dt) / m;
        //virtual force component        
        if(virtualforce && (target.charge < 1000) && (x > 25) && (y > 25)) {
          System.out.println("Virtual Force");
          target.charge*=minS/100;
          
            vx = vx + 5;
        }
    //    System.out.println("Vx "+vx+" Vy "+vy);
        x += vx * dt;
        y += vy * dt;
    //    System.out.println("X "+x+" Y "+y);
    }
    
    boolean range(Obstacle ob, double range) {
       double dist =ob.distanceSq(this);
       if(dist < range)
         return true;
       else 
         return false;
    }
    
    double addNoise(double x, double mean, double stddev) {
      Random r = new Random();
      double noise = stddev*r.nextGaussian() + mean;
      return x + noise;
    }
    public void setTarget(Obstacle hedef){
    	this.target = hedef;
    }

	public void setObstacle(ArrayList engeller) {
		obstacles =engeller;
		
	}
}

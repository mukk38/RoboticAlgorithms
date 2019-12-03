package PotentialField;

import java.awt.Point;

public final  class PotentialFieldConstant {

	private PotentialFieldConstant(){
		
	}
	public static final int OBSTACLE_MASS = 20;
	public static final int OBSTACLE_CHARGE = 20;
	public static final int OBSTACLE_DIAM = 9;
	public static final int ROBOT_FMAX =40000;
	public static final int ROBOT_DIAM = 1500;
	public static final int ROBOT_MASS =4000;
	public static final double ROBOT_DT = 0.1;
	
	public static final Point ROBOT_BASLANGIC_NOKTASI = new Point(800, 600);
	public static final double ROBOT_BASLANGIC_DT = 0.1;
	public static final double ROBOT_BASLANGIC_FMAX = 4000;
	public static final double ROBOT_BASLANGIC_DIAM = 15;
	public static final double ROBOT_BASLANGIC_MASS = 40;
	
	public static final Point HEDEF_NOKTA =new Point(0,0);
	public static final double HEDEF_CHARGE = 1550000;
	public static final double HEDEF_DIAM = 1;
}

package model;

import java.util.ArrayList;

import Ortak.OrtakSabitler;

public class Modeller {
	public static ArrayList<RobotModel> aStarRobotModel = new ArrayList<RobotModel>();
	public static ArrayList<RobotModel> leeRobotModel = new ArrayList<RobotModel>();
	public static ArrayList<RobotModel> potentialFieldRobotModel = new ArrayList<RobotModel>();
	public static ArrayList<RobotModel> rrtRobotModel = new ArrayList<RobotModel>();
	public static ArrayList<RobotModel> rrtStarRobotModel = new ArrayList<RobotModel>();
	public static ArrayList<RobotModel> dynamicWindowsApproachRobotModel = new ArrayList<RobotModel>();
	static{
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			RobotModel robotModel = new RobotModel(i);
			aStarRobotModel.add(robotModel);
		}
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			RobotModel robotModel = new RobotModel(i);
			leeRobotModel.add(robotModel);
		}
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			RobotModel robotModel = new RobotModel(i);
			potentialFieldRobotModel.add(robotModel);
		}
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			RobotModel robotModel = new RobotModel(i);
			rrtRobotModel.add(robotModel);
		}
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			RobotModel robotModel = new RobotModel(i);
			rrtStarRobotModel.add(robotModel);
		}
		for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
			RobotModel robotModel = new RobotModel(i);
			dynamicWindowsApproachRobotModel.add(robotModel);
		}
		
	}

	
	

}

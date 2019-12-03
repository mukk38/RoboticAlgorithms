package model;

import java.util.ArrayList;

public class RobotModel {

	private int robotIndeks;
	private ArrayList<AlgoritmaModel> algoritmaModel = new ArrayList<AlgoritmaModel>();
	
	public RobotModel(int indeks_){
		robotIndeks = indeks_;
	}
	
	public int getRobotIndeks() {
		return robotIndeks;
	}
	public void setRobotIndeks(int robotIndeks) {
		this.robotIndeks = robotIndeks;
	}
	public ArrayList<AlgoritmaModel> getAlgoritmaModel() {
		return algoritmaModel;
	}
	public void setAlgoritmaModel(ArrayList<AlgoritmaModel> algoritmaModel) {
		this.algoritmaModel = algoritmaModel;
	}
}

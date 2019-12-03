package dwa2;
public class RobotState {
	public RobotState(){
		
	}
	
	public RobotState(double xPos,double yPos,double orien,double velocity_,double omega_){
		 xPosition =xPos;
		 yPosition = yPos;
		 orientation=orien;
		 velocity = velocity_;
		 omega = omega_;
	}
 public	double xPosition, yPosition, orientation, velocity, omega;
 public int bitisXPosition,bitisYPosition;

}

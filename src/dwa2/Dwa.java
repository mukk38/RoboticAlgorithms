package dwa2;
import java.util.Vector;


public class Dwa {

	public static void main(String[] args){
	// ???????????
	RobotState currentState = new RobotState( Sabitler.BASLAMA_NOKTASI_X, Sabitler.BASLAMA_NOKTASI_Y,Sabitler.M_PI / 2, 0, 0 );
	int goal[] = { Sabitler.GOAL_X, Sabitler.GOAL_Y };
	Vector<RobotState> path = new Vector<RobotState>();

	while (true)
	{
		// ?????????
		if (currentState.xPosition < (goal[0] + 0.1) && currentState.xPosition > (goal[0] - 0.1) && currentState.yPosition < (goal[1] + 0.1) && currentState.yPosition > (goal[1] - 0.1))
		{

			break;
		}
		Vector<Double> selectedVelocity = DynamicWindowApproach(currentState, goal);

		// ?????
		currentState = Motion(currentState, selectedVelocity.get(0), selectedVelocity.get(1));

		path.add(currentState);

	}


//	ofstream file("map.txt");
	for (int i=0;i<path.size();i++)
	{
		System.out.println("X Position "+path.get(i).xPosition+" Y Position "+path.get(i).yPosition+" Velocity "+path.get(i).velocity);
	//	file << i->xPosition << " " << i->yPosition << endl;
	}
//	file.close();

//	system("pause");


	}
	
public static Vector<Double> DynamicWindowApproach(RobotState rState, int target[])
{
	// 0:minVelocity, 1:maxVelocity, 2:minOmega, 3:maxOmega
	Vector<Double> velocityAndOmegaRange = CreateDW(rState);
	Vector<EvaluationPara>evalParas = new Vector<EvaluationPara>();
	double sumHeading = 0;
	double sumClearance = 0;
	double sumVelocity = 0;

	for (double v = velocityAndOmegaRange.get(0); v < velocityAndOmegaRange.get(1); v += Sabitler.SAMPLING_VELOCITY)
	{
		for (double w = velocityAndOmegaRange.get(2); w < velocityAndOmegaRange.get(3); w += Sabitler.SAMPLING_OMEGA)
		{
			Vector<RobotState> trajectories = GenerateTraj(rState, v, w);

			//????
			EvaluationPara tempEvalPara = new EvaluationPara();
			double tempClearance = CalcClearance(trajectories.lastElement(), GlobalDegisken.obstacle);
			double stopDist = CalcBreakingDist(v);
		//	System.out.println("TempClearance "+tempClearance+" Stop Dist "+stopDist+" Sum Velocity "+sumVelocity);
			if (tempClearance > stopDist)
			{
				
				tempEvalPara.heading = CalcHeading(trajectories.lastElement(), target);
				tempEvalPara.clearance = tempClearance;
				tempEvalPara.velocity = Math.abs(v);
				tempEvalPara.v = v;
				tempEvalPara.w = w;
			//	System.out.println("Engelden Uzak  Heading "+tempEvalPara.heading);
				sumHeading = sumHeading + tempEvalPara.heading;
				sumClearance = sumHeading + tempEvalPara.clearance;
				sumVelocity = sumVelocity + tempEvalPara.velocity;

				evalParas.add(tempEvalPara);
			}else{
				//System.out.println("Engele Yakýn");
			}
		}
	}

	//?????????????
	double selectedVelocity = 0;
	double selectedOmega = 0;
	double G = 0;
//	System.out.println("EVAL PARAS "+evalParas.size());
	for (int i=0;i<evalParas.size(); i++)
	{
		double smoothHeading = evalParas.get(i).heading / sumHeading;
		double smoothClearance = evalParas.get(i).clearance / sumClearance;
		double smoothVelocity = evalParas.get(i).velocity / sumVelocity;
		if(sumVelocity==0){
			smoothVelocity = 0;
		}

		double tempG = Sabitler.WEIGHT_HEADING*smoothHeading + Sabitler.WEIGHT_CLEARANCE*smoothClearance + Sabitler.WEIGHT_VELOCITY*smoothVelocity;
	//	System.out.println("TEMPG "+tempG+" G "+G+" Sum Heading "+sumHeading+" Sum Clearance "+sumClearance+" Sum Velocity "+sumVelocity);
		if (tempG > G)
		{
			G = tempG;
			selectedVelocity = evalParas.get(i).v;
			selectedOmega = evalParas.get(i).w;
		}
	}

//	System.out.println("Selected Velocity "+selectedVelocity);
//	System.out.println("Selected Omega "+selectedOmega);
	Vector<Double> selVelocity = new Vector<Double>();
	selVelocity.add(selectedVelocity);
	selVelocity.add(selectedOmega);

	return selVelocity;
}
static Vector<Double> CreateDW(RobotState curState)
{
	Vector<Double> dw =new Vector<Double>();
	double tmpMinVelocity = curState.velocity - Sabitler.MAX_ACCELERATE*Sabitler.DT;
	double tmpMaxVelocity = curState.velocity + Sabitler.MAX_ACCELERATE*Sabitler.DT;
	double tmpMinOmega = curState.omega - Sabitler.MAX_ACCOMEGA*Sabitler.DT;
	double tmpMaxOmega = curState.omega + Sabitler.MAX_ACCOMEGA*Sabitler.DT;

	dw.add(tmpMinVelocity > Sabitler.MIN_VELOCITY ? tmpMinVelocity : Sabitler.MIN_VELOCITY);
	dw.add(tmpMaxVelocity < Sabitler.MAX_VELOCITY ? tmpMaxVelocity : Sabitler.MAX_VELOCITY);
	dw.add(tmpMinOmega);
	dw.add(tmpMaxOmega < Sabitler.MAX_OMEGA ? tmpMaxOmega : Sabitler.MAX_OMEGA);

	return dw;
}

public static RobotState Motion(RobotState curState, double velocity, double omega)
{
	RobotState afterMoveState = new RobotState();

	//if (omega != 0)
	//{
	//	afterMoveState.xPosition = curState.xPosition + velocity / omega*sin(curState.orientation)
	//		- velocity / omega*sin(curState.orientation + omega*DT);

	//	afterMoveState.yPosition = curState.yPosition - velocity / omega*cos(curState.orientation)
	//		- velocity / omega*cos(curState.orientation + omega*DT);
	//}
	//else
	//{
	//	afterMoveState.xPosition = curState.xPosition + velocity*cos(curState.orientation)*DT;

	//	afterMoveState.yPosition = curState.yPosition + velocity*sin(curState.orientation)*DT;
	//}

	afterMoveState.xPosition = curState.xPosition + velocity*Sabitler.DT*Math.cos(curState.orientation);
	afterMoveState.yPosition = curState.yPosition + velocity*Sabitler.DT*Math.sin(curState.orientation);
//System.out.println("XPosition "+afterMoveState.xPosition+" YPosition "+afterMoveState.yPosition);
	afterMoveState.orientation = curState.orientation + omega * Sabitler.DT;
	afterMoveState.velocity = velocity;
	afterMoveState.omega = omega;	

	return afterMoveState;
}
static Vector<RobotState> GenerateTraj(RobotState initState, double vel, double ome)
{
	RobotState tempState = initState;
	Vector<RobotState> trajectories = new Vector<RobotState>();
	float time = 0;
	
	trajectories.add(initState);
	while (time < Sabitler.PREDICT_TIME)
	{
		tempState = Motion(tempState, vel, ome);
		trajectories.add(tempState);
		time += Sabitler.DT;
	}

	return trajectories;
}

static double CalcHeading(RobotState rState, int goal[])
{
	double heading;

	double dy = goal[1] - rState.yPosition;
	double dx = goal[0] - rState.xPosition;

	double goalTheta = Math.atan2(dy, dx);
	double targetTheta;
	if (goalTheta > rState.orientation)
	{
		targetTheta = goalTheta - rState.orientation;
	}
	else
	{
		targetTheta = rState.orientation - goalTheta;
	}

	heading = 180 - targetTheta / Sabitler.M_PI * 180;

	return heading;
}

static double CalcClearance(RobotState rState, int obs[][])
{
	double dist = 100;
	double distTemp;
	for (int i = 0; i < obs.length; i++)
	{

		double dx = rState.xPosition - obs[i][0];
		double dy = rState.yPosition - obs[i][1];
		distTemp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2)) - Sabitler.ROBOT_RADIUS;
	
		if (dist > distTemp)
		{
			dist = distTemp;
		}
	}
	if (dist >= 2 * Sabitler.ROBOT_RADIUS)
	{
		dist = 2 * Sabitler.ROBOT_RADIUS;
	}
	return dist;
}

static double CalcBreakingDist(double velo)
{
	double stopDist = 0;
	while (velo > 0)
	{
		stopDist = stopDist + velo*Sabitler.DT;
		velo = velo - Sabitler.MAX_ACCELERATE*Sabitler.DT;
	}

	return stopDist;
}

}










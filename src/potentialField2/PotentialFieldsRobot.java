package potentialField2;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import geometry.IntPoint;
import renderables.*;

public class PotentialFieldsRobot {

	private final RenderableImg robotPic; //Renderable image of the robot
	private final RenderablePoint robotPicAlt; //Default image if no picture is provided
	private IntPoint coords; //Position of robot
	private double heading; //Robot's heading in radians
	private final int radius; //Size of the robot (Our robot is a circle)
	private final int sensorRange; //Range of sensors
	private final int stepSize; //How far the robot moves each step, in pixels
	private final int sensorDensity; //Number of 'lines' the robot uses to see
	private IntPoint goal;
	private int goalRadius;
	private List<Renderable> obstacles; //All of the obstacles on the map
	private List<IntPoint> visibleObstacles=new ArrayList<IntPoint>();
	private  int sampleSize;
	private final int sampleSizeDefault;
	private Arc sampleArc;
	private Arc currArc;
	private Arc[] arcPair;
	private static final int FIRSTARC = 0;
	private static final int SECONDARC = 1;
	public static int movesMade = 0;

	// function used for printing path
	public LinkedList<IntPoint> getArcPoints() {
		LinkedList<IntPoint> points = new LinkedList<>();
		if (sampleArc != null) {
			points.add(sampleArc.getStart());
			points.add(sampleArc.getEnd());
		}
		if (arcPair != null) {
			points.add(arcPair[FIRSTARC].getStart());
			points.add(arcPair[FIRSTARC].getEnd());
			points.add(arcPair[SECONDARC].getStart());
			points.add(arcPair[SECONDARC].getEnd());
		}

		return points;
	}

	/**
	 * Set the robot moving towards a goal on the screen with set radius, step size, etc.
	 * @param imagePath The on-disk location of the image to use for the robot, or null for default
	 * @param startingLocation The coordinates of the starting point
	 * @param goalLocation The coordinates of the goal
	 * @param radius The radius of the robot
	 * @param sensorRange How far the robot can 'see'
	 * @param sensorDensity The number of sensor lines the robot can use
	 * @param goalRadius The width of the goal
	 * @param obstacles A list of all the obstacles on the map 
	 * */
	public PotentialFieldsRobot( IntPoint startingLocation, IntPoint goalLocation, int radius, 
			int sensorRange, int sensorDensity, int goalRadius, List<Renderable>obstacles, double initHeading) {
			robotPic = null;
			robotPicAlt = new RenderablePoint(startingLocation.x, startingLocation.y);
			robotPicAlt.setProperties(Color.RED, (float)radius*2);
		
		this.coords = new IntPoint(startingLocation.x, startingLocation.y);
//		heading = calculateHeading(goalLocation);
		heading = initHeading;
		this.radius = radius;
		this.sensorRange = sensorRange;
		this.sensorDensity = sensorDensity;
		this.stepSize = 10;
		this.sampleSizeDefault = 2*radius;
		this.goal = goalLocation;
		this.goalRadius = goalRadius;
		this.obstacles = obstacles;
	}


	/**
	 * Move the robot 1 step towards the goal (point of least potential resistance)
	 * @return True if the move is successful, false if there are no viable moves. 
	 **/
	public boolean move(boolean arcs) {
		IntPoint moveTo = evaluateSamplePoints(); //Pick a sample point to move towards
		if (moveTo == null) return false;
//		IntPoint makeMove = evaluateMovePoints(moveTo); //Find the best move point using current sample as goal
		IntPoint makeMove = evaluateSensorPoints(moveTo); //Find the best move point using current sample as goal
		if (makeMove == null) return false;
		double newHeading;
		if(arcs) {
//			double backUpHeading = heading;
		//	System.out.println("backUpHeading = " + Math.toDegrees(backUpHeading));
			newHeading = calculateHeadingArcs(makeMove);
		//	System.out.println("newHeading = " + Math.toDegrees(newHeading));
			moveTowards(newHeading); //Make the move
//			heading = backUpHeading + 2*currArc.getGama();
		//	System.out.println("heading = " + Math.toDegrees(heading));
	//		System.out.println();
		}// check if point is actually further than goal ??
//		if(arcs) {
//		    IntPoint bestPoint = evaluateSensorPoints(goal);
//            System.out.println("bestPoint = "+bestPoint);
//            newHeading = calculateHeadingArcs(bestPoint);
//        }
        else {
			newHeading = calculateHeading(makeMove);
			moveTowards(newHeading); //Make the move
		}
		movesMade++;
		return true;
	}

	/**
	 * Evaluate all of the robot's potential movement positions & return the best.
	 * @return The most valuable point
	 */
	private IntPoint evaluateSamplePoints() {
		List<IntPoint>moves = getSamplePoints();
		//If there's no moves that don't go through obstacles, quit
		if(moves.size() == 0) {
			return null;
		}
		//Value of moves is a function of distance from goal & distance from detected objects
		double[]moveValues = new double[moves.size()];
		for(int i=0;i<moves.size();i++)  {
			moveValues[i] = evalMove(moves.get(i), this.goal);
		}
		return moves.get(minIndex(moveValues)); //Return the lowest valued move
	}
	
	/**
	 * Evaluate all of the robot's potential movement positions & return the best.
	 * @return The most valuable point
	 */
	private IntPoint evaluateMovePoints(IntPoint goal) {
		List<IntPoint>moves = getMoveablePoints();
		//If there's no moves that don't go through obstacles, quit
		if(moves.size() == 0) {
			return null;
		}
		//Value of moves is a function of distance from goal & distance from detected objects
		double[]moveValues = new double[moves.size()];
		for(int i=0;i<moves.size();i++)  {
			moveValues[i] = evalMove(moves.get(i), goal);
		}
		return moves.get(minIndex(moveValues)); //Return the lowest valued move
	}

	private IntPoint evaluateSensorPoints(IntPoint goal) {
		List<IntPoint>moves = getSensorablePoints();
		//If there's no moves that don't go through obstacles, quit
		if(moves.size() == 0) {
			return null;
		}
		//Value of moves is a function of distance from goal & distance from detected objects
		double[]moveValues = new double[moves.size()];
		for(int i=0;i<moves.size();i++)  {
			moveValues[i] = evalMove(moves.get(i), goal);
		}
		return moves.get(minIndex(moveValues)); //Return the lowest valued move
	}

	/**
	 * Get the potential field at point p. The lower the value returned, the better the point is as a move.
	 * @param p The point to evaluate
	 * @return The value of the point
	 */
	private double evalMove(IntPoint p, IntPoint goal) {
		//Get distances to goal & all visible objects
		double goalDist = (distance(p, goal)-radius) / 10; //Everything is divided by 10 because otherwise the numbers get too big
		double[] obsDists = new double[visibleObstacles.size()];
		for(int i=0;i<visibleObstacles.size();i++) {
			//Distance is set to 0 if it's closer than the radius to the obstacle
			obsDists[i] = (distance(p, visibleObstacles.get(i)) - radius) <= 0 ? 0 : (distance(p, visibleObstacles.get(i)) - radius) / 10;
		}
		//Calculate field power - x^2 so value gets small as distance decreases
		double goalField = Math.pow(goalDist, 2);
		//obs. field power is sum of all obstacles, and gets v. large as distance decreases and vice versa
		double obsField = 0;
		for(int i=0;i<visibleObstacles.size();i++) {
			if(obsDists[i] <= 0) {
				obsField = Double.MAX_VALUE;
				break;
			} else if (obsDists[i] > sensorRange) {
				continue;
			}
			obsField += Math.pow(Math.E, -1 / ((sensorRange) - obsDists[i])) / (obsDists[i]);
		}
		return 10*goalField + Math.pow(2*radius,2)*4750*obsField / (sensorDensity*sensorRange);

	}

	/**
	 * Get all of the points the robot can move to - the robot moves 10 pixels forward, 
	 * and can turn upto 12 degrees in either direction to simulate continuous movement.
	 */
	public List<IntPoint> getMoveablePoints() {
		List<IntPoint> moveablePoints = new ArrayList<IntPoint>(5);
		double angleBetween = Math.toRadians(3);
		double currentAngle = mod(heading-Math.toRadians(12), 2*Math.PI);
		for(int i=0;i<9;i++) { 
			//Only make this a 'moveable' point if it does not touch an obstacle
			Line2D.Double line = new Line2D.Double();
			IntPoint p2 = getPointTowards(currentAngle, stepSize);
			line.setLine(coords.x, coords.y, p2.x, p2.y);
			//Check if this line intersects an obstacle, and if so, don't add it
			boolean crash = false;
			for(IntPoint p : visibleObstacles) {
				if (distance(p, p2) <= radius) {
					crash = true;
				}
			}
			if(intersects(line) == null && !crash) {
				moveablePoints.add(p2);
			}
			currentAngle = mod((currentAngle+angleBetween), 2*Math.PI);
		}
		return moveablePoints;
	}
	
	/**
	 * Get a list of all the sample points evenly distributed in a 180-degree arc in front of the robot 
	 **/
	public List<IntPoint> getSamplePoints() {
		List<IntPoint> moveablePoints = new ArrayList<IntPoint>(sensorDensity);
		double angleBetween = Math.PI / (sensorDensity-1);
		double currentAngle = mod(heading-Math.PI/2, 2*Math.PI);
		sampleSize = distanceToClosestObstacle(); //Sample size changes based on closest obstacle
		for(int i=0;i<sensorDensity;i++) {
			//Only make this a 'moveable' point if it does not touch an obstacle
			Line2D.Double line = new Line2D.Double();
			IntPoint p2 = getPointTowards(currentAngle, sampleSize);
			line.setLine(coords.x, coords.y, p2.x, p2.y);
			if(intersects(line) == null) 
				moveablePoints.add(p2);
			currentAngle+=angleBetween;
		}
		return moveablePoints;
	}

	/**
	 * Get all of the points the robot can move to - the number is equal to the robot's sensor density
	 * spread equally in a 180 degree arc in front of the robot. Additionally, calculate if a sensor
	 * hits an obstacle and make a note of the collision point
	 **/
	public List<IntPoint>getSensorablePoints() {
		List<IntPoint> sensorablePoints = new ArrayList<IntPoint>(sensorDensity);
		visibleObstacles = new ArrayList<IntPoint>();
		double angleBetween = Math.PI/ (sensorDensity-1);
		double currentAngle = mod(heading-Math.PI/2, 2*Math.PI);
		for(int i=0;i<sensorDensity;i++) {
			int sensorRange = this.sensorRange;
			//Check for intersecting obstacles
			IntPoint edge = getPointTowards(currentAngle, sensorRange);
			Line2D.Double sensorLine = new Line2D.Double(new Point(coords.x, coords.y), new Point(edge.x, edge.y));
			IntPoint intersection = intersects(sensorLine);
			if(intersection != null) {
				sensorRange = (int)distance(intersection, coords);
				visibleObstacles.add(intersection);
			}
			sensorablePoints.add(getPointTowards(currentAngle, sensorRange));
			currentAngle+=angleBetween;
		}
		return sensorablePoints;
	}

	/** 
	 * Get the closest point where this line crosses an obstacle - this varies based on the obstacle type
	 * In general, this is achieved by turning the obstacle into a series of lines and calling 
	 * getIntersectionPoint() on the target line and each of the polygon's lines. Once all intersection 
	 * points are found, the closest to the robot is returned. It is assumed all polygons are convex.
	 */
	private IntPoint intersects(Line2D.Double line) {
		ArrayList<IntPoint> intersections = new ArrayList<IntPoint>();
		for(Renderable obstacle : obstacles) {
			if (obstacle.getClass() == RenderablePolyline.class) {
				ArrayList<Integer> xs = ((RenderablePolyline)obstacle).xPoints;
				ArrayList<Integer> ys = ((RenderablePolyline)obstacle).yPoints;
				for(int i=0;i<xs.size()-1;i++) {
					Line2D.Double obsLine = new Line2D.Double(xs.get(i), ys.get(i), xs.get(i+1), ys.get(i+1));
					IntPoint intersect = getIntersectionPoint(line, obsLine);
					if(intersect != null) intersections.add(intersect);
				}
			} else if (obstacle.getClass() == RenderableRectangle.class) {
				/* Rectangle is treated like a polygon but since because it's a 
				 * different class it has to be handled separately - we've got to construct the
				 * polypoints separately (annoyingly)*/
				ArrayList<Integer>xs = new ArrayList<Integer>();
				ArrayList<Integer>ys = new ArrayList<Integer>();
				xs.add(((RenderableRectangle)obstacle).bottomLeftX);
				xs.add(((RenderableRectangle)obstacle).bottomLeftX);
				xs.add(((RenderableRectangle)obstacle).bottomLeftX + ((RenderableRectangle)obstacle).width);
				xs.add(((RenderableRectangle)obstacle).bottomLeftX + ((RenderableRectangle)obstacle).width);

				ys.add(((RenderableRectangle)obstacle).bottomLeftY);
				ys.add(((RenderableRectangle)obstacle).bottomLeftY + ((RenderableRectangle)obstacle).height);
				ys.add(((RenderableRectangle)obstacle).bottomLeftY + ((RenderableRectangle)obstacle).height); 
				ys.add(((RenderableRectangle)obstacle).bottomLeftY);

				for(int i=0;i<xs.size();i++) {
					Line2D.Double obsLine = new Line2D.Double(xs.get(i), ys.get(i), 
							xs.get((i+1) % xs.size()), ys.get((i+1) % ys.size()));
					IntPoint intersect = getIntersectionPoint(line, obsLine);
					if(intersect != null) intersections.add(intersect);
				}

			} else if (obstacle.getClass() == RenderablePolygon.class) {
				ArrayList<Integer> xs = ((RenderablePolygon)obstacle).xPoints;
				ArrayList<Integer> ys = ((RenderablePolygon)obstacle).yPoints;
				for(int i=0;i<xs.size();i++) {
					Line2D.Double obsLine = new Line2D.Double(xs.get(i), ys.get(i), 
							xs.get((i+1) % xs.size()), ys.get((i+1) % ys.size()));
					IntPoint intersect = getIntersectionPoint(line, obsLine);
					if(intersect != null) intersections.add(intersect);
				}
			} else if (obstacle.getClass() == RenderableOval.class) {
				//ovals are treated as their bounding polygons (90-sided) and they have to be circles
				ArrayList<Integer>xs = new ArrayList<Integer>();
				ArrayList<Integer>ys = new ArrayList<Integer>();
				RenderableOval roval = (RenderableOval) obstacle; 
				
				for(int i=0;i<90;i++) {
					int trigPoint = (int) (roval.width/2 * Math.cos(i*Math.PI / 45));
					xs.add(roval.centreX + trigPoint);
				}
				
				for(int i=0;i<90;i++) {
					int trigPoint = (int) (roval.width/2 * Math.sin(i*Math.PI / 45));
					ys.add(roval.centreY + trigPoint);
				}
				
				for(int i=0;i<xs.size();i++) {
					Line2D.Double obsLine = new Line2D.Double(xs.get(i), ys.get(i), 
							xs.get((i+1) % xs.size()), ys.get((i+1) % ys.size()));
					IntPoint intersect = getIntersectionPoint(line, obsLine);
					if(intersect != null) intersections.add(intersect);
				}
				
			}
		}
		return intersections.size() == 0 ? null : lowestDist(intersections);
	}

	/**
	 * Get the closest point to the robot's coords
	 * @param points A list of point
	 * @return The point with the smallest distance from the robot
	 **/
	private IntPoint lowestDist(ArrayList<IntPoint> points) {
		int lowest = 0;
		for(int i=0;i<points.size();i++) {
			if (distance(points.get(i), coords) < distance(points.get(lowest), coords))
				lowest = i;
		}
		return points.get(lowest);
	}

	/**
	 * Have the robot move along a certain heading
	 * @param heading The heading to move along. 
	 **/
	private void moveTowards(double heading) {
//		int chord = Math.abs(end.x - coords.x);
//		//arc movement
//		double curvature = 2 * (Math.sin(Math.abs(heading)) / chord );
//		// almost certainly wrong
//		double arcLength = 2 * (Math.abs(heading) / curvature);

		int length = (int) (stepSize * Math.cos(heading));
		int height = (int) (stepSize * Math.sin(heading));
		coords.x += length;
		coords.y += height;
		if(robotPic == null) {
			robotPicAlt.x = coords.x;
			robotPicAlt.y = coords.y;
		} else {
			robotPic.x += coords.x;
			robotPic.y += coords.y;
		}
		this.heading = heading;
	}

	/**
	 * Get the point 'step' pixels along the given heading from the robot's position
	 * @param heading The heading to move along
	 * @param step The distance to travel along that heading
	 **/
	private IntPoint getPointTowards(double heading, int step) {
		int length = (int) (step * Math.cos(heading));
		int height = (int) (step * Math.sin(heading));
		return new IntPoint(coords.x+length, coords.y+height);
	}

	/**
	 * Find the heading that the robot must move to in order to reach a certain point. If
	 * the angle is greater than 60 degrees, truncate it to 60 degrees,=.
	 * @param end The destination point
	 **/
	private double calculateHeading(IntPoint end) {
		double grad = Math.abs(((double)end.y - (double)coords.y)
				/ ((double)end.x - (double)coords.x));
		double angle = Math.atan(grad);

		if(end.x - coords.x < 0) {
			if(end.y - coords.y < 0) {
				angle = Math.PI + angle;
			} else {
				angle = Math.PI - angle;
			}
		} else {
			if(end.y - coords.y < 0) {
				angle = (Math.PI * 2) - angle;
			}
		}

		return angle;
	}

	// method for calculating heading based on arcs
	private double calculateHeadingArcs(IntPoint end){
		//AI just starting to move
		if(sampleArc == null){
			System.out.println("Starts watch out !!!");
			sampleArc = new Arc(coords, end, heading);
//			currArc = sampleArc;
//			this.arcPair = sampleArc.getTwoMoreArcs(end, goal);
			sampleArc.setTurnDone(sampleArc.getTurnDone() + sampleArc.getGama() * 2);
			return  heading + sampleArc.getGama() * 2;
		}
		// on the move
		else{
			// if exceeds
			if(sampleArc.getTurnDone() + sampleArc.getGama() * 2 > sampleArc.getTheta()){
				sampleArc = new Arc(coords, end, heading);
				sampleArc.setTurnDone(sampleArc.getTurnDone() + sampleArc.getGama() * 2);
				return  heading + sampleArc.getGama() * 2;
			}
			// if not exceeds
			sampleArc.setTurnDone(sampleArc.getTurnDone() + sampleArc.getGama() * 2);
			return  heading + sampleArc.getGama() * 2;
		}
		//AI is on the move
//		else{
//			if(!sampleArc.isDone(heading)){
////				System.out.println("in first arc");
//				currArc = sampleArc;
//				sampleArc.setTurnDone(heading + sampleArc.getGama() * 2);
//				return heading + sampleArc.getGama() ;
//			}
//			else if(!arcPair[FIRSTARC].isDone(heading)){
////				System.out.println("in second arc");
//				currArc =arcPair[FIRSTARC];
//				arcPair[FIRSTARC].setTurnDone(heading + arcPair[FIRSTARC].getGama() * 2);
//				return heading + arcPair[FIRSTARC].getGama();
//			}
//			else{
////				System.out.println("in third arc");
//				currArc = arcPair[SECONDARC];
//				arcPair[SECONDARC].setTurnDone(heading + arcPair[SECONDARC].getGama() * 2);
//				return heading + arcPair[SECONDARC].getGama();
//			}
//
//		}
	}

//	private double move3Arcs(IntPoint end){
//		if(sampleArc == null){
////			sampleArc = new Arc(gama * 2, 2 * theta, curvature, arcLen);
//			// sample (end of sampleArc) to goal distance
//			double b = Math.sqrt(Math.pow(goal.x - end.x, 2) + Math.pow(goal.y - end.y, 2));
//			// first arc of pair's turn
//			alpha = heading + 2 * theta * sign; // heading copy probably because heading is changing ???
//			// second arc of pair's turn
//			beta = alpha * 0.5837;
//			//new gama ???
//			gama = 180 - alpha/2 + beta/2;
//			double chordAlpha = b * Math.sin(beta/2) / Math.sin(gama);
//			double chordBeta = b * Math.sin(alpha/2) / Math.sin(gama);
//
//			double arcAlphaCurvature = 2 * (Math.sin(Math.abs(alpha/2)) / chordAlpha);
//			double arcAlphaLength = Math.abs(alpha) / arcAlphaCurvature;
//
//			//heading at goal from sample
//			heading += alpha + beta;
//		}
//return 1.4;
//	}

	/**
	 * Get the position of the smallest number in an array of doubles 
	 **/
	private int minIndex(double[] nums) {
		int minIndex = 0;
		for(int i=1;i<nums.length;i++) {
			if(nums[i] < nums[minIndex]) minIndex = i; 
		}
		return minIndex;
	}

	/**
	 * Get the distance between two points. 
	 **/
	private static double distance(IntPoint a, IntPoint b) {
		return Math.sqrt(Math.pow((a.x-b.x), 2) + Math.pow((a.y-b.y), 2));
	}

	/**
	 * Check if the robot falls within the goal radius. 
	 **/
	public boolean inGoal() {
		return distance(coords, goal) < goalRadius+radius;
	}

	/**
	 * Calculate the intersection point of two lines, or return null if there is no
	 * intersection.
	 * @param line1 The first line
	 * @param line2 The second line
	 * @return The point of intersection, or null.
	 */
	private static IntPoint getIntersectionPoint(Line2D.Double line1, Line2D.Double line2) {
		if (! line1.intersectsLine(line2) ) return null;
		double px = line1.getX1(),
				py = line1.getY1(),
				rx = line1.getX2()-px,
				ry = line1.getY2()-py;
		double qx = line2.getX1(),
				qy = line2.getY1(),
				sx = line2.getX2()-qx,
				sy = line2.getY2()-qy;

		double det = sx*ry - sy*rx;
		if (det == 0) {
			return null;
		} else {
			double z = (sx*(qy-py)+sy*(px-qx))/det;
			if (z==0 ||  z==1) return null;  // intersection at end point
			return new IntPoint(
					(int)(px+z*rx), (int)(py+z*ry));
		}
	}

	/**
	 * Calculate a % b, but always result in a positive answer - java's default syntax returns a
	 * negative number if the dividend is negative, which is unhelpful when my calculations are
	 * performed between 0 and 2PI, rather than -PI and PI.
	 **/
	private static double mod(double a, double b) {
		return ((a % b) + b) % b;
	}
	
	/**
	 * Find the distance from the robot to the closest visible obstacle, or some default if
	 * none are visible 
	 **/
	private int distanceToClosestObstacle() {
		if(visibleObstacles==null||  visibleObstacles.size()==0) return sampleSizeDefault;
		int closest = 0;
		for(int i=0;i<visibleObstacles.size();i++) 
			if (distance(coords, visibleObstacles.get(i)) < distance(coords, visibleObstacles.get(closest))) 
				closest = i;
		return (int)Math.round(distance(coords, visibleObstacles.get(closest)));
	}
	
	public IntPoint getPosition() {
		return coords;
	}

	/**
	 * Return the image representing the robot. This can be a red blob, or a picture depending on the options
	 * enabled. 
	 **/
	public Renderable getImage() {
		if(robotPic != null) {
			robotPic.x = coords.x - radius;
			robotPic.y = coords.y - radius;
			return robotPic;
		} else {
			return robotPicAlt;
		}
	}
	
	public int getStepSize() {
		return this.stepSize;
	}
	
	public void setGoal(IntPoint newGoal) {
		this.goal = newGoal;
	}
	
	public void setGoalRadius(int rad) {
		this.goalRadius = rad;
	}

	public void obstacleDegisti(ArrayList<Renderable> obstacles2) {
		obstacles = obstacles2;
		
	}

}

package dwa;

import java.awt.Point;
import java.util.Vector;

public class PathPlanner {
	private final static float alpha = 1;
	private final static float beta = 0;
	private final static float gamm = 10;
	private Point start;
	private Point end;
	private int length, bredth;
	private State curr_state =new State();
	private Vector<State> path;
	private int acceleration;
	private int ang_acceleration;

	public PathPlanner(Point star, Point en, int len, int bred) {
		start = star;
		length = len;
		bredth = bred;
		end = en;
		acceleration = 8;
		ang_acceleration = 40;
	}

	void getPath() {
		curr_state.setVel(0);
		curr_state.setAng_vel(0);
		curr_state.setX(start.x);
		curr_state.setY(start.y);
		curr_state.setOrientation(0);

		float r = 0;
		while (!(curr_state.getX() == end.x && curr_state.getY() == end.y)) {
			float dist, i, j, velocity = curr_state.getVel(), ang_velocity = curr_state.getAng_vel(),
					x = curr_state.getX(), y = curr_state.getY(), orientation = curr_state.getOrientation();
			float maxval = -100;
			int maxi = 0;
			int maxj = 0;
			boolean allnotpossible = true;
			for (i = velocity + acceleration / 4; i >= velocity - acceleration / 4; i--) {
				if (i == 0 || Math.abs(i) > 25 || i < 0)
					continue;

				for (j = ang_velocity - ang_acceleration / 4; j <= ang_velocity + ang_acceleration / 4; j++) {
					if (j != 0 || Math.abs(j) > 100)
						r = (float) ((float) i / j * 180 / 3.14);
					else
						continue;
					for (float t = 0; true; t += 0.25 * sgn((int) j)) {
						float y1 = (float) (y - r * Math.sin((orientation - t) * (3.14 / 180))
								+ r * (Math.sin((orientation) * (3.14 / 180))));
						float x1 = (float) (x - r * Math.cos((orientation - t) * (3.14 / 180))
								+ r * (Math.cos((orientation) * (3.14 / 180))));
						float theta = (orientation - t);
						if (!isValid(x1, y1, theta, length, bredth)) {
							if (Math.abs(t) < Math.abs(j * 0.25)) {
								break;
							}
							dist = (float) (r * (t) * (3.14 / 180));
							if (Math.abs(i) <= Math.sqrt(acceleration * dist * sgn((int) dist)) - 10) {
								if ((calc(x, y, orientation, j, r, end) + gamm * dist) > maxval) {
									allnotpossible = false;
									maxval = (calc(x, y, orientation, j, r, end) + gamm * dist);
									maxi = (int) i;
									maxj = (int) j;
									//System.out.println("MaxVal "+maxval);
									
								}
							} else {

							}
							break;
						}
						if (t >= 360 || t <= -360) {
							if (calc(x, y, orientation, j, r, end) + Math.abs(gamm * 2 * 3.14 * r) > maxval) {
								allnotpossible = false;
								maxval = (float) (calc(x, y, orientation, j, r, end) + Math.abs(gamm * 2 * 3.14 * r));
								maxi = (int) i;
								maxj = (int) j;
							//	System.out.println("MaxVal "+maxval);
							}
							break;
						}
					}
				}
			}
			if (allnotpossible) {
				System.out.println("All of the possible options are not viable");

			}
			int yd = (int) y;
			int xd = (int) x;
			for (float t = orientation; sgn(maxj) * t >= sgn(maxj) * (orientation - maxj * 0.25); t = t - sgn(maxj)) {
				r = (float) ((float) maxi / maxj * 180 / 3.14);
				y = (float) (yd + r * (Math.sin(orientation * (3.14 / 180)) - r * (Math.sin((3.14 / 180) * (t)))));
				x = (float) (xd + r * (Math.cos(orientation * (3.14 / 180)) - r * (Math.cos((3.14 / 180) * (t)))));
		
				Point[] vertices = new Point[4];

				vertices[0] = new Point(
						(int) (y + length * Math.cos(t * 3.14 / 180) + bredth * Math.sin(t * 3.14 / 180)),
						(int) (x - length * Math.sin(t * 3.14 / 180) + bredth * Math.cos(t * 3.14 / 180)));
				vertices[1] = new Point(
						(int) (y + length * Math.cos((-t + 90) * 3.14 / 180)
								- bredth * Math.sin((-t + 90) * 3.14 / 180)),
						(int) (x + length * Math.sin((-t + 90) * 3.14 / 180)
								+ bredth * Math.cos((-t + 90) * 3.14 / 180)));
				vertices[2] = new Point(
						(int) (y + length * Math.cos((-t + 180) * 3.14 / 180)
								- bredth * Math.sin((-t + 180) * 3.14 / 180)),
						(int) (x + length * Math.sin((-t + 180) * 3.14 / 180)
								+ bredth * Math.cos((-t + 180) * 3.14 / 180)));
				vertices[3] = new Point(
						(int) (y + length * Math.cos((-t + 270) * 3.14 / 180)
								- bredth * Math.sin((-t + 270) * 3.14 / 180)),
						(int) (x + length * Math.sin((-t + 270) * 3.14 / 180)
								+ bredth * Math.cos((-t + 270) * 3.14 / 180)));

			}

			curr_state.setY( (int) (curr_state.getY() + r * Math.sin(orientation * (3.14 / 180))
					- r * Math.sin((3.14 / 180) * (orientation - maxj * 0.25))));
			curr_state.setX( (int) (curr_state.getY() + r * Math.cos(orientation * (3.14 / 180))
					- r * Math.cos((3.14 / 180) * (orientation - maxj * 0.25))));
			curr_state.setOrientation((float) (curr_state.getOrientation() - maxj * 0.25));

			curr_state.setVel(maxi);
			curr_state.setAng_vel(maxj);
			System.out.println("New position X "+curr_state.getX() +" Y "+curr_state.getY());

		}

	}

	int bound(int x, int limit) {
		if (x < 0)
			return 0;
		else if (x >= limit)
			return limit - 1;
		else
			return x;
	}

	private boolean isValid(float x, float y, float theta, int length, int bredth) {
		float t = -theta;
		if (x < 0 || x > length || y < 0 || y > bredth) {
			return false;
		}

		else
			return true;
	}

	private int sgn(int x) {
		if (x > 0)
			return 1;
		else if (x < 0)
			return -1;
		else
			return 0;

	}

	private float calc(float x, float y, float orientation, float j, float r, Point end) {
		int y1 = (int) (y
				- r * (Math.sin((orientation - 0.25 * j) * (3.14 / 180)) + r * Math.sin((orientation) * (3.14 / 180))));
		int x1 = (int) (x
				- r * (Math.cos((orientation - 0.25 * j) * (3.14 / 180)) + r * Math.cos((orientation) * (3.14 / 180))));
		orientation -= 0.25 * j;
		float theta = (float) (Math.atan(-(float) (end.x - x) / (end.y - y)) * (180 / 3.14));
		if (end.y < y)
			theta = (int) (theta + 180) % 360;
		float a;

		if (theta - orientation < -180)
			a = theta - orientation + 360;
		else if (theta - orientation > 180)
			a = theta - orientation - 360;
		else
			a = theta - orientation;
	//	System.out.println("Calc "+(alpha * (180 - Math.abs(a)) + beta * r * j));
		return (alpha * (180 - Math.abs(a)) + beta * r * j);
	}

	public static void main(String[] args) {


		Point start = new Point(0, 0);
		Point end = new Point(27, 24);
		PathPlanner dvij = new PathPlanner(start, end, 300, 300);
		dvij.getPath();

	}

}

package potentialField2;
import geometry.IntPoint;

public class Arc {
    private IntPoint start;
    private IntPoint end;
    private double startHeading;
    private double theta;
    private double curvature;
    private double length;
    private double gama;
    private double turnDone;

    public Arc(IntPoint start, IntPoint end, double startHeading) {
        this.start = new IntPoint(start.x, start.y);
        this.end = end;
        this.startHeading = startHeading;
        this.setTurnDone(0);

        double headingVectorX = Math.cos(startHeading);
        double headingVectorY = Math.sin(startHeading);
        double diffVectorX = end.x - start.x;
        double diffVectorY = end.y - start.y;
        // compute the dot product of the startHeading and difference vectors
        double dotProduct = headingVectorX * diffVectorX + headingVectorY * diffVectorY;
        // startHeading vector length (always 1)
        double norm1 = Math.sqrt(Math.pow(headingVectorX, 2) + Math.pow(headingVectorY, 2));
        // sample vector length
        double norm2 = Math.sqrt(Math.pow(diffVectorX, 2) + Math.pow(diffVectorY, 2));
        // from a * b = dotProduct  * cos(theta)
        double cosAngle = dotProduct / (norm1 * norm2);
        // angle between startHeading vector and sample vector
        this.theta = Math.acos(cosAngle); // result between 0 and PI SIGN ???
        if (headingVectorX * diffVectorY < headingVectorY * diffVectorX)
            this.theta = -theta;

        this.curvature = 2 * (Math.abs(Math.sin(theta)) / norm2);
        this.length = 2 * Math.abs(theta) / curvature;
        double moveRatio = 10 / this.length;
        // 1/2 of the arc's fractional turn
        this.gama = moveRatio * theta;
        if (curvature <= 0) {
            this.gama = 0;
        }

    }

    public boolean isDone(double robotHeading) {
        if (2 * this.getTheta() >= this.getTurnDone())
            return true;
        return false;
    }

    //getIntersectPoint, if alpha zero cater...
    public Arc[] getTwoMoreArcs(IntPoint sample, IntPoint goal) {
        // theta * 2 is heading after sample arc ???
        System.out.println("this = " + this);
        //calculate angle between SV and x axis
        double diffVectorX = goal.x - sample.x; // cos if this is otricatelno + or - math.pi
        double diffVectorY = goal.y - sample.y; // sin
        double SG = Math.sqrt(Math.pow(diffVectorX, 2) + Math.pow(diffVectorY, 2));
        double fi = Math.atan(SG);
  //      this.startHeading - alfa/2;
        if(Math.cos(diffVectorX) < 0)
            fi = Math.PI - fi;
        fi = normaliseAngle(fi);


        System.out.println("fi is: " + Math.toDegrees(fi));
        IntPoint V = getV(sample, this.getStartHeading()- fi, goal);
        Arc arc2 = new Arc(sample, V, this.getHeadingAtEnd());
        System.out.println("arc2 = " + arc2);
        Arc arc3 = new Arc(arc2.end, goal, arc2.getHeadingAtEnd());
        System.out.println("arc3 = " + arc3);
        return new Arc[]{arc2, arc3};
    }

    // the heading angle sign problem ?????
    private IntPoint getV(IntPoint sample, double alpha, IntPoint goal) {
        IntPoint V = new IntPoint();
        //calculate alfa angle change
        if (alpha == 0) {
            System.out.println("alpha is 000000000000000000");
            V.x = (sample.x + goal.x) / 2;
            V.y = (sample.y + goal.y) / 2;
        } else calculateArcIntersection(sample, goal, alpha);

        return V;
    }

    private double normaliseAngle(double angle){
        while(angle > 2 * Math.PI)
            angle -= 2 * Math.PI;
        while(angle < 0)
            angle += 2 * Math.PI;
        return angle;
    }

    private IntPoint calculateArcIntersection(IntPoint sample, IntPoint goal, double alpha) {
        double b = Math.sqrt(Math.pow(goal.x - sample.x, 2) + Math.pow(goal.y - sample.y, 2));
        System.out.println("b is: " + b);
        alpha = normaliseAngle(alpha);
        double beta = alpha * 0.5837;
  //      beta = normaliseAngle(beta);
        System.out.println("beta is: " + Math.toDegrees(beta));
        System.out.println("alpha is: " + Math.toDegrees(alpha));
        // normalise gama ??
        double gamma = Math.PI - (alpha/2 + beta/2);
        System.out.println("gamma is:" + Math.toDegrees(gamma));
        double chordAlpha = b * Math.sin(beta / 2) / Math.sin(gamma);
        System.out.println("alpha chord is: " + chordAlpha);
        double x = Math.cos(getHeadingAtEnd() - alpha / 2) * chordAlpha;
        System.out.println("theta here is"  + Math.toDegrees(this.theta));
        System.out.println("alpha here is: " + Math.toDegrees(alpha));
        System.out.println("heading at end is: " + Math.toDegrees(getHeadingAtEnd()));
        System.out.println("getHeadingAtEnd() + alpha / 2 is: " + Math.toDegrees(getHeadingAtEnd() + alpha / 2));
        System.out.println("Vx is: " + x);
        double y = Math.sin(getHeadingAtEnd() - alpha / 2) * chordAlpha;
        System.out.println("Vy is: " + y);
        //WRONG!!!
        IntPoint V = new IntPoint((int) x, (int) y);
        System.out.println("Vx after int conversion is: " + V.x);
        System.out.println("Vy after int conversion is: " + V.y);
        return V;
    }

    private double getHeadingAtEnd() {
        return startHeading + 2 * theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getTheta() {
        return theta;
    }

    public double getCurvature() {
        return curvature;
    }

    public void setCurvature(double curvature) {
        this.curvature = curvature;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public IntPoint getStart() {
        return start;
    }

    public void setStart(IntPoint start) {
        this.start = start;
    }

    public IntPoint getEnd() {
        return end;
    }

    public void setEnd(IntPoint end) {
        this.end = end;
    }

    public double getStartHeading() {
        return startHeading;
    }

    public void setStartHeading(double startHeading) {
        this.startHeading = startHeading;
    }

    public double getGama() {
        return gama;
    }

    public void setGama(double gama) {
        this.gama = gama;
    }

    public double getTurnDone() {
        return turnDone;
    }

    public void setTurnDone(double turnDone) {
        this.turnDone = turnDone;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "start=" + start +
                ", end=" + end +
                ", startHeading=" + Math.toDegrees(startHeading) +
                ", theta=" + Math.toDegrees(theta) +
                ", curvature=" + curvature +
                ", length=" + length +
                ", gama=" + Math.toDegrees(gama) +
                ", turnDone=" + turnDone +
                ", heading at end="+ Math.toDegrees(getHeadingAtEnd()) +
                '}';
    }
}

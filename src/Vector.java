
public class Vector {

	public double x, y, theta, magnitude;
	
	public Vector(double d, double e) {
		this.x = d;
		this.y = e;
		this.theta = Math.atan(y/x);
		this.magnitude = Math.sqrt(x*x + y*y);
	}
	
	// just needed to be different
	public Vector(double t, double m, String x) {
		this.theta = t;
		this.magnitude = m;
		this.x = Math.cos(theta) * magnitude;
		this.y = Math.sin(theta) * magnitude;
 	}
	
	public double[] getComponents() {
		return new double[] {x, y};
	}
	
	public double[] getPolar() {
		return new double[] {theta, magnitude};
	}
	
	public Vector addVector(Vector other) {
		return new Vector(x + other.x, y + other.y);
	}
	
	public static Vector sub(Vector v2, Vector v1) {
		return new Vector(v2.x-v1.x, v2.y-v1.y);
	}
	
	public Vector scale(double d) {
		return new Vector(d * x, d * y);
	}
 	
}

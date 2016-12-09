import java.awt.Graphics;
import java.util.ArrayList;

public class Satellite extends Entity {

	public Entity o;
	private static final double G = 2 * 6.67 * Math.pow(10, 2);
	private ArrayList<Vector> x0s;

	public Satellite(Vector s, Vector v0, Entity o) {
		super(s, v0);
		this.o = o;
		this.x0s = new ArrayList<Vector>();
	}

	public void update() {
		Vector res = Vector.sub(o.s, this.s);
		double radius = res.magnitude;
		
		if(this.s.x > o.s.x)
			res.theta += Math.PI;
		
		double Fg = G * this.mass * o.mass / (radius * radius);
		
		acceleration = new Vector(res.theta, Fg/mass, "POLAR");
		
		velocity.x += acceleration.x * 1/30;
		velocity.y += acceleration.y * 1/30;
		
		s.x += velocity.x;
		s.y += velocity.y;
		
		x0s.add(new Vector(s.x, s.y));
		
		while(x0s.size() > 15) {
			x0s.remove(0);
		}
	}
	
	public void render(Graphics g, double scale) {
		g.setColor(col);
		g.fillRect((int)(s.x*scale), (int)((s.y*scale)), (int)(width*scale), (int)(height*scale));
		for(int i = 0; i < x0s.size()-1; i++) {
			g.drawLine((int)(x0s.get(i).x*scale), (int)(x0s.get(i).y*scale), (int)(x0s.get(i+1).x*scale), (int)(x0s.get(i+1).y*scale));
		}
	}
	
}

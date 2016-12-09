import java.awt.Color;
import java.awt.Graphics;

public class Entity {

	public double mass=100, width=100, height=100;
	public Vector s, velocity, acceleration;
	public Color col = Color.white;
	
	
	public Entity(Vector s, Vector v0) {
		this.s = s;
		this.velocity = v0;
		this.acceleration = new Vector(0,0);
	}
	
	public void update() {	
		velocity.x += acceleration.x * 1/30;
		velocity.y += acceleration.y * 1/30;
		
		s.x += velocity.x;
		s.y += velocity.y;
	}
	
	public void render(Graphics g, double scale) {
		g.setColor(col);
		g.fillRect((int)(s.x*scale), (int)((s.y*scale)), (int)(width*scale), (int)(height*scale));
	}
}

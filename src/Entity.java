
public class Entity {

	public double mass=100, width=100, height=100;
	public Vector s, velocity, acceleration;
	public int col = 0xff0000;
	
	
	public Entity(Vector s, Vector v0) {
		this.s = s;
		this.velocity = v0;
		this.acceleration = new Vector(0,0);
	}
	
	public void update() {	
		velocity.x += acceleration.x * 1/60;
		velocity.y += acceleration.y * 1/60;
		
		s.x += velocity.x;
		s.y += velocity.y;
	}
	
	public void render(Screen screen) {
		screen.fillRect((int)(s.x-width/2), (int)(Game.HEIGHT-(s.y+height/2)), (int)(width), (int)(height), col);
	}
}

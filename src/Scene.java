import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Scene implements KeyListener{
	
	private Entity square;
	
	private Entity earth;
	private Entity mars;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Scene() {
		//square = new Entity(100, 600, new Vector(2, 0));
		//entities.add(square);
		
		earth = new Entity(new Vector(500, 400), new Vector(0,0));
		earth.width = earth.height = 5;
		earth.mass = earth.width * earth.height;
		earth.col = 0xffff;
	
		mars = new Entity(new Vector(500, 700), new Vector(1.2,0.3));
		mars.width = mars.height = 1;
		mars.mass = mars.width * mars.height;
		mars.col = 0xE56A0D;
		
		entities.add(earth);
		entities.add(mars);
	}
	
	public void update() {
		for(Entity e: entities) {
			e.update();
		}
		
		final double G = 2 * 6.67 * Math.pow(10, 2);
		Vector e2m = Vector.sub(mars.s, earth.s);
		double radius = e2m.magnitude;
		
		// takes negetive theta value and corrects it
		if(earth.s.x > mars.s.x)
			e2m.theta += Math.PI;
		
		double Fg = G * earth.mass * mars.mass / (radius * radius);
		
		earth.acceleration = new Vector(e2m.theta, Fg/earth.mass, "POLAR");
		mars.acceleration = new Vector(Math.PI + e2m.theta, Fg/mars.mass, "POLAR");
		
		
		/*
		square.acceleration = new Vector(0, Game.accelGravity);
		if(square.xc - square.width/2 < 0) {
			square.velocity.x *= -0.9;
			square.xc = square.width/2;
		}
		else if (square.xc + square.width/2 >= Game.WIDTH) {
			square.velocity.x *= -0.9;
			square.xc = Game.WIDTH - square.width/2;
		}
	
		if(square.yc - square.height/2 < 0) {
			square.velocity.y *= -0.9;
			square.yc = square.height/2;
		}
		*/
		
	}
	
	public void render(Screen screen) {
		for(Entity e: entities) {
			e.render(screen);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		/*
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			mars.velocity = square.velocity.addVector(new Vector(-1, 0));
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			square.velocity = square.velocity.addVector(new Vector(1, 0));
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			square.velocity = square.velocity.addVector(new Vector(0, -2));
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			square.velocity = square.velocity.addVector(new Vector(0, 2));
		}
		else 
		*/
		
		if(e.getKeyCode() == KeyEvent.VK_E) {
			earth.velocity = earth.velocity.scale(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_M) {
			mars.velocity = mars.velocity.scale(-0.9);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Scene extends JPanel {
	
	private Entity sun;
	private Satellite earth, mars, jupiter;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Scene() {
		this.setBackground(Color.black);
		
		sun = new Entity(new Vector(720 + 280 + 3000, 450 + 550 + 3000), new Vector(0,0));
		sun.width = sun.height = 18;
		sun.mass = sun.width * sun.height;
		sun.col = Color.yellow;
		entities.add(sun);
		
		earth = new Satellite(new Vector(1340 + 280 + 3000, 800 + 550 + 3000), new Vector(-3,0), sun);
		earth.width = earth.height = 6;
		earth.mass = earth.width * earth.height;
		earth.col = Color.blue;
		entities.add(earth);
		
		mars = new Satellite(new Vector(100 + 280 + 3000, 100 + 550 + 3000), new Vector(3,0), sun);
		mars.width = mars.height = 8;
		mars.mass = mars.width * mars.height;
		mars.col = Color.red;
		entities.add(mars);
		
		jupiter = new Satellite(new Vector(1900 + 3000, 1900 + 3000), new Vector(-3,0.9), sun);
		jupiter.width = jupiter.height = 12;
		jupiter.mass = jupiter.width * jupiter.height;
		jupiter.col = Color.green;
		entities.add(jupiter);
		
	}
	
	public void update() {
		for(Entity e: entities) {
			e.update();
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(5000,5000);
	}
	
	public void paintComponent(Graphics g) {
		for(Entity e: entities) {
			e.render(g, Game.SCALE);
		}
	}
	
}

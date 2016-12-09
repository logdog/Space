import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean running = false;
	private Scene scene;
	public static int highscore;
	public static String title = "Space";
	public static double accelGravity = -9.81;
	private JScrollPane sp;
	
	public static double SCALE = 1.0;
	
	private static JSlider slider;
	
	public static int WIDTH=1440, HEIGHT=900;
	
	public Game() {
		frame = new JFrame();
		
		scene = new Scene();
		slider = new JSlider(0, 981 * 10, 981);
		slider.setMajorTickSpacing(981);
		slider.setMinorTickSpacing(109);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				accelGravity = -1 * slider.getValue() / 100;
			}
		});
		
		scene.getInputMap().put(KeyStroke.getKeyStroke("UP"), "zoom in");
		scene.getActionMap().put("zoom in", new KeyAction(1.33));
		
		scene.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "zoom out");
		scene.getActionMap().put("zoom out", new KeyAction(1/1.33));
		
		sp = new JScrollPane(scene);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
	
	private class KeyAction extends AbstractAction {

		private double scalar;
		
        KeyAction(double scalar) {

            this.scalar = scalar;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	SCALE *= scalar;
        }
    }

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 30.0;
		double delta = 0;
		int frames = 0, updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update() {
		scene.update();
	}
	
	public void render(){
		scene.repaint();
	}

	public static void main(String[] args) {
		Game game = new Game();
		
		frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setUndecorated(false);
		frame.setResizable(true);
		frame.setLayout(new GridLayout());
		frame.setTitle(title);
		
		frame.add(game.sp, 0);
		//frame.add(slider, 1);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		game.start();
	}
}

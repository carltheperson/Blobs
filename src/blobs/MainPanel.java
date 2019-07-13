package blobs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable{
	private int WIDTH = 500;
	private int HEIGHT = 500;
	
	private Thread thread;	
	private boolean running = true;
	private BufferedImage image;
	private Graphics2D g;
	
	public MainPanel () {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);	
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		while (running) {
			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			g.setColor(new Color(200, 200, 200));
			g.fillOval(100, 100, 100, 100);
			
			mainDraw();
		
		}
	}
	
	
	private void mainDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
}

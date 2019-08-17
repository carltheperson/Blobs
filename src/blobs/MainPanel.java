package blobs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static int WIDTH = 800;
	public static int HEIGHT = 800;
	
	private Thread thread;	
	private boolean running = true;
	private BufferedImage image;
	private Graphics2D g;
	private int fps = 1000;
	
	public MainPanel () {
		super();
		
		setPreferredSize(new Dimension(WIDTH + 500, HEIGHT));
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
		
		image = new BufferedImage(WIDTH + 500, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//fps
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime = 1000 / fps;
		
		
		Blob.makeBlobArray();
		
		while (running) {
			startTime = System.nanoTime();
			g.setColor(new Color(200, 255, 255));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			//////////
			g = Food.manageFood(g);
			g = Blob.manageBlob(g);
			g = Statistics.draw(g);
			
			//////////
			
			mainDraw();
			
			//fps
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			try {
				//Thread.sleep(waitTime);
			}
			catch (Exception e) {}
		
		}
	}
	
	
	private void mainDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
}

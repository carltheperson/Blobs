package blobs;

import java.awt.Color;
import java.awt.Graphics2D;

public class Blob {
	public Color color = new Color(200, 0, 0);
	public int foodLevel = 100;
	public int fieldOfView = 200;
	
	public int width = 50;
	
	public int x;
	public int y;
	
	public Blob(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Graphics2D draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x - width/2, y - width/2, width, width);
		
		return g;
	}
}

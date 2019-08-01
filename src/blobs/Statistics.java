package blobs;

import java.awt.Color;
import java.awt.Graphics2D;

public class Statistics {

	public static Graphics2D draw (Graphics2D g) {
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(MainPanel.WIDTH, 0, 500, MainPanel.WIDTH);
		
		
		
		int sX = MainPanel.WIDTH + 20;
		int sY = 50;
		g.setColor(new Color(100, 100, 100));
		g.drawString("speed", sX, sY - 10);
		g.setColor(new Color(200, 255, 255));
		g.fillRect(sX, sY, 410, 20);
		
		int aX = MainPanel.WIDTH + 20;
		int aY = 150;
		g.setColor(new Color(100, 100, 100));
		g.drawString("max age", aX, aY - 10);
		g.setColor(new Color(200, 255, 255));
		g.fillRect(aX, aY, 410, 20);
		
		
		int rX = MainPanel.WIDTH + 20;
		int rY = 250;
		g.setColor(new Color(100, 100, 100));
		g.drawString("reproductive chance", rX, rY - 10);
		g.setColor(new Color(200, 255, 255));
		g.fillRect(rX, rY, 410, 20);
		
		
		for (int i = 0; i < Blob.blobArray.size(); i++) {
			// speed
			g.setColor(Blob.blobArray.get(i).color);
			sX = ((460/100) * Blob.blobArray.get(i).speed) + MainPanel.WIDTH + 20;
			sY = 55;
			g.fillOval(sX, sY, 10, 10);
			
			// max age
			g.setColor(Blob.blobArray.get(i).color);
			aX = 460/100 * Blob.blobArray.get(i).maxAge + MainPanel.WIDTH + 20;
			aY = 155;
			g.fillOval(aX, aY, 10, 10);
			
			
			// reChance
			g.setColor(Blob.blobArray.get(i).color);
			rX = 460/100 * Blob.blobArray.get(i).reChance + MainPanel.WIDTH + 20;
			rY = 255;
			g.fillOval(rX, rY, 10, 10);
			
			g.drawLine(sX + 5, sY + 5, aX + 5, aY + 5);
			g.drawLine(aX + 5, aY + 5, rX + 5, rY + 5);
		}
		
		
		return g;
	}
	
}

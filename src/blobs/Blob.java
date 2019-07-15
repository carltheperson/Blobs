package blobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Blob {
	public Color color = new Color(200, 0, 0);
	public int foodLevel = 100;
	public int fieldOfView = 200;
	
	public int width = 50;
	
	public double x;
	public double y;
	
	public Blob(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Graphics2D draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) x - width/2, (int)  y - width/2, width, width);
		
		return g;
	}
	
	public void update() {
		
		findNearestFood();
	}
	
	private void moveToFood(int x, int y) {
		
		System.out.println("");
		
		double unit = 4;
		double state = 1;
		
		double difX = x - this.x;
		double difY = y - this.y;
		
		// correcting
		
		if (difX < 0) {
			unit = unit * -1;
			state = -1;
		}
		if (difY < 0) {
			state = -1;
		} 
		if (difY < 0 && difX < 0) {
			state = 1;
		}
		
		////
		
		System.out.println("difX: " + difX);
		System.out.println("difY: " + difY);
		
		double totalDif = difX + difY * state;
		
		System.out.println("totalDif: " + totalDif);
		System.out.println("totalOtherDif: " + (difY - difX));
		
		double moveX = (difX / totalDif) * unit;
		double moveY = (difY / totalDif) * unit;
		
		System.out.println("moveX: " + moveX);
		System.out.println("moveY: " + moveY);
		
		this.x += moveX;
		this.y += moveY;
		
	}
	
	public void findNearestFood() {
		for (int i = 0; i < Food.foodArray.size(); i++) {
			
		}
		
		moveToFood(Food.foodArray.get(0).x, Food.foodArray.get(0).y);
	}
}

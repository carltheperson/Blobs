package blobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Blob {
	public Color color = new Color(200, 0, 0);

	public boolean dead = false;
	
	public int width = 50;
	
	public double x;
	public double y;
	
	public double health = 50;
	public int maxHealth = width;
	
	double unit = 4;
	
	public Blob(int x, int y, double unit) {
		this.x = x;
		this.y = y;
		this.unit = unit;
	}
	
	public Graphics2D draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) x - width/2, (int)  y - width/2, width, width);
		
		g = drawHealth(g);
		
		return g;
	}
	
	public void update() {
		
		findNearestFood();
		manageHealth();
		
	}
	
	private void moveToFood(int x, int y) {
		
		double unit = this.unit;
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
		
		double totalDif = difX + difY * state;
		
		double moveX = (difX / totalDif) * unit;
		double moveY = (difY / totalDif) * unit;
		
		
		if (!Double.isNaN(moveX) || !Double.isNaN(moveY)) {
			this.x += moveX;
			this.y += moveY;
		} else {
			this.x += 1;
			this.y += 1;
		}
	
		
		if (this.x > x - 10 && this.x < x + 10 && this.y > y - 10 && this.y < y + 10) {
			// remove food
			int food = 10000;
			for (int i = 0; i < Food.foodArray.size(); i++) {
				if (Food.foodArray.get(i).x == x && Food.foodArray.get(i).y == y) {
					food = i;
				}
			}
			
			try {
				Food.foodArray.remove(food);
				this.health += 4;
			} catch (Exception e) {}
		}
	}
	
	public void findNearestFood() {
		
		int closest = 100000;
		Food closestFood = null;
		
		int disX;
		int disY;
		
		for (int i = 0; i < Food.foodArray.size(); i++) {
			// X
			if (this.x > Food.foodArray.get(i).x) {
				disX = (int) (this.x - Food.foodArray.get(i).x);
			} else {
				disX = (int) (Food.foodArray.get(i).x - this.x);
			}
			
			//Y
			if (this.y > Food.foodArray.get(i).y) {
				disY = (int) (this.y - Food.foodArray.get(i).y);
			} else {
				disY = (int) (Food.foodArray.get(i).y - this.y);
			}
			
			if (disX + disY < closest) {
				closest = disX + disY;
				closestFood = Food.foodArray.get(i);
			}
		}
		
		if (closestFood != null) {
			moveToFood(closestFood.x, closestFood.y);
		}
	}
	
	public void manageHealth () {
		this.health -= 0.1;
		
		if (this.health > this.maxHealth) {
			this.health = this.maxHealth;
		}
		
		if (this.health < 0) {
			this.dead = true;
		}
		
	}
	
	public Graphics2D drawHealth(Graphics2D g) {
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect((int) x - (width / 2), (int) y + (width / 2) + 4, width, 6);
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect((int) x - (width / 2) + 1, (int) y + (width / 2) + 5, width - 2, 4);
		
		
		g.setColor(new Color(150, 150, 150));
		g.fillRect((int) x - (width / 2) + 1, (int) y + (width / 2) + 5, width - 2 - (width - (int) this.health), 4);
		
		return g;
	}
	
	
}

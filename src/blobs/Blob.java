package blobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;


// They have 3 things they can change
// All stats max at 100

//s * speed
//a * max maxAge
//r * reproductive chance

public class Blob {
	public Color color = new Color(200, 0, 0);

	public boolean dead = false;
	
	public int width = 50;
	
	public double x;
	public double y;
	
	public double health = 50;
	public int maxHealth = width;
	
	int age = 0;
	int foodEaten = 0;
	
	int speed = 4;
	int maxAge = 75;
	int reChance = 0;
	
	public Blob(int x, int y, int speed, int maxAge, int reChance) {
		this.x = x;
		this.y = y;
		
		this.speed = speed;
		this.maxAge = maxAge;
		this.reChance = reChance;
		
	}
	
	public Graphics2D draw(Graphics2D g) {
	
		g = drawBlob(g);
		g = drawStats(g);
		g = drawHealth(g);
		
		return g;
	}
	
	public void update() {
		
		this.age += 1;
		if (this.age / 16 > maxAge) {
			this.dead = true;
		}
		
		if (age > 80) {
			findNearestFood();
			manageHealth();
			manageReproducing();
		}
		
	}
	
	private void moveToFood(int x, int y) {
		
		double speed = (double) this.speed/100 * 5;
		
		double state = 1;
		
		double difX = x - this.x;
		double difY = y - this.y;
		
		// correcting
		
		if (difX < 0) {
			speed = speed * -1;
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
		
		double moveX = (difX / totalDif) * speed;
		double moveY = (difY / totalDif) * speed;
		
		
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
				this.foodEaten += 1;
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
	
	public void manageReproducing() {
		if (rand.nextInt((100 - reChance) * 30) == 5 && this.health > 20 && this.foodEaten > 6) {
			Blob.dublicate((int) this.x, (int) this.y, mutate(this.speed), mutate(this.maxAge), mutate(this.reChance));
		}
	}
	
	private int mutate(int number) {
		
		int newNumber = number + rand.nextInt(20) - 10;
		
		if (newNumber < 0 || newNumber > 99) {
			newNumber = number;
		}
		
		return newNumber;
	}
	
	///////// Drawing //////////////
	
	public Graphics2D drawBlob(Graphics2D g) {
		
		this.color = new Color((int) (speed / 100 * 255), (int) (((double)maxAge / 100) * 255), (int) ((double)reChance / 100 * 255));
		g.setColor(this.color);
		
		g.fillOval((int) x - width/2, (int)  y - width/2, width, width);
		
		return g;
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
	
	public Graphics2D drawStats(Graphics2D g)  {
		
		g.setColor(new Color(0, 0, 0));
		
		g.drawString("s" + (int) speed, (int) this.x - 35, (int) this.y + 47);

		g.drawString("a" + (int) maxAge, (int) this.x - 5, (int) this.y + 47);
		
		g.drawString("r" + (int) reChance, (int) this.x + 25, (int) this.y + 47);
		
		return g;
	}
	
	
	///////////// Static //////////////////////
	
	static Random rand = new Random(); 
	
	static ArrayList<Blob> blobArray = new ArrayList<Blob>();
	static int blobAmount = 10; 
	
	static Graphics2D manageBlob(Graphics2D g) {
		for (int i = 0; i < blobArray.size(); i++) {
			if (!blobArray.get(i).dead) {
				g = blobArray.get(i).draw(g);
				blobArray.get(i).update();
				
			} else {
				blobArray.remove(i);
			}
		}
		
		return g;
	}
	
	static void makeBlobArray() {
		for (int i = 0; i < blobAmount; i++) {
			blobArray.add(new Blob(rand.nextInt(MainPanel.WIDTH), rand.nextInt(MainPanel.WIDTH), rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
		}
		blobAmount = 1;
	}
	
	static void dublicate(int x, int y, int speed, int maxAge, int reChance) {
		blobArray.add(new Blob(x, y, speed, maxAge, reChance));
	}
}

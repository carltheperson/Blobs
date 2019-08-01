package blobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;


public class Food {
	private int width = 20;
	private Color color = new Color(0, 255, 0);
	
	public int x;
	public int y;
	
	public Random rand = new Random(); 
	
	public Food() {
		makeRandomPosition();
	}
	
	public void makeRandomPosition() {
		this.x = rand.nextInt(MainPanel.WIDTH);
		this.y = rand.nextInt(MainPanel.WIDTH);
	}
	
	public Graphics2D draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x - width/2, y - width/2, width, width);
		
		return g;
	}
	
	public void update() {
		
	}
	
	static ArrayList<Food> foodArray = new ArrayList<Food>();
	static int foodAmount = 60;
	static Random rand1 = new Random(); 
	
	static Graphics2D manageFood(Graphics2D g) {
		if (foodArray.size() == 0) {
			makeFoodArray();
		}
		
		if (foodArray.size() < foodAmount && rand1.nextInt(4) == 1) {
			foodArray.add(new Food());
		}
		
		for (int i = 0; i < foodArray.size(); i++) {
			g = foodArray.get(i).draw(g);
			foodArray.get(i).update();
		}
		
		return g;
	}
	
	static void makeFoodArray() {
		for (int i = 0; i < foodAmount; i++) {
			foodArray.add(new Food());
		}
	}
}

package blobs;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Blobs");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MainPanel());
		window.pack();
		window.setVisible(true);
		window.setResizable(false);
	}
	
}

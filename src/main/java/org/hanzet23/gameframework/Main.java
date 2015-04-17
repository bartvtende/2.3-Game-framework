package main.java.org.hanzet23.gameframework;

import java.awt.Dimension;
import java.awt.Toolkit;

import main.java.org.hanzet23.gameframework.views.MainView;

/**
 * Main methods for this application
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class Main {
	
	/**
	 * Main method for starting the game framework
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize the main view
		MainView frame = new MainView();
		
		// Set a fixed size of 525 by 200
		frame.setPreferredSize(new Dimension(525, 200));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set the spawning location to the middle of the screen
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(width / 2, height / 2);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
	
}
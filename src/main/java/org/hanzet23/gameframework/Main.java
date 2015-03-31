package main.java.org.hanzet23.gameframework;

import java.awt.Dimension;
import java.awt.Toolkit;

import main.java.org.hanzet23.gameframework.views.MainView;

public class Main {
	public static void main(String[] args) {
		MainView frame = new MainView();
		frame.setPreferredSize(new Dimension(525, 200));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(width / 2, height / 2);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
}
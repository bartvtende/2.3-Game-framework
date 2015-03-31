package main.java.org.hanzet23.gameframework;

import java.awt.Dimension;

import main.java.org.hanzet23.gameframework.views.MainView;

public class Main {
	public static void main(String[] args) {
		MainView frame = new MainView();
		frame.setPreferredSize(new Dimension(525, 200));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
}
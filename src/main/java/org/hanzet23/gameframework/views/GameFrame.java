package main.java.org.hanzet23.gameframework.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.controllers.GameControls;
import main.java.org.hanzet23.gameframework.controllers.MenuBar;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel = new GamePanel();

	public GameFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setJMenuBar(new MenuBar());
		this.add(gamePanel, BorderLayout.CENTER);
		//this.add(new GameControls(), BorderLayout.EAST);
	}

}

package main.java.org.hanzet23.gameframework.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.java.org.hanzet23.gameframework.controllers.GameControls;
import main.java.org.hanzet23.gameframework.controllers.MenuBar;
import main.java.org.hanzet23.gameframework.controllers.NewConnection;
import main.java.org.hanzet23.gameframework.controllers.NewGame;

public class GameFrame extends JFrame {
	
	private GamePanel gamePanel = new GamePanel();
	
	
	public GameFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setJMenuBar(new MenuBar());
		this.add(gamePanel, BorderLayout.CENTER);
		this.add(new GameControls(), BorderLayout.EAST);
	}
	

}

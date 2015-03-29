package main.java.org.hanzet23.gameframework.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.controllers.ConnectionPanel;
import main.java.org.hanzet23.gameframework.controllers.GamesPanel;
import main.java.org.hanzet23.gameframework.controllers.PlayerPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ConnectionPanel connection = new ConnectionPanel();
	public GamesPanel games = new GamesPanel();
	public PlayerPanel players = new PlayerPanel(); 
	public static GamePanel gamePanel;
	
	public GamePanel() {
		gamePanel = this;
		this.setLayout(new GridLayout(1,0, 5, 0));
		this.add(connection);
		this.add(games);
		this.add(players);
	}
	
	public PlayerPanel getPlayers(){
		return players;
	}

}

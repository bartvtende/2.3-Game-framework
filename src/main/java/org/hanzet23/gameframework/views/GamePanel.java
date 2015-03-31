package main.java.org.hanzet23.gameframework.views;

import java.awt.GridLayout;

import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.controllers.ConnectionController;
import main.java.org.hanzet23.gameframework.controllers.GamesController;
import main.java.org.hanzet23.gameframework.controllers.PlayerController;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static GamePanel gamePanel;
	
	public ConnectionController connection = new ConnectionController();
	public GamesController games = new GamesController();
	public PlayerController players = new PlayerController();
	
	public GamePanel() {
		gamePanel = this;
		this.setLayout(new GridLayout(1,0, 5, 0));
		this.add(connection);
		this.add(games);
	}
	
	public GamesController getGamesPanel(){
		return games;
	}
	
	public PlayerController getPlayers(){
		return players;
	}
	
	public void activatePlayers(){
		System.out.println("Fire activate");
		if (!this.isAncestorOf(players)) {
			this.add(players, 2);
			this.getRootPane().revalidate();
		}
	}
	
	public void removePlayers(){
		if(this.isAncestorOf(players)){
			this.remove(2);
			this.getRootPane().revalidate();
		}
	}

}

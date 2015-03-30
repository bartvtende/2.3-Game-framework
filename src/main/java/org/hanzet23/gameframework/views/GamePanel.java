package main.java.org.hanzet23.gameframework.views;

import java.awt.GridLayout;

import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.controllers.ConnectionPanel;
import main.java.org.hanzet23.gameframework.controllers.GamesPanel;
import main.java.org.hanzet23.gameframework.controllers.PlayerPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static GamePanel gamePanel;
	
	public ConnectionPanel connection = new ConnectionPanel();
	public GamesPanel games = new GamesPanel();
	public PlayerPanel players = new PlayerPanel();
	
	public GamePanel() {
		gamePanel = this;
		this.setLayout(new GridLayout(1,0, 5, 0));
		this.add(connection);
		this.add(games);
		
	}
	
	public GamesPanel getGamesPanel(){
		return games;
	}
	
	public PlayerPanel getPlayers(){
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

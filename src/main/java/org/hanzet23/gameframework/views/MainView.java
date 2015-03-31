package main.java.org.hanzet23.gameframework.views;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.controllers.ConnectionController;
import main.java.org.hanzet23.gameframework.controllers.GamesController;
import main.java.org.hanzet23.gameframework.controllers.MenuBarController;
import main.java.org.hanzet23.gameframework.controllers.PlayersController;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	public static MainView mainview;
	public static Container cp;
	
	// Panels
	public ConnectionController connection = new ConnectionController();
	public GamesController games = new GamesController();
	public PlayersController players = new PlayersController();

	public MainView() {
		mainview = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setJMenuBar(new MenuBarController());
		
		this.setupContentPane();
		this.activateConnection();
		
	}
	
	private void setupContentPane(){
		cp = this.getContentPane();
		cp.setLayout(new GridLayout(1,0,5,0));
		
	}
	
	public void activateConnection(){
		if (!cp.isAncestorOf(connection)) {
			cp.add(connection, 0);
			this.getRootPane().revalidate();
		}
	}
	
	public void removeConnection(){
		if(cp.isAncestorOf(connection)){
			cp.remove(0);
			this.getRootPane().revalidate();
		}
	}
	
	public void activateGames(){
		if (!cp.isAncestorOf(games)) {
			cp.add(games, 1);
			this.getRootPane().revalidate();
		}
	}
	
	public void removeGames(){
		if(cp.isAncestorOf(games)){
			cp.remove(1);
			this.getRootPane().revalidate();
		}
	}
	
	public void activatePlayers(){
		if (!cp.isAncestorOf(players)) {
			cp.add(players, 2);
			this.getRootPane().revalidate();
		}
	}
	
	public void removePlayers(){
		if(cp.isAncestorOf(players)){
			cp.remove(2);
			this.getRootPane().revalidate();
		}
	}
}

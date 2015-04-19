package main.java.org.hanzet23.gameframework.views;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.controllers.ConnectionController;
import main.java.org.hanzet23.gameframework.controllers.GamesController;
import main.java.org.hanzet23.gameframework.controllers.MenuBarController;
import main.java.org.hanzet23.gameframework.controllers.PlayersController;

/**
 * Frame that contains all of the game setup.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	public static MainView mainview;
	public static Container cp;

	// Panels
	public static ConnectionController connection = new ConnectionController();
	public static GamesController games = new GamesController();
	public static PlayersController players = new PlayersController();

	/**
	 * Constructor for the MainView.
	 */
	public MainView() {
		mainview = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setJMenuBar(new MenuBarController());

		this.setupContentPane();
		this.activateConnection();
	}

	/**
	 * Sets up the layout of the contentPane.
	 */
	private void setupContentPane() {
		cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, 0, 5, 0));

	}

	/**
	 * Displays the ConnectionController.
	 */
	public void activateConnection() {
		if (!cp.isAncestorOf(connection)) {
			cp.add(connection, 0);
			this.getRootPane().revalidate();
		}
	}

	/**
	 * Stops Displaying the ConnectionController.
	 */
	public void removeConnection() {
		if (cp.isAncestorOf(connection)) {
			cp.remove(connection);
			this.getRootPane().revalidate();
		}
	}

	/**
	 * Displays the GamesController.
	 */
	public void activateGames() {
		if (!cp.isAncestorOf(games)) {
			cp.add(games, 1);
			this.getRootPane().revalidate();
		}
	}

	/**
	 * Stops displaying the GamesController.
	 */
	public void removeGames() {
		if (cp.isAncestorOf(games)) {
			cp.remove(games);
			this.getRootPane().revalidate();
		}
	}

	/**
	 * Displays the PlayersController.
	 */
	public void activatePlayers() {
		if (!cp.isAncestorOf(players)) {
			cp.add(players, 2);
			this.getRootPane().revalidate();
		}
	}

	/**
	 * Stops displaying the PlayersController.
	 */
	public void removePlayers() {
		if (cp.isAncestorOf(players)) {
			cp.remove(players);
			this.getRootPane().revalidate();
		}
	}
}

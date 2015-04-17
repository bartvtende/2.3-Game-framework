package main.java.org.hanzet23.gameframework.models;

/**
 * Main class for a game, contains the player and the game objects Delegates
 * events to the server to the corresponding methods
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class BoardModel {

	public PlayerModel player = null;
	public GameModel game = null;

	/**
	 * Constructor for the BoardModel
	 * 
	 * @param player
	 * @param game
	 */
	public BoardModel(PlayerModel player, GameModel game) {
		this.player = player;
		this.game = game;
	}

	/**
	 * Starts the game
	 */
	public void startGame() {
		game.startGame();
	}

	/**
	 * Stops the game
	 */
	public void stopGame() {
		game.stopGame();
	}

	/**
	 * Delegates the move to either the human or the computer (AI)
	 */
	public void move() {
		if (player.getPlayerType().equalsIgnoreCase("Human")) {
			// The player is a human
			game.moveHuman();
		} else if (player.getPlayerType().equalsIgnoreCase("Computer")) {
			// The player is a computer (AI)
			game.moveComputer();
		} else {
			// Something went wrong
			System.out
					.println("Something went wrong when making a move, try again");
		}
	}

}

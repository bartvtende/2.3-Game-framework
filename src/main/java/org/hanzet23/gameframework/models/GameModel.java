package main.java.org.hanzet23.gameframework.models;

/**
 * An abstract class that is extended by the game class
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public abstract class GameModel {

	// The x and y axis of the board
	public final int BOARD_RANGE = 0;

	// The game board containing a 2d array of chars
	public char[][] board = null;

	// The name of the game
	private String gameName = null;

	private final char EMPTY = 'E';

	/**
	 * Constructor for the GameModel
	 * 
	 * @param gameName
	 */
	public GameModel(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * Adds a character to the board, given a position
	 * 
	 * @param position
	 * @param identifier
	 */
	public void addItemToBoard(String positionString, char identifier) {
		// Parses the string into an integer
		int position = (int) Integer.parseInt(positionString);

		// Calculates the x and y axis of the position
		int x = (int) Math.floor(position / board.length);
		int y = position % board.length;

		// Gets the character of the opponent
		char opp;
		if (identifier == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		// Place the character on the board if it isn't filled with the opponent
		// char
		if (board[x][y] != opp)
			board[x][y] = identifier;
	}

	/**
	 * Initializes the game board, fills it with the 'E' character
	 */
	public void initializeBoard() {
		// Loop through the board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				// Fill it with a 'E' character
				board[i][j] = EMPTY;
			}
		}
	}

	/**
	 * Places a move on the game board of the selected game
	 * 
	 * @param identifier
	 * @param move
	 * @param game
	 */
	public abstract void placeMove(char identifier, int move, boolean game);

	/**
	 * Gets called when the game is set to human and it's this users turn
	 */
	public abstract void moveHuman();

	/**
	 * Gets called when the game is set to computer and it's this users turn
	 */
	public abstract void moveComputer();

	/**
	 * Starts the game by adding the views and initializing the game board
	 */
	public abstract void startGame();

	/**
	 * Stops the game by removing the views
	 */
	public abstract void stopGame();

	/**
	 * Getter for the 2d array game board
	 * 
	 * @return
	 */
	public char[][] getBoard() {
		return board;
	}

	/**
	 * Getter for the game name
	 * 
	 * @return
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * Helper method to print the chars within the 2d array game board
	 */
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}

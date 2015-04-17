package main.java.org.hanzet23.gameframework.games.othello;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.views.MainView;

/**
 * Class implementing the basic behavior of the Othello game
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class OthelloModel extends GameModel {

	// The x and y axis of this game board
	private final int BOARD_RANGE = 8;

	// The AI object, easily changeable to another AI class
	public OthelloGreedyAI ai = null;

	// Keeps track of the turns that the user plays
	private int turnCounter = 0;

	// Views for the Othello view
	private JFrame boardFrame;
	public OthelloView boardView;

	// Static instance of this model
	public static OthelloModel othelloModel;

	/**
	 * Constructor for the OthelloModel
	 * 
	 * @param gameName
	 */
	public OthelloModel(String gameName) {
		super(gameName);

		// Create a new board
		this.board = new char[BOARD_RANGE][BOARD_RANGE];

		// Set the othelloModel object to the current object
		othelloModel = this;

		// Create a new AI instance for this class, easily changeable to another
		// AI
		ai = new OthelloGreedyAI();
	}

	@Override
	public void moveHuman() {
		boardView.refresh(board);
	}

	@Override
	public void moveComputer() {
		// Increment the turn counter
		turnCounter++;

		// Sleep for 2 seconds
		sleep(2);

		// Get the tile of the current player
		char tile = NetworkModel.board.player.getTile();

		// Calculate the best move with the selected algorithm
		OthelloMove move = ai.getBestMove(tile, board, turnCounter);

		// Send to server
		NetworkModel network = NetworkModel.getInstance();
		String position = Integer.toString(move.getPosition());
		network.getOutput().move(position);

		// Add to board
		placeMove(tile, move.getPosition(), false);
	}

	@Override
	public void placeMove(char identifier, int move, boolean game) {
		// If this is a request for the opponent, get the opponent tile
		if (game) {
			char tileOpp = NetworkModel.board.player.getTileOpp();
			identifier = tileOpp;
		}

		// Convert the integer to an OthelloMove object
		OthelloMove newMove = new OthelloMove(move);
		
		// Place the move on the board
		this.board = ai.place(identifier, newMove, board);
		
		// Refresh the board
		boardView.refresh(board);
	}

	@Override
	public void startGame() {
		// Add the Othello view
		JFrame frame = new JFrame();
		this.boardFrame = frame;
		OthelloView boardView = new OthelloView();
		this.boardView = boardView;
		frame.getContentPane().add(boardView);
		frame.setVisible(true);
		frame.pack();

		// Set the Othello view to the correct spawning location
		int x = 0;
		int y = 0;

		MainView main = MainView.mainview;
		x = main.getLocation().x + (main.getContentPane().getWidth() + 50);
		y = main.getLocation().y
				+ (main.getContentPane().getHeight() - boardFrame.getHeight());
		boardFrame.setLocation(x, y);

		// Initialize and refresh the board
		initializeBoard();
		boardView.refresh(board);
	}

	@Override
	public void stopGame() {
		// Remove the view
		if (boardFrame != null) {
			boardFrame.dispose();
			boardFrame = null;
		}
	}

	@Override
	public void initializeBoard() {
		super.initializeBoard();

		// Initialize the first four stones for Othello
		board[3][3] = 'O';
		board[3][4] = 'X';
		board[4][3] = 'X';
		board[4][4] = 'O';
	}

}

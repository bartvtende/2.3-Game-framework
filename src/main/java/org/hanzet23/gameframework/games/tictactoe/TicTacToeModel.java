package main.java.org.hanzet23.gameframework.games.tictactoe;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.views.MainView;

/**
 * Class implementating the basic behaviour of the TicTacToe game
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class TicTacToeModel extends GameModel {

	// The x and y axis of this game board
	private final int BOARD_RANGE = 3;

	// Views for the TicTacToe view
	private JFrame boardFrame;
	private TicTacToeView boardView;
	
	// Static instance of this model
	public static TicTacToeModel TTTModel;

	/**
	 * Constructor for the TicTacToeModel
	 * @param gameName
	 */
	public TicTacToeModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
		initializeBoard();
		TTTModel = this;
	}

	@Override
	public void moveHuman() {
		// Refresh board
		boardView.refresh(board);
	}

	@Override
	public void moveComputer() {
		// Refresh board
		boardView.refresh(board);

		// Sleep for 2 seconds
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		// Call to the AI
		TicTacToeAI AI = new TicTacToeAI(board);
		
		// Get the best move from our AI
		int position = AI.chooseMove();
		
		// Send the move to the server
		NetworkModel network = NetworkModel.getInstance();
		network.getOutput().move(Integer.toString(position));
		
		// Add this move to the game board
		placeMove('X', position, false);
		
		// Print the board in the console
		printBoard();
	}

	@Override
	public void startGame() {
		System.out.println("Gameview wordt aangemaakt");
		// Start the TicTacToe view
		boardFrame = new JFrame();
		boardView = new TicTacToeView();
		boardFrame.getContentPane().add(boardView);

		boardFrame.setVisible(true);
		boardFrame.pack();
		// Set the fixed size
		boardFrame.getContentPane().setSize(BOARD_RANGE * 50, BOARD_RANGE * 50);
		boardFrame.setResizable(false);
		
		// Location popup
		int x;
		int y;
		
		MainView main = MainView.mainview;
		
		x = main.getLocation().x + (main.getContentPane().getWidth()+50);
		y = main.getLocation().y + (main.getContentPane().getHeight()-boardFrame.getHeight());
		
		boardFrame.setLocation(x, y);
	}

	@Override
	public void stopGame() {
		// Remove the view
		if (boardFrame != null) {
			boardFrame.dispose();
			boardFrame = null;
		}
	}

	/**
	 * Getter for the TicTacToeView object
	 * @return
	 */
	public TicTacToeView getBoardView() {
		return boardView;
	}

	@Override
	public void placeMove(char identifier, int move, boolean game) {
		// Parse the integer to a string
		String newMove = Integer.toString(move);
		
		// Make a call to the addItemToBoard method
		NetworkModel.board.game.addItemToBoard(newMove, identifier);
		
		// Refresh the board
		boardView.refresh(board);
	}

}

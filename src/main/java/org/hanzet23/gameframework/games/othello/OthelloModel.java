package main.java.org.hanzet23.gameframework.games.othello;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.views.MainView;

public class OthelloModel extends GameModel {
	
	private final int BOARD_RANGE = 8;
	
	private static OthelloMinimaxAI ai = null;
	private int turnCounter = 0;
	
	private JFrame boardFrame;
	public BoardView boardView;
	public static OthelloModel othelloModel;

	public OthelloModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
		othelloModel = this;
		ai = new OthelloMinimaxAI();
	}

	@Override
	public void moveHuman() {
		boardView.refresh(board);
	}

	@Override
	public void moveComputer() {
		// Increment the turn counter
		turnCounter++;
		
		// Sleep to compensate latency
		sleep(4);

		// Get the tile of the current player
		char tile = NetworkModel.board.player.getTile();
		
		// Calculate the best move with the greedy algorithm
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
		BoardView boardView = new BoardView();
		this.boardView = boardView;
		frame.getContentPane().add(boardView);
		frame.setVisible(true);
		frame.pack();
		
		// Set the Othello view to the correct spawning location
		int x = 0;
		int y = 0;
		
		MainView main = MainView.mainview;
		x = main.getLocation().x + (main.getContentPane().getWidth()+50);
		y = main.getLocation().y + (main.getContentPane().getHeight()-boardFrame.getHeight());
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
	
	private void sleep(int seconds) {
		seconds *= 100;
		try {
            Thread.sleep(seconds);
		} catch (Exception e) {
			System.out.println(e);
        }
	}

}

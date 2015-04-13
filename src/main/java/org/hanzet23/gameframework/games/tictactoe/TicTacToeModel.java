package main.java.org.hanzet23.gameframework.games.tictactoe;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;

public class TicTacToeModel extends GameModel {

	private final int BOARD_RANGE = 3;
	private JFrame boardFrame;
	private BoardView boardView;
	public static TicTacToeModel TTTModel;

	public TicTacToeModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
		initializeBoard();
		TTTModel = this;
	}

	@Override
	public void moveHuman() {
		// Luister naar de actionlisteners
		for (int i = 0; i < BOARD_RANGE; i++) {
			for (int j = 0; j < BOARD_RANGE; j++) {
				boardView.getTile(i, j).setEnabled(true);
			}
		}
	}

	@Override
	public void moveComputer() {
		// Refresh board
		boardView.refresh(board);
		
		String position = null;
		
		try {
		    Thread.sleep(4000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		// Gebruik minimax AI
		for (int i = 0; i < BOARD_RANGE; i++) {
			for (int j = 0; j < BOARD_RANGE; j++) {
				if (board[i][j] == 'E') {
					position = Integer.toString(i * board.length + j);
				}
			}
		}

		if (position != null) {		
			// Send to server
			NetworkModel network = NetworkModel.getInstance();
			network.getOutput().move(position);
			
			// Add to board
			addItemToBoard(position, 'X');
			
			// Refresh board
			boardView.refresh(board);
		}

		printBoard();
	}

	@Override
	public void startGame() {
		System.out.println("Gameview wordt aangemaakt");
		// Maak view
		boardFrame = new JFrame();
		boardView = new BoardView();
		boardFrame.getContentPane().add(boardView);

		boardFrame.setVisible(true);
		boardFrame.pack();
		// yea, yea, hardcoded vars, i know, bad me
		boardFrame.getContentPane().setSize(BOARD_RANGE * 50, BOARD_RANGE * 50);
		boardFrame.setResizable(false);
	}

	@Override
	public void stopGame() {
		// Remove the view
		if (boardFrame != null) {
			boardFrame.dispose();
			boardFrame = null;
		}
	}

	public BoardView getBoardView() {
		return boardView;
	}

}

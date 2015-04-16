package main.java.org.hanzet23.gameframework.games.tictactoe;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.views.MainView;

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
		int position = AI.chooseMove();

		NetworkModel network = NetworkModel.getInstance();
		network.getOutput().move(Integer.toString(position));
		// Send to server and add to board
		placeMove('X', position, false);
		
		// Print the board in the console for testing purposes
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
		// Set the fixed size
		boardFrame.getContentPane().setSize(BOARD_RANGE * 50, BOARD_RANGE * 50);
		boardFrame.setResizable(false);
		
		//location popup
		int x;
		int y;
		
		MainView main = MainView.mainview;
		
		x = main.getLocation().x + (main.getContentPane().getWidth()+50);
		y = main.getLocation().y + (main.getContentPane().getHeight()-boardFrame.getHeight());
		
		
		boardFrame.setLocation(x, y);
		//boardFrame.setLocationRelativeTo(MainView.mainview);
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

	@Override
	public void placeMove(char identifier, int move, boolean game) {
		String newMove = Integer.toString(move);
		NetworkModel.board.game.addItemToBoard(newMove, identifier);
		boardView.refresh(board);
	}

}

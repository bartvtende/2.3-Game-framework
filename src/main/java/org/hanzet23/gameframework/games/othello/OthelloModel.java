package main.java.org.hanzet23.gameframework.games.othello;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.games.othello.BoardView;
import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;

public class OthelloModel extends GameModel {
	
	private final int BOARD_RANGE = 8;
	
	private JFrame boardFrame;
	private BoardView boardView;
	public static OthelloModel OthelloModel;

	public OthelloModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
		initializeBoard();
		OthelloModel = this;
	}

	@Override
	public void moveHuman() {
		// TODO Auto-generated method stub
		// boardFrame.refresh(board);
	}

	@Override
	public void moveComputer() {
		// TODO Auto-generated method stub
		boardView.refresh(board);
		// Send to server
		NetworkModel network = NetworkModel.getInstance();
		network.getOutput().move("1");

		// Add to board
		addItemToBoard("1", 'X');

		boardView.refresh(board);
	}

	@Override
	public void startGame() {
		// TODO: doesn't work!
		JFrame frame = new JFrame();
		this.boardFrame = frame;
		BoardView boardView = new BoardView();
		this.boardView = boardView;
		frame.getContentPane().add(boardView);
		frame.setVisible(true);
		frame.pack();
	}

	@Override
	public void stopGame() {
		// Remove the view
		if (boardFrame != null) {
			boardFrame.dispose();
			boardFrame = null;
		}
	}

}

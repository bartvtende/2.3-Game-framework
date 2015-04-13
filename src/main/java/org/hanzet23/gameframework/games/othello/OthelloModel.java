package main.java.org.hanzet23.gameframework.games.othello;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.games.othello.BoardView;
import main.java.org.hanzet23.gameframework.models.GameModel;

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
		// boardFrame.refresh(board);		
	}

	@Override
	public void startGame() {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BoardView());
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

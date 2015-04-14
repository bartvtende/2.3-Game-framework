package main.java.org.hanzet23.gameframework.games.othello;



import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.games.othello.BoardView;
import main.java.org.hanzet23.gameframework.models.BoardModel;
import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;

public class OthelloModel extends GameModel {
	
	public static final int PLAYER_ONE = 0;
	public static final int PLAYER_TWO = 1;
	
	public static final int STATE_DRAW = 2;
	public static final int STATE_UNKNOWN = 3; //Hierbij is de uitkomst van het spel nog niet bepaald, zie OthelloMove
	public static final int EMPTY = 2;
	
	private final int BOARD_RANGE = 8;
	
	private JFrame boardFrame;
	private BoardView boardView;
	public static OthelloModel OthelloModel;

	public OthelloModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
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
		// NetworkModel network = NetworkModel.getInstance();
		try {
            // thread to sleep for 10 seconds
            Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
        }
		//network.getOutput().move("1");

		// Add to board
		//addItemToBoard("1", 'X');

		boardView.refresh(board);
	}

	@Override
	public void startGame() {
		JFrame frame = new JFrame();
		this.boardFrame = frame;
		BoardView boardView = new BoardView();
		this.boardView = boardView;
		frame.getContentPane().add(boardView);
		frame.setVisible(true);
		frame.pack();
		
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
		char tile = NetworkModel.board.player.getTile();
		char opp;

		if (tile == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}
		
		board[3][3] = tile;
		board[3][4] = opp;
		board[4][4] = tile;
		board[4][3] = opp;
	}

}

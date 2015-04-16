package main.java.org.hanzet23.gameframework.games.othello;



import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.models.GameModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.views.MainView;

public class OthelloModel extends GameModel {
	
	public static final int PLAYER_ONE = 0;
	public static final int PLAYER_TWO = 1;
	
	public static final int STATE_DRAW = 2;
	public static final int STATE_UNKNOWN = 3; //Hierbij is de uitkomst van het spel nog niet bepaald, zie OthelloMove
	public static final int EMPTY = 2;
	
	private final int BOARD_RANGE = 8;
	
	private JFrame boardFrame;
	public BoardView boardView;
	public static OthelloModel OthelloModel;

	public OthelloModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
		OthelloModel = this;
	}

	@Override
	public void moveHuman() {
		// TODO Auto-generated method stub
		boardView.refresh(board);
	}

	@Override
	public void moveComputer() {
		boardView.refresh(board);
		
		try {
            // thread to sleep for 1 seconds
            Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e);
        }
		
		char tile = NetworkModel.board.player.getTile();
		
		OthelloRandomAI ai = new OthelloRandomAI();
		ArrayList<OthelloMove> list = ai.getValidMoves(tile, board);
		
		for (OthelloMove move : list) {
			System.out.println("FOUND MOVE: " + move.toString());
		}

		int moves = list.size();

		if (moves == 1) {
			moves = 2;
		}

		OthelloMove move = list.get(new Random().nextInt(moves - 1));
		
		// Send to server
		NetworkModel network = NetworkModel.getInstance();
		String position = Integer.toString(move.x * 8 + move.y);
		network.getOutput().move(position);
		
		// Add to board
		placeMove(tile, move.getValue(), false);

		boardView.refresh(board);
	}
	
	@Override
	public void placeMove(char identifier, int move, boolean game) {
		if (game) {
			char tileOpp = NetworkModel.board.player.getTileOpp();
			identifier = tileOpp;
		}
		
		OthelloMove newMove = new OthelloMove(move/8, move%8);
		OthelloRandomAI ai = new OthelloRandomAI();
		this.board = ai.place(identifier, newMove, board);
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
		
		//location popup
		int x;
		int y;
		
		MainView main = MainView.mainview;
		
		x = main.getLocation().x + (main.getContentPane().getWidth()+50);
		y = main.getLocation().y + (main.getContentPane().getHeight()-boardFrame.getHeight());
		
		
		boardFrame.setLocation(x, y);
		
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
		/*
		char tile = NetworkModel.board.player.getTile();
		char opp;

		if (tile == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}*/
		
		board[3][3] = 'O';
		board[3][4] = 'X';
		board[4][3] = 'X';
		board[4][4] = 'O';
	}

}

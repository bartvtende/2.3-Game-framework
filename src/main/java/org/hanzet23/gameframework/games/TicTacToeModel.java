package main.java.org.hanzet23.gameframework.games;

import javax.swing.JFrame;

import main.java.org.hanzet23.gameframework.games.tictactoe.BoardView;

public class TicTacToeModel extends GameModel {
	
	private final int BOARD_RANGE = 3;
	private JFrame boardFrame;
	private BoardView boardView;
	public static TicTacToeModel TTTModel;

	public TicTacToeModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
		TTTModel = this;
	}

	@Override
	public void moveHuman() {
		// Luister naar de actionlisteners
		for(int i = 0; i<BOARD_RANGE;i++){
			for(int j = 0; j<BOARD_RANGE; i++){
				boardView.getTile(i, j).setEnabled(true);
			}
		}
	}

	@Override
	public void moveComputer() {
		// Gebruik minimax AI
	}

	@Override
	public void startGame() {
		// Maak view
		boardFrame = new JFrame();
		boardView = new BoardView();
		boardFrame.getContentPane().add(boardView);
		
		boardFrame.setVisible(true);
		boardFrame.pack();
		//yea, yea, hardcoded vars, i know, bad me
		boardFrame.getContentPane().setSize(BOARD_RANGE*50, BOARD_RANGE*50);
		boardFrame.setResizable(false);
	}

	@Override
	public void stopGame() {
		// Remove the view
		if(boardFrame != null){
			boardFrame.dispose();
			boardFrame = null;
		}
	}
	
	public BoardView getBoardView(){
		return BoardView;
	}
	
}

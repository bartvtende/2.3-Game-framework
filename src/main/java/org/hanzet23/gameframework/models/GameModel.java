package main.java.org.hanzet23.gameframework.models;

public abstract class GameModel {
	
	public final int BOARD_RANGE = 0;
	
	protected char[][] board = null;
	
	private String gameName = null;
	
	public GameModel(String gameName) {
		this.gameName = gameName;
	}
	
	/**
	 * Adds a character to the board, given a position
	 * 
	 * @param position
	 * @param identifier
	 */
	public void addItemToBoard(int position, char identifier) {
		int x = (int) Math.floor(position / board.length);
		int y = position % board.length;
		
		board[x][y] = identifier;
	}

	public abstract void moveHuman();
	
	public abstract void moveComputer();
	
	public abstract void startGame();
	
	public abstract void stopGame();
	
	public char[][] getBoard() {
		return board;
	}
	
	public String getGameName() {
		return gameName;
	}
	
}

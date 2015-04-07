package main.java.org.hanzet23.gameframework.games;

public abstract class GameModel {
	
	public final int BOARD_RANGE = 0;
	
	protected char[][] board = null;
	
	private String gameName = null;
	
	public GameModel(String gameName) {
		this.gameName = gameName;
	}

	public abstract void moveHuman();
	
	public abstract void moveComputer();
	
	public char[][] getBoard() {
		return board;
	}
	
	public String getGameName() {
		return gameName;
	}
	
}

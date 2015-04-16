package main.java.org.hanzet23.gameframework.games.othello;

public class OthelloBoard {
	
	/**
	 * Calculates how much tiles a player has on the board
	 * 
	 * @param player
	 * @param board
	 * @return
	 */
	public int calculateTiles(char player, char[][] board) {
		int counter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == player) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	
	/**
	 * Helper method that makes a copy of the 2d Othello board
	 * @param board
	 * @return
	 */
	public char[][] cloneBoard(char[][] board) {
		// Copy the board
		char[][] tempBoard = new char[board.length][];
		for (int j = 0; j < board.length; j++) {
			tempBoard[j] = board[j].clone();
		}
		return tempBoard;
	}
	
	
	public int countTiles(char[][] board, char identifier) {
		int counter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == identifier) {
					counter++;
				}
			}
		}
		return counter;
	}
	
}

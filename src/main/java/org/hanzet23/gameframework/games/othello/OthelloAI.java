package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

/**
 * An abstract class that is extended by the algorithms for Othello
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public abstract class OthelloAI {

	// Matrix which determines how favourable a square is
	protected int[][] randomBoard = { { 30, -25, 10, 5, 5, 10, -25, 30, },
			{ -25, -25, 1, 1, 1, 1, -25, -25, }, { 10, 1, 5, 2, 2, 5, 1, 10, },
			{ 5, 1, 2, 1, 1, 2, 1, 5, }, { 5, 1, 2, 1, 1, 2, 1, 5, },
			{ 10, 1, 5, 2, 2, 5, 1, 10, }, { -25, -25, 1, 1, 1, 1, -25, -25, },
			{ 30, -25, 10, 5, 5, 10, -25, 30, } };

	/**
	 * Calculates if a move is valid for the player
	 * 
	 * @param player
	 * @param move
	 * @param board
	 * @return
	 */
	private boolean isValidMove(char player, OthelloMove move, char[][] board) {
		// Return false if the square is empty
		if (board[move.x][move.y] != 'E') {
			return false;
		}

		// Calculates the tile of the opponent
		char opp = 0;
		if (player == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		// Define the directions to search for
		int[][] directions = { { -1, -1 }, { 0, -1 }, { 1, -1 }, // Top
				{ -1, 0 }, { 1, 0 }, // Left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 } // Bottom
		};

		// Loop through all of the possible directions
		for (int dir = 0; dir < directions.length; dir++) {
			// For each direction check the pieces
			for (int dist = 1; dist < board.length; dist++) {
				// Calculate the x and y coordinates
				int x = move.x + (dist * directions[dir][0]);
				int y = move.y + (dist * directions[dir][1]);
				
				// Check for array out of bound
				if (x < 0 || x >= board.length || y < 0 || y >= board.length) {
					break;
				}

				// Get the tile from the coordinates
				char tile = board[x][y];

				// Check the tiles
				if (dist == 1) {
					if (tile != opp)
						break;
				} else {
					if (tile == 'E')
						break;
					if (tile == player)
						return true;
				}
			}
		}

		// No direction includes a valid move for this position
		return false;
	}

	/**
	 * Returns an ArrayList with all of the valid moves for the given player
	 * 
	 * @param player
	 * @param board
	 * @return
	 */
	public ArrayList<OthelloMove> getValidMoves(char player, char[][] board) {
		// Initialize the ArrayList
		ArrayList<OthelloMove> validMoves = new ArrayList<OthelloMove>();

		// Loop through the board
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				// Make a new OthelloMove for every entry
				OthelloMove move = new OthelloMove(x, y);
				// If it's a valid move, add it to the list
				if (isValidMove(player, move, board)) {
					validMoves.add(move);
				}
			}
		}

		return validMoves;
	}

	/**
	 * Places an OthelloMove object on the board for a specific player
	 * 
	 * @param player
	 * @param move
	 * @param board
	 * @return
	 */
	public char[][] place(char player, OthelloMove move, char[][] board) {
		// Get the tile of the opponent
		char opp = 0;
		if (player == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		// Define the directions to search for
		int[][] directions = { { -1, -1 }, { 0, -1 }, { 1, -1 }, // Top
				{ -1, 0 }, { 1, 0 }, // Left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 } // Bottom
		};

		// Check for all the direction
		outerLoop: for (int dir = 0; dir < directions.length; dir++) {

			// Check if we can go this way, skip if not
			for (int dist = 1; true; dist++) {
				// Get the x and y coordinates of this move
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);

				// Check the tile
				if (checkX < 0 || checkY < 0 || checkX >= board.length
						|| checkY >= board.length) {
					continue outerLoop;
				} else if (dist == 1) {
					if (board[checkX][checkY] != opp)
						continue outerLoop;
				} else {
					if (board[checkX][checkY] == 'E')
						continue outerLoop;
					else if (board[checkX][checkY] == player)
						break;
				}
			}

			// Flip the tiles
			for (int dist = 1; dist < board.length; dist++) {
				// Get the x and y coordinates of this move
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);

				// Check the tile
				if (board[checkX][checkY] == opp) {
					board[checkX][checkY] = player;
				} else {
					break;
				}
			}
		}

		// Place the tile
		board[move.x][move.y] = player;
		
		// Return the game board
		return board;
	}

	/**
	 * Calculates the amount of tiles a player will get when they do a move
	 * 
	 * @param player
	 * @param move
	 * @param board
	 * @return
	 */
	public int getAmountOfStones(char player, OthelloMove move, char[][] board) {
		// Count the tiles for the old move
		int oldCount = countTiles(board, player);
		
		// Simulate a move
		board = place(player, move, board);
		
		// Count the tiles for the new move
		int newCount = countTiles(board, player);

		// Return the newCount minus the oldCount
		return newCount - oldCount;
	}

	/**
	 * Helper method that makes a copy of the 2d Othello board
	 * 
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

	/**
	 * Calculate the amount of occurrences of the given tile in the board
	 * 
	 * @param board
	 * @param identifier
	 * @return
	 */
	public int countTiles(char[][] board, char identifier) {
		int counter = 0;
		// Loop through the board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				// Check if the tile contains a tile from the given player
				if (board[i][j] == identifier) {
					// Increment the counter
					counter++;
				}
			}
		}
		return counter;
	}

}

package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

public abstract class OthelloAI {
	
	private boolean isValidMove(char player, OthelloMove move, char[][] board) {
		// Return false if the square is empty
		if (board[move.x][move.y] != 'E') {
			return false;
		}

		char opp = 0;
		if (player == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		int[][] directions = { { -1, -1 }, { 0, -1 }, { 1, -1 }, // Top
				{ -1, 0 }, { 1, 0 }, // Left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 } // Bottom
		};

		for (int dir = 0; dir < directions.length; dir++) {
			// Each direction check the pieces
			for (int dist = 1; dist < board.length; dist++) {
				int x = move.x + (dist * directions[dir][0]);
				int y = move.y + (dist * directions[dir][1]);
				// Check for array out of bound
				if (x < 0 || x >= board.length || y < 0 || y >= board.length) {
					break;
				}

				int tile = board[x][y];

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
	 * Returns an ArrayList with all of the valid moves
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
		char opp = 0;
		if (player == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		int[][] directions = { { -1, -1 }, { 0, -1 }, { 1, -1 }, // Top
				{ -1, 0 }, { 1, 0 }, // Left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 } // Bottom
		};

		// Check for all the direction
		outerLoop: for (int dir = 0; dir < directions.length; dir++) {

			// First check if we can go this way, skip if not
			for (int dist = 1; true; dist++) {
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);

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

			// Then flip the tiles
			for (int dist = 1; dist < board.length; dist++) {
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);

				if (board[checkX][checkY] == opp) {
					board[checkX][checkY] = player;
				} else {
					break;
				}
			}
		}

		board[move.x][move.y] = player;
		return board;
	}

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
	 * Calculates the amount of tiles a player will get when they do a move
	 * 
	 * @param player
	 * @param move
	 * @param board
	 * @return
	 */
	public int getAmountOfStones(char player, OthelloMove move, char[][] board) {
		int oldCount = calculateTiles(player, board);
		board = place(player, move, board);
		int newCount = calculateTiles(player, board);

		return newCount - oldCount;
	}

}

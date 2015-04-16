package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

public class OthelloSomewhatRandomAI {

	private int[][] randomBoard = {
	   { 30, -25, 10, 5, 5, 10, -25,  30,},
	   {-25, -25,  1, 1, 1,  1, -25, -25,},
	   { 10,   1,  5, 2, 2,  5,   1,  10,},
	   {  5,   1,  2, 1, 1,  2,   1,   5,},
	   {  5,   1,  2, 1, 1,  2,   1,   5,},
	   { 10,   1,  5, 2, 2,  5,   1,  10,},
	   {-25, -25,  1, 1, 1,  1, -25, -25,},
	   { 30, -25, 10, 5, 5, 10, -25,  30,}
	};
	
	public OthelloMove getBestMove(char player, char[][] board) {
		ArrayList<OthelloMove> moves = getValidMoves(player, board);
		
		int bestValue = -30;
		OthelloMove bestMove = null;
		for (int i = 0; i < moves.size(); i++) {
			OthelloMove move = moves.get(i);
			if (randomBoard[move.x][move.y] > bestValue) {
				bestValue = randomBoard[move.x][move.y];
				bestMove = move;
			}
		}
		return bestMove;
	}

	/**
	 * Checks if a OthelloMove is valid.
	 * 
	 * @param player
	 * @param move
	 * @return boolean
	 */
	private boolean isValidMove(char player, OthelloMove move, char[][] board) {
		if (board[move.x][move.y] != 'E') {
			return false;
		}

		char opp = 0;
		if (player == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		// 8 directions 2 coordinates each direction
		int[][] directions = { { -1, -1 }, { 0, -1 }, { 1, -1 }, // Top
																	// directions
				{ -1, 0 }, { 1, 0 }, // left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 } // bottom directions
		};

		for (int dir = 0; dir < directions.length; dir++) {
			// each direction check the pieces
			for (int dist = 1; dist < board.length; dist++) {
				int x = move.x + (dist * directions[dir][0]);
				int y = move.y + (dist * directions[dir][1]);
				// check for array out of bound
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
		// no direction includes a valid move for this position
		return false;
	}

	/**
	 * Function to get a list of valid moves
	 * 
	 * @return ArrayList OthelloMove objects
	 */
	public ArrayList<OthelloMove> getValidMoves(char player, char[][] board) {
		ArrayList<OthelloMove> validmoves = new ArrayList<OthelloMove>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				OthelloMove move = new OthelloMove(x, y);
				if (isValidMove(player, move, board)) {
					validmoves.add(move);
				}
			}
		}
		return validmoves;
	}

	/**
	 * Place an OthelloMove on the board.
	 * 
	 * @param player
	 * @param move
	 * @return
	 */
	public char[][] place(char player, OthelloMove move, char[][] board) {
		char opp = 0;
		if (player == 'X') {
			opp = 'O';
		} else {
			opp = 'X';
		}

		int flipped = 0;

		int[][] directions = {
				{ -1, -1 }, { 0, -1 }, { 1, -1 }, // Top directions
				{ -1, 0 }, { 1, 0 }, // left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 } // bottom directions
		};

		outerLoop: for (int dir = 0; dir < directions.length; dir++) { // Check
																		// all
																		// directions

			// first check if we can go this way, skip if not
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

			// then flip the tiles
			for (int dist = 1; dist < board.length; dist++) {
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);

				if (board[checkX][checkY] == opp) {
					board[checkX][checkY] = player;
					flipped++;
				} else {
					break;
				}
			}
		}

		board[move.x][move.y] = player;
		return board;
	}
	
	
	
}

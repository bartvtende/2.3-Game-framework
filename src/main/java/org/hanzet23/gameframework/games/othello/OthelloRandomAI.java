package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;
import java.util.Random;

public class OthelloRandomAI implements OthelloAIInterface {

	private char[][] board = null;
	private Random random = new Random();

	public OthelloRandomAI() {
		// Val in een kuil jongen!
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
		int[][] directions = { 
				{ -1, -1 }, { 0, -1 }, { 1, -1 }, // Top directions
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

	@Override
	public void setBoard(char[][] board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position[] getAvailableSpaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getRandom(Position[] positions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countGain(Position position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position bestMove() {
		// TODO Auto-generated method stub
		return null;
	}
}

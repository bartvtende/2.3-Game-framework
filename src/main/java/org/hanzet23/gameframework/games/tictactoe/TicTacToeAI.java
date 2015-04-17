package main.java.org.hanzet23.gameframework.games.tictactoe;

/**
 * A minimax AI implementation for TicTacToe
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 */
public class TicTacToeAI {

	public static final char ENEMY = 'O';
	public static final char COMPUTER = 'X';
	public static final char EMPTY = 'E';

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;

	// The game board
	private char[][] board = new char[3][3];
	private char side = COMPUTER;

	// Constructor for TicTacToeAI
	public TicTacToeAI(char[][] board) {
		this.board = board;
	}

	/**
	 * Find the best move for this player
	 * 
	 * @return
	 */
	public int chooseMove() {
		Best best = chooseMove(COMPUTER);
		return best.row * 3 + best.column;
	}

	/**
	 * Find the optimal move given a side
	 * 
	 * @param side
	 * @return
	 */
	private Best chooseMove(char side) {
		// The other side
		char opp = 0;
		// Opponent's best reply
		Best reply;
		int bestRow = 0;
		int bestColumn = 0;
		int value = 0;

		if (positionValue() != UNCLEAR) {
			return new Best(positionValue());
		}

		// Get the opponents info
		if (side == COMPUTER) {
			opp = ENEMY;
			value = HUMAN_WIN;
		} else if (side == ENEMY) {
			opp = COMPUTER;
			value = COMPUTER_WIN;
		}

		// Loop through the game board
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				// Find the empty squares
				if (squareIsEmpty(row, col)) {
					// Simulate a move
					place(row, col, side);
					reply = chooseMove(opp);
					place(row, col, EMPTY);
					// Update the best move if it's better
					if (side == COMPUTER && reply.val > value || side == ENEMY
							&& reply.val < value) {
						value = reply.val;
						bestRow = row;
						bestColumn = col;
					}
				}
			}
		}
		return new Best(value, bestRow, bestColumn);
	}

	/**
	 * Check if the given move is legal
	 * 
	 * @param move
	 * @return
	 */
	public boolean moveOk(int move) {
		return (move >= 0 && move <= 8 && board[move / 3][move % 3] == EMPTY);
	}

	/**
	 * Play the move for this side
	 */
	public void playMove(int move) {
		board[move / 3][move % 3] = this.side;
		if (side == COMPUTER)
			this.side = ENEMY;
		else
			this.side = COMPUTER;
	}

	/**
	 * Check if the game board is full
	 * 
	 * @return
	 */
	private boolean boardIsFull() {
		// Loop through the game board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// Return false if there's an empty spot
				if (board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns whether the given side has won in this position
	 * 
	 * @param side
	 * @return
	 */
	public boolean isAWin(int side) {
		// Check for a win horizontal and vertical
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == side && board[i][1] == side
					&& board[i][2] == side) {
				return true;
			}
			for (int j = 0; j < board[i].length; j++) {
				if (board[0][j] == side && board[1][j] == side
						&& board[2][j] == side) {
					return true;
				}
			}
		}
		// Check for a diagonal win
		if (board[0][0] == side && board[1][1] == side && board[2][2] == side) {
			return true;
		} else if (board[0][2] == side && board[1][1] == side
				&& board[2][0] == side) {
			return true;
		}
		return false;
	}

	/**
	 * Play a move, possibly clearing a square
	 * 
	 * @param row
	 * @param column
	 * @param piece
	 */
	private void place(int row, int column, char piece) {
		board[row][column] = piece;
	}

	/**
	 * Check if a square is empty
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean squareIsEmpty(int row, int column) {
		return board[row][column] == EMPTY;
	}

	/**
	 * Compute static value of current position (win, draw, etc.)
	 * 
	 * @return
	 */
	public int positionValue() {
		if (isAWin(COMPUTER))
			return COMPUTER_WIN;
		else if (isAWin(ENEMY))
			return HUMAN_WIN;
		else if (!isAWin(COMPUTER) && !isAWin(ENEMY) && boardIsFull())
			return DRAW;
		else
			return UNCLEAR;
	}

	/**
	 * Private inner class for a move
	 *
	 */
	private class Best {
		int row;
		int column;
		int val;

		public Best(int v) {
			this(v, 0, 0);
		}

		public Best(int v, int r, int c) {
			val = v;
			row = r;
			column = c;
		}
	}

}
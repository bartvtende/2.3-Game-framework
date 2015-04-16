package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

/**
 * Relatively simple inplementation of the greedy algorithm for Othello
 * 
 * @author Bart
 *
 */
public class OthelloGreedyAI extends OthelloAI {

	// Matrix which determines how favourable a square is
	private int[][] randomBoard = { { 30, -25, 10, 5, 5, 10, -25, 30, },
			{ -25, -25, 1, 1, 1, 1, -25, -25, }, { 10, 1, 5, 2, 2, 5, 1, 10, },
			{ 5, 1, 2, 1, 1, 2, 1, 5, }, { 5, 1, 2, 1, 1, 2, 1, 5, },
			{ 10, 1, 5, 2, 2, 5, 1, 10, }, { -25, -25, 1, 1, 1, 1, -25, -25, },
			{ 30, -25, 10, 5, 5, 10, -25, 30, } };

	public OthelloMove getBestMove(char player, char[][] board, int turnCounter) {
		// Get all of the valid moves for this
		ArrayList<OthelloMove> moves = getValidMoves(player, board);
		
		// If size is 0, place a random move
		if (moves.size() == 0) {
			moveRandom(player, board);
		}
		
		// Print the size of the list
		System.out.println(moves.size() + " moves have been found");

		// Initialize the best value integer with -30
		int bestValue = -30;

		OthelloMove bestMove = null;
		// Loop through all the available valid moves
		for (int i = 0; i < moves.size(); i++) {
			// Copy the board
			char[][] tempBoard = new char[board.length][];
			for (int j = 0; j < board.length; j++) {
				tempBoard[j] = board[j].clone();
			}

			// Get the move
			OthelloMove move = moves.get(i);

			// Check the amount of stones the move gives
			int amountOfStones = getAmountOfStones(player, move, tempBoard);

			// If there aren't more then .. squares on the board, try not to get
			// the most amount of stones
			if (turnCounter < 16) {
				if ((randomBoard[move.x][move.y] + amountOfStones) > bestValue) {
					bestValue = randomBoard[move.x][move.y];
					bestMove = move;
				}
			} else { // Pick the square with the most probability and most
						// stones
				if ((randomBoard[move.x][move.y] - amountOfStones) > bestValue) {
					bestValue = randomBoard[move.x][move.y];
					bestMove = move;
				}
			}
		}

		return bestMove;
	}

}

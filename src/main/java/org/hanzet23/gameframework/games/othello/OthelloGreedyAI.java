package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

/**
 * Relatively simple implementation of the greedy algorithm for Othello
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class OthelloGreedyAI extends OthelloAI {

	/**
	 * Calculates the best move for the given player using the greedy algorithm
	 * 
	 * @param player
	 * @param board
	 * @param turnCounter
	 * @return
	 */
	public OthelloMove getBestMove(char player, char[][] board, int turnCounter) {
		// Get all of the valid moves for this
		ArrayList<OthelloMove> moves = getValidMoves(player, board);

		// Print the size of the list
		System.out.println(moves.size() + " moves have been found");

		// Initialize the best value integer with -50 (failsafe)
		int bestValue = -50;

		OthelloMove bestMove = null;
		// Loop through all the available valid moves
		for (int i = 0; i < moves.size(); i++) {
			// Copy the board
			char[][] tempBoard = cloneBoard(board);

			// Get the move
			OthelloMove move = moves.get(i);

			// Check the amount of stones the move gives
			int amountOfStones = getAmountOfStones(player, move, tempBoard);

			// If there aren't more then 36 (16*2 + 4) squares on the board, try not to get
			// the most amount of stones
			if (turnCounter < 16) {
				if ((randomBoard[move.x][move.y] - amountOfStones) > bestValue) {
					bestValue = randomBoard[move.x][move.y];
					bestMove = move;
				}
			} else { // Pick the square with the most probability and most
						// stones
				if ((randomBoard[move.x][move.y] + amountOfStones) > bestValue) {
					bestValue = randomBoard[move.x][move.y];
					bestMove = move;
				}
			}
		}

		// Returns the calculated best move
		return bestMove;
	}

}

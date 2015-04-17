package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a random move from the available valid moves
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class OthelloRandomAI extends OthelloAI {

	/**
	 * Calculates a "best move" from the random valid moves
	 * 
	 * @param player
	 * @param board
	 * @param turnCounter
	 * @return
	 */
	public OthelloMove getBestMove(char player, char[][] board, int turnCounter) {
		// Get all the valid moves for the player and board
		ArrayList<OthelloMove> moves = getValidMoves(player, board);

		// Get the size of the list
		int size = moves.size();

		// Increment the size if there's only one move left, to avoid a pointer
		// exception
		if (size == 1) {
			size = 2;
		}

		// Get a random index from the size of the list
		Random random = new Random();
		int i = random.nextInt(size - 1);

		// Return the random OthelloMove object
		return moves.get(i);
	}

}

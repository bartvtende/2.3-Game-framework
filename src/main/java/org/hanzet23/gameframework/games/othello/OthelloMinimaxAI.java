package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

/**
 * Implementation of a minimax and alpha-beta pruning for Othello
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class OthelloMinimaxAI extends OthelloAI {

	// The depth this algorithm searches
	private final static int DEPTH = 6;

	private final static int MAX_RANK = Integer.MAX_VALUE - 64;

	public OthelloMove getBestMove(char player, char[][] board, int turnCounter) {
		// Initialize the alpha-beta cutoff values.
		int alpha = MAX_RANK + 64;
		int beta = -alpha;

		// Kick off the look ahead.
		return this.getBestMove(board, player, 1, alpha, beta, turnCounter);
	}

	/**
	 * Calculates the best move for the given player using the minimax algorithm
	 * and alpha-beta pruning
	 * 
	 * @param board
	 * @param player
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @param turnCounter
	 * @return
	 */
	public OthelloMove getBestMove(char[][] board, char player, int depth,
			int alpha, int beta, int turnCounter) {
		// Get the tiles of the user and opponent
		char tile = NetworkModel.board.player.tile;
		char tileOpp = NetworkModel.board.player.tileOpp;

		char opponent = tileOpp;

		// Initialize the best move.
		OthelloMove bestMove = new OthelloMove(-1, -1);
		bestMove.setValue(-1 * MAX_RANK);

		// Get all of the valid moves for this player
		ArrayList<OthelloMove> moves = getValidMoves(player, board);

		for (OthelloMove move : moves) {
			// Copy the board
			char[][] tempBoard = cloneBoard(board);

			// Simulate a move
			place(player, move, tempBoard);

			// Check the score
			int score = countTiles(board, tileOpp) - countTiles(board, tile);

			// Check the board
			int forfeit = 0;
			boolean isEndGame = false;
			ArrayList<OthelloMove> opponentValidMoves = getValidMoves(opponent,
					tempBoard);

			if (opponentValidMoves.size() == 0) {
				// The opponent cannot move, count the forfeit.
				forfeit = 1;

				// If the player cannot make a move, game over
				if (getValidMoves(player, tempBoard).size() == 0) {
					isEndGame = true;
				}
			}

			// Base case: we've reached the end of the board or the depth
			if (isEndGame || depth == DEPTH) {
				// If the game is over, max the score
				if (isEndGame) {
					// Negative for black win.
					if (score < 0) {
						move.setValue(-MAX_RANK + score);
					}
					// Positive for white win.
					else if (score > 0) {
						move.setValue(MAX_RANK + score);
					}
					// Zero for a draw.
					else {
						move.setValue(0);
					}
				} else {
					// Get the amount of tiles this move gets
					int stones = countTiles(tempBoard, player)
							- countTiles(board, player);
					// Get the probability of this move
					int probability = randomBoard[move.getX()][move.getY()];

					// If less then 36 tiles has been placed, put the amount of
					// stones to negative
					if (turnCounter < 16) {
						stones = -stones;
					}

					// Count the two factors together
					int rank = stones + probability;

					// Set the rank as the current value
					move.setValue(rank);
				}
			}

			// Search for the best move
			else {
				// Get the best move for the next
				OthelloMove nextMove = getBestMove(tempBoard, tileOpp,
						depth + 1, alpha, beta, turnCounter++);

				// Set the value of the move to the best move's value
				move.setValue(nextMove.getValue());

				// If the move did not result in an end game, add the forfeit
				// value to the rank.
				if (forfeit != 0 && Math.abs(move.getValue()) < MAX_RANK) {
					int rank = move.getValue() + (6 * forfeit);
					move.setValue(rank);
				}

				// Adjust the alpha and beta values
				if (player == tileOpp && move.getValue() > beta)
					beta = move.getValue();
				if (player == tile && move.getValue() < alpha)
					alpha = move.getValue();
			}

			// Adjust the value to alpha if necessary
			if (player == tileOpp && move.getValue() > alpha) {
				move.setValue(alpha);
				return move;
			}
			
			// Adjust the value to beta if necessary
			if (player == tile && move.getValue() < beta) {
				move.setValue(beta);
				return move;
			}

			// Set to best move if it's the first move
			if (bestMove.getX() < 0) {
				bestMove = move;
			}
			
			// Set the best move to the current if it's better
			else if (player * move.getValue() > player * bestMove.getValue()) {
				bestMove = move;
			}
		}

		// Return the best move found.
		return bestMove;
	}

}

package main.java.org.hanzet23.gameframework.games.othello;


import game.othello.OthelloBoard;

import java.util.ArrayList;


/**
 * AI for Othello
 */
public class OthelloAI implements Runnable {
	
	
	private final int MAX_DEPTH;				// Value representing the depth of recursion
	private  OthelloModel board;					// Speelveld
	private int runForPlayer;
	

	
	public OthelloAI(OthelloModel board) {
		
		MAX_DEPTH = 3; //difficulty later implementeren?
		
	}
	
	
	/**
	 * Bereken de best beschikbare zet voor een speler.
	 * @param player
	 * @return best move (voorbeeld: "1,1")
	 */
	public void getBestMove(int player) {
		runForPlayer = player;
	}
	
	@Override
	public void run() {
			OthelloMove bestMove = bestMove(board.clone(), runForPlayer, MAX_DEPTH, null, null);
		//	notifyListeners(bestMove.toString());
		}
	
	
	/**
	 * Method to, recursively and with use of pruning, compute the best move for a player
	 * @param board
	 * @param player
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @return OthelloMove
	 */
	private OthelloMove bestMove(OthelloModel board, int player, int depth, OthelloMove alpha, OthelloMove beta) {
		
		if(depth == 0) {
			// This is the final depth, check if we can determine an end-state. If not, estimate it
			OthelloMove move = new OthelloMove(-1, -1);
			double moveValue = board.evaluateGameResult(false);
			if(moveValue == OthelloModel.STATE_UNKNOWN) {
				moveValue = board.estimateGameResult();
			}
			move.setValue(moveValue);
			return move;
		}
		
		int opponent = OthelloModel.getOtherPlayer(player);
		ArrayList<OthelloMove> moves = board.getValidMoves(player);
		if(moves.size() == 0) {
			if(board.canPlayerMove(opponent)) {
				// If the player can't move, he doesn't and it's his opponent's turn
				return bestMove(board, opponent, depth - 1, alpha, beta);
			}
			else {
				// Neither player can move. Game over. Player with most tiles has won
				OthelloMove move = new OthelloMove(-1, -1);
				move.setValue(board.evaluateGameResult(true));
				return move;
			}
		}
		
		// The player can move, iterate over all moves and determine which is best (Speed up the proces using alpha beta pruning)
		
		OthelloModel backup = board.clone(); // Make a backup of the current board so we can restore it after we test a move
		for(OthelloMove move : moves) {
			// Recursively find out the value of this move
			board.place(player, move);
			move.value = bestMove(board, opponent, depth - 1, alpha, beta).value;
			
			if(player == OthelloModel.PLAYER_ONE) {
				// If this move is better or equal for player alpha, alpha best = move
				if(alpha == null || move.isBetterThan(alpha, OthelloModel.PLAYER_ONE)){
					alpha = move;
				}
			}
			else {
				// If this move is better or equal for player beta, beta best = move
				if(beta == null || move.isBetterThan(beta, OthelloModel.PLAYER_TWO)){
					beta = move;
				}
			}
			
			// Restore the board, even if this recursion step no longer needs it- parent recursions might still need it
			board = backup.clone();
			
			// If the opponent has options with an outcome better than an outcome you have available here- he won't let it get to this
			// If beta is better for player two, then alpha is better for player one
			if(alpha != null && beta != null && beta.isBetterThan(alpha, OthelloModel.PLAYER_TWO)) break;
		}
		
		if(player == OthelloModel.PLAYER_ONE){
			return alpha;
		}
		return beta;
	}
}

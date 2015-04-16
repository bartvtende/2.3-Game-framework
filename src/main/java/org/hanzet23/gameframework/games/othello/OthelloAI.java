package extended.gameModules.othello;

import extended.gameInterface.AbstractGameAI;
import game.othello.OthelloBoard;
import game.othello.OthelloMove;
import game.othello.OthelloGame;

import java.util.ArrayList;

import model.Settings;

/**
 * AI for Othello
 */
public class OthelloAI extends AbstractGameAI implements Runnable {
	
	// Turns out, threading doesn't make things fast enough that we could increase the max depth
	// (Likely because it removes alpha-beta pruning benefit from the top recursion level)
	// Its still faster than the regular approach though
	public static final boolean USE_THREADING = true;
	
	
	private final int MAX_DEPTH;				// Value representing the depth of recursion
	private OthelloBoard board;					// Playing field
	private int runForPlayer;
	private Thread thread;
	
	// Used for multi-threading
	private OthelloAIThreaded rootAIInstance;	// Stores the AI instance to which to report the result
	private OthelloMove originMove;				// Stores the move that lead to this starting situation
	
	public OthelloAI(OthelloBoard board) {
		if(USE_THREADING) MAX_DEPTH = 1 + (int) (2.0 * Settings.DIFFICULTY); // The root thread already effectively takes care of 1 level of recursion
		else MAX_DEPTH = 1 + (int) (2.5 * Settings.DIFFICULTY);
		
		this.board = board;
	}
	
	/**
	 * Used when using the threading class, if it is used, answers are sent there
	 * @param ai
	 */
	protected void setRootAIInstance(OthelloAIThreaded ai) {
		rootAIInstance = ai;
	}
	
	/**
	 * Wait for this thread to complete
	 */
	public void waitForCompletion() {
		try { thread.join(); } catch (InterruptedException e) {}
	}
	
	/**
	 * Used when using threading, the origin move is the move object that was played for this AI thread
	 * @param player
	 * @param originMove
	 */
	public void getBestMove(int player, OthelloMove originMove) {
		this.originMove = originMove;
		getBestMove(player);
	}
	
	/**
	 * Compute the best move available for player
	 * @param player
	 * @return best move (ie: "1,1")
	 */
	public void getBestMove(int player) {
		runForPlayer = player;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		// Used for threaded solving of calculating the best move
		if(USE_THREADING) { // Sub AI
			OthelloMove bestMove = bestMove(board.clone(), runForPlayer, MAX_DEPTH, null, null);
			originMove.value = bestMove.value;
			rootAIInstance.addResult(originMove);
			return;
		}
		
		// Used for non-threaded solving of calculating the best move
		else {
			OthelloMove bestMove = bestMove(board.clone(), runForPlayer, MAX_DEPTH, null, null);
			notifyListeners(bestMove.toString());
		}
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
	private OthelloMove bestMove(OthelloBoard board, int player, int depth, OthelloMove alpha, OthelloMove beta) {
		
		if(depth == 0) {
			// This is the final depth, check if we can determine an end-state. If not, estimate it
			OthelloMove move = new OthelloMove(-1, -1);
			double moveValue = board.evaluateGameResult(false);
			if(moveValue == OthelloBoard.STATE_UNKNOWN) {
				moveValue = board.estimateGameResult();
			}
			move.setValue(moveValue);
			return move;
		}
		
		int opponent = OthelloGame.getOtherPlayer(player);
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
		
		OthelloBoard backup = board.clone(); // Make a backup of the current board so we can restore it after we test a move
		for(OthelloMove move : moves) {
			// Recursively find out the value of this move
			board.place(player, move);
			move.value = bestMove(board, opponent, depth - 1, alpha, beta).value;
			
			if(player == OthelloGame.PLAYER_ONE) {
				// If this move is better or equal for player alpha, alpha best = move
				if(alpha == null || move.isBetterThan(alpha, OthelloGame.PLAYER_ONE)){
					alpha = move;
				}
			}
			else {
				// If this move is better or equal for player beta, beta best = move
				if(beta == null || move.isBetterThan(beta, OthelloGame.PLAYER_TWO)){
					beta = move;
				}
			}
			
			// Restore the board, even if this recursion step no longer needs it- parent recursions might still need it
			board = backup.clone();
			
			// If the opponent has options with an outcome better than an outcome you have available here- he won't let it get to this
			// If beta is better for player two, then alpha is better for player one
			if(alpha != null && beta != null && beta.isBetterThan(alpha, OthelloGame.PLAYER_TWO)) break;
		}
		
		if(player == OthelloGame.PLAYER_ONE){
			return alpha;
		}
		return beta;
	}
}

package main.java.org.hanzet23.gameframework.games.tictactoe;

import extended.gameInterface.AbstractGameAI;
import game.tictactoe.TicTacToeGame;

import java.util.ArrayList;

import model.Settings;

/**
 * AI for TicTacToe
 */
public class TicTacToeAI extends AbstractGameAI {
	
	private final double difficulty;
	private final int DRAW = 2;				// Value representing a draw
	private final int STATE_UNKNOWN = 3;	// Value representing that the game is yet undecided
	
	private int[][] board;					// Playing field
	
	public TicTacToeAI(int[][] board) {
		this.board = board;
		
		difficulty = 1 - (Math.pow(Settings.DIFFICULTY, 0.4) / Math.pow(Settings.HARD, 0.4));
	}
	
	/**
	 * Compute the best move available for player
	 * @param player
	 * @return best move (ie: "1,1")
	 */
	public void getBestMove(int player) {
		boolean getRandom;
		double random = Math.random();
		getRandom = (random < difficulty);
		notifyListeners(bestMove(player, getRandom).toString());
	}
	
	/**
	 * Method to recursively compute the best move for a player
	 * @param player
	 * @return Best Move
	 */
	private Move bestMove(int player, boolean getRandom) {
		
		int opp = TicTacToeGame.getOtherPlayerInt(player);
		
		Move bestMove = null;
		
		ArrayList<Move> moves = getLegalMoves();
		if(getRandom) {
			return moves.get((int) (Math.random() * moves.size()));
		}
		
		for(Move move : moves) {
			board[move.x][move.y] = player;
			move.setValue(evaluateGame());
			
			// Recursive
			if(move.value == STATE_UNKNOWN) {
				// If the value of the current move is unknown, the value of the move
				// can be evaluated by evaluating the opponent's best follow up move
				move.value = bestMove(opp, false).value;
			}
			
			if(bestMove == null) {
				bestMove = move;
			}
			else if(move.value != opp) {
				// The move is the new best move if either the previous best was a loss, or the current move is a win
				if(bestMove.value == opp || move.value == player) {
					bestMove = move;
				}
			}
			// Remove our move from the board
			board[move.x][move.y] = TicTacToeGame.EMPTY;
			// No need to keep looking if we've found a winning move
			if(bestMove.value == player) break;
		}
		return bestMove;
	}
	
	/**
	 * Gets an iterator over all legal moves
	 * @return legal moves
	 */
	private ArrayList<Move> getLegalMoves() {
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				if(board[x][y] == TicTacToeGame.EMPTY) legalMoves.add(new Move(x, y));
			}
		}
		return legalMoves;
	}
	
	/**
	 * Determine if someone has won, lost, it's a draw or the game isnt over
	 * @return true/false
	 */
	private int evaluateGame() {
		if(TicTacToeGame.isAWin(board, TicTacToeGame.PLAYER_ONE)) return TicTacToeGame.PLAYER_ONE;
		if(TicTacToeGame.isAWin(board, TicTacToeGame.PLAYER_TWO)) return TicTacToeGame.PLAYER_TWO;
		if(TicTacToeGame.boardIsFull(board)) return DRAW;
		return STATE_UNKNOWN;
	}
	
	/**
	 * Class to describe a move and it's value
	 * @author Wim, Mart, Bas, Jurrian
	 *
	 */
	private class Move {
		public int x;
		public int y;
		public int value = STATE_UNKNOWN;
		
		public Move(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void setValue(int value){
			this.value = value;
		}
		
		public String toString() {
			return x + "," + y;
		}
	}
}

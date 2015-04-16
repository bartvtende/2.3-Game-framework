package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class holds the board of OthelloGame.
 *
 */
public class OthelloBoard {
	
	public static final boolean TIME_TILE_VALUE_NORMALIZATION = true;
	private static final double INITIAL_MODIFIER = 6.0;		// How large is the value modifier initially
	private static final double END_GAME_MODIFIER = 3.7;	// How much value will it have lost at the end of the game

	
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;
	
	public static final int STATE_DRAW = 2;
	public static final int STATE_UNKNOWN = 3;
	public static final int EMPTY = 2;
	
	public final Color playerOneColor = Color.white;
	public final Color playerTwoColor = Color.black;
	
	protected int[][] board = new int[WIDTH][HEIGHT];
	
	public OthelloBoard() {
		empty();
	}
	
	/**
	 * Clear the board.
	 */
	public void empty() {
		for(int[] a : board) {
			Arrays.fill(a, EMPTY);
		}
	}
	
	/**
	 * Getter for the board array
	 * @return Array
	 */
	public int[][] getArray() {
		return board;
	}
	
	/**
	 * Method to clone the board.
	 * @return OthelloBoard
	 */
	public OthelloBoard clone() {
		OthelloBoard clone = new OthelloBoard();
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				clone.board[x][y] = board[x][y];
			}
		}
		return clone;
	}
	
	/**
	 * Checks if board is full return true
	 */
	public boolean isFull()
	{
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				if(board[i][j] == EMPTY){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isEmpty(int x, int y) {
		return (board[x][y] == EMPTY);
	}
	
	/**
	 * Function to check if there is any more left
	 * @return True if can still move
	 */
	public boolean canPlayerMove(int player){
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board[x].length; y++){
				if(board[x][y] == EMPTY && isValidMove(player, x, y)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Function to check if there is any more left
	 * @return True if can still move
	 */
	public boolean canPlayerMove(){
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board[x].length; y++){
				if(board[x][y] == EMPTY && (isValidMove(OthelloGame.PLAYER_ONE, x, y) || isValidMove(OthelloGame.PLAYER_TWO, x, y))){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a move is valid.
	 * @param player
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isValidMove(int player, int x, int y) {
		return isValidMove(player, new OthelloMove(x, y));
	}
	
	/**
	 * Checks if a OthelloMove is valid.
	 * @param player
	 * @param move
	 * @return boolean
	 */
	private boolean isValidMove(int player, OthelloMove move) {
		if(board[move.x][move.y] != EMPTY){
			return false;
		}
		int opp = OthelloGame.getOtherPlayer(player); 
		
		//8 directions 2 coordinates each direction
		int[][] directions = {
				{ -1, -1 }, { 0, -1 }, { 1, -1 },  	//Top directions
				{ -1, 0 },  { 1, 0 },				//left and right
				{ -1, 1 }, { 0, 1 }, { 1, 1 }		//bottom directions
			};
		
		for(int dir = 0; dir < directions.length; dir++){
			//each direction check the pieces
			for(int dist = 1; dist < WIDTH; dist++){
				int x = move.x + (dist * directions[dir][0]);
				int y = move.y + (dist * directions[dir][1]);
				//check for array out of bound
				if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT){
					break;
				}
				
				int tile = board[x][y];
				
				if(dist == 1) {
					if(tile != opp)	break;
				}
				else {
					if(tile == EMPTY) break;
					if(tile == player) return true;
				}
			}
		}
		// no direction includes a valid move for this position
		return false;
	}
	
	/**
	 * Function to get a list of valid moves
	 * @return ArrayList OthelloMove objects
	 */
	public ArrayList<OthelloMove> getValidMoves(int player){
		ArrayList<OthelloMove> validmoves = new ArrayList<OthelloMove>();
		for(int x = 0; x < WIDTH; x++){
			for(int y = 0; y < HEIGHT; y++){
				OthelloMove move = new OthelloMove(x, y);
				if(isValidMove(player, move)){
					validmoves.add(move);
				}
			}
		}
		return validmoves;
	}
	
	/**
	 * Place a move on the board.
	 * @param player
	 * @param x
	 * @param y
	 * @return
	 */
	public int place(int player, int x, int y) {
		return place(player, new OthelloMove(x, y));
	}
	
	/**
	 * Place an OthelloMove on the board.
	 * @param player
	 * @param move
	 * @return
	 */
	public int place(int player, OthelloMove move) {
		int opp = OthelloGame.getOtherPlayer(player);
		int flipped = 0;
		
		int[][] directions = {
				{ -1, -1 }, { 0, -1 }, { 1, -1 },  		//Top directions
				{ -1,  0 }, { 1,  0 },					//left and right
				{ -1,  1 }, { 0,  1 }, { 1,  1 }		//bottom directions
			};
		
		outerLoop:
		for(int dir = 0; dir < directions.length; dir++){		// Check all directions
			
			// first check if we can go this way, skip if not
			for(int dist = 1; true; dist++){
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);
				
				if(checkX < 0 || checkY < 0 || checkX >= WIDTH || checkY >= HEIGHT){
					continue outerLoop;
				}
				else if(dist == 1) {
					if(board[checkX][checkY] != opp) continue outerLoop;
				}
				else {
					if(board[checkX][checkY] == EMPTY) continue outerLoop;
					else if(board[checkX][checkY] == player) break;
				}
			}
			
			// then flip the tiles
			for(int dist = 1; dist < WIDTH; dist++){
				int checkX = move.x + (dist * directions[dir][0]);
				int checkY = move.y + (dist * directions[dir][1]);
				
				if(board[checkX][checkY] == opp) {
					board[checkX][checkY] = player;
					flipped++;
				}
				else {
					break;
				}
			}
		}
		
		board[move.x][move.y] = player;
		return flipped;
	}
	
	/**
	 * Method to count the total amount of stones on the board.
	 * @return int count
	 */
	public int countStones(){
		int count = 0;
		for(int x = 0; x < WIDTH; x++){
			for(int y = 0; y < HEIGHT; y++){
				if(board[x][y] != EMPTY){
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * Method to count the amount of stones of a specific player.
	 * @param player
	 * @return int count
	 */
	public int countPlayerStones(int player){
		int count = 0;
		for(int x = 0; x < WIDTH; x++){
			for(int y = 0; y < HEIGHT; y++){
				if(board[x][y] == player){
					count++;
				}
			}
		}
		return count;
	}
	
	@Override
	public String toString() {
	    String print = "";
	    print += " |01234567\n";
	    print += "-+--------\n";
	    
		for(int y = 0; y < HEIGHT; y++){
			print += y + "|";
	    	for(int x = 0; x < WIDTH; x++){
	    		if(board[x][y] == OthelloGame.PLAYER_ONE){
	    			print += '1';
	    		}else if(board[x][y] == OthelloGame.PLAYER_TWO){
	    			print += '2';
	    		}else{
	    			print += ".";
	    		}
	    	}
	    	print += "\n";
	    }
		return print;
	}
	
	/**
	 * Determine if someone has won, lost, it's a draw or the game isnt over
	 * @param if true: Avoid state_unknown, just count the stones and determine a winner
	 * @return true/false
	 */
	public double evaluateGameResult(boolean forceOutcome) {
		if(forceOutcome || isFull() || !canPlayerMove()) {
			int one = countPlayerStones(OthelloGame.PLAYER_ONE);
			int two = countPlayerStones(OthelloGame.PLAYER_TWO);
			if(one > two) return OthelloGame.PLAYER_ONE;
			if(two > one) return OthelloGame.PLAYER_TWO;
			return OthelloBoard.STATE_DRAW;
		}
		return OthelloBoard.STATE_UNKNOWN;
	}

	/**
	 * Determine who is likely winning
	 * @return 
	 */
	public double estimateGameResult() {
		
		double playerOneScore = 0;
		double playerTwoScore = 0;
		
		int stones = countStones() - 4;
		if(stones < 1) stones = 1;
		
		double modifierRegression = INITIAL_MODIFIER - END_GAME_MODIFIER;	// How much value will it have lost at the end of the game
		double modifier = INITIAL_MODIFIER - modifierRegression * (stones / 60.0); 
		
		for(int x = 0; x < WIDTH; x++) {
			
			for(int y = 0; y < HEIGHT; y++) {
				int tile = board[x][y];
				if(tile == EMPTY) continue;
				
				double value = 1;
				
				if(x == 0 || x == WIDTH - 1) value *= modifier;
				else if(x == 1 || x == WIDTH - 2) value /= modifier;
				
				if(y == 0 || y == HEIGHT - 1) value *= modifier;
				else if(y == 1 || y == HEIGHT - 2) value /= modifier;
				
				if(tile == OthelloGame.PLAYER_ONE) {
					playerOneScore += value;
				}
				else if(tile == OthelloGame.PLAYER_TWO) {
					playerTwoScore += value;
				}
			}
		}
		
		double sum = playerOneScore + playerTwoScore;
		double result = OthelloGame.PLAYER_ONE + (playerTwoScore / sum);
		return result;
		
		/*
		int one = countPlayerStones(OthelloGame.PLAYER_ONE);
		int two = countPlayerStones(OthelloGame.PLAYER_TWO);
		double sum = one + two;
		return OthelloGame.PLAYER_ONE + (one / sum);
		*/
	}
}

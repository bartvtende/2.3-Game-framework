package main.java.org.hanzet23.gameframework.games.othello;

/**
 * This class is a Othello move.
 * It holds the coordinates of the move.
 * Is also holds the value of the move.
 *
 */
public class OthelloMove {
	public int x;
	public int y;
	public double value = OthelloModel.STATE_UNKNOWN;
	
	public OthelloMove(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Setter for value.
	 * @param value
	 */
	public void setValue(double value){
		this.value = value;
	}
	
	/**
	 * Setter for the value.
	 * @param value
	 */
	public void setValue(int value){
		this.value = (double) value;
	}
	
	/**
	 * Method to return the move in a String.
	 */
	public String toString() {
		return x + "," + y;
	}
	
	/**
	 * Method to return the move + value in a String
	 * @return
	 */
	public String toStringExt() {
		return toString() + ": " + value;
	}
	
	public int getValue() {
		return x*8 + y;
	}
	
	/**
	 * Method to check if another move is better than (or equal to) this one.
	 * @param move
	 * @param player
	 * @return
	 */
	public boolean isBetterThan(OthelloMove move, char player) {
		double valueA = (value <= OthelloModel.PLAYER_TWO) ? value : ((0.0 + OthelloModel.PLAYER_ONE + OthelloModel.PLAYER_TWO) / 2.0);
		double valueB = (move.value <= OthelloModel.PLAYER_TWO) ? move.value : ((0.0 + OthelloModel.PLAYER_ONE + OthelloModel.PLAYER_TWO) / 2.0);
		
		return (Math.abs(player - valueA) < Math.abs(player - valueB));
	}
}
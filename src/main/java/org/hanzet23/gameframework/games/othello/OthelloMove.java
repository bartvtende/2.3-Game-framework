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
	
	public int getValue() {
		return x*8 + y;
	}
}
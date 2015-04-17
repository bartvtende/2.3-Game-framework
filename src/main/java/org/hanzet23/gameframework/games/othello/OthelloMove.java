package main.java.org.hanzet23.gameframework.games.othello;

/**
 * Class that represents a move for Othello, including the coordinates and value
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class OthelloMove {
	
	// The x point of this move
	public int x;
	
	// The y point of this move
	public int y;
	
	// The value of this move
	public int value;

	/**
	 * Constructor of OthelloMove that takes the x and y coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public OthelloMove(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor of OthelloMove that takes a position
	 * 
	 * @param move
	 */
	public OthelloMove(int move) {
		this.x = move / 8;
		this.y = move % 8;
	}

	/**
	 * Setter of the value variable
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Getter of the value variable
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Getter of the X axis
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter of the Y axis
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the position calculated from the x and y points of this move
	 * 
	 * @return
	 */
	public int getPosition() {
		return ((x * 8) + y);
	}
}
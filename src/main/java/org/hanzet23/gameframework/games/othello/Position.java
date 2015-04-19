package main.java.org.hanzet23.gameframework.games.othello;

/**
 * Contain an X and Y position.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class Position {

	private int XPosition;
	private int YPosition;

	/**
	 * Constructor for Position.
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		setXPosition(x);
		setYPosition(y);
	}

	/**
	 * Returns the X value of the position.
	 * @return
	 */
	public int getXPosition() {
		return XPosition;
	}

	/**
	 * Sets the X value of the position.
	 * @param xPosition
	 */
	public void setXPosition(int xPosition) {
		XPosition = xPosition;
	}

	/**
	 * Returns the Y value of the position.
	 * @return
	 */
	public int getYPosition() {
		return YPosition;
	}

	/**
	 * Sets the Y value of the position.
	 * @param yPosition
	 */
	public void setYPosition(int yPosition) {
		YPosition = yPosition;
	}
}

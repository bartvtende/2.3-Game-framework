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
	public int value;
	
	public OthelloMove(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public OthelloMove(int move) {
		this.x = move / 8;
		this.y = move % 8;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getPosition() {
		return ((x * 8) + y);
	}
}
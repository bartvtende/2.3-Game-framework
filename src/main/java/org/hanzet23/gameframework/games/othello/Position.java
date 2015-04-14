package main.java.org.hanzet23.gameframework.games.othello;

public class Position {

	private int XPosition;
	private int YPosition;

	public Position(int x, int y) {
		setXPosition(x);
		setYPosition(y);
	}

	public int getXPosition() {
		return XPosition;
	}

	public void setXPosition(int xPosition) {
		XPosition = xPosition;
	}

	public int getYPosition() {
		return YPosition;
	}

	public void setYPosition(int yPosition) {
		YPosition = yPosition;
	}
}

package main.java.org.hanzet23.gameframework.games.othello;

public interface OthelloAIInterface {

	public void setBoard(char[][] board);
	
	public Position[] getAvailableSpaces();
	
	public Position getRandom(Position[] positions);
	
	public int countGain(Position position);
	
	public Position bestMove();
}

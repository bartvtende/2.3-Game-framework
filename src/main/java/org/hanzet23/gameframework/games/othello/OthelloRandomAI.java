package main.java.org.hanzet23.gameframework.games.othello;

import java.util.Random;

public class OthelloRandomAI implements OthelloAIInterface {

	private char[][] board = null;
	private Random random = new Random();
	
	public OthelloRandomAI(){
		//Val in een kuil jongen!
	}
	
	
	@Override
	public Position[] getAvailableSpaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getRandom(Position[] positions) {
		// TODO Auto-generated method stub

		int index = new Double(Math.floor(random.nextDouble() * positions.length)).intValue();
		
		return positions[index];
	}

	@Override
	public int countGain(Position position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position bestMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBoard(char[][] board) {
		// TODO Auto-generated method stub
		this.board = board;
	}

}

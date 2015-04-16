package main.java.org.hanzet23.gameframework.games.othello;

import java.util.ArrayList;
import java.util.Random;

public class OthelloRandomAI extends OthelloAI {

	public OthelloMove getBestMove(char player, char[][] board, int turnCounter) {
		ArrayList<OthelloMove> moves = getValidMoves(player, board);
		
		int size = moves.size();
		
		if (size == 1) {
			size = 2;
		}
		
		Random random = new Random();
		int i = random.nextInt(size - 1);
		
		return moves.get(i);
	}
	
}

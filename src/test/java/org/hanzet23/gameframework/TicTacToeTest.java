package test.java.org.hanzet23.gameframework;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.org.hanzet23.gameframework.games.tictactoe.TicTacToeAI;
import main.java.org.hanzet23.gameframework.games.tictactoe.TicTacToeModel;

public class TicTacToeTest extends TestCase {

	public TicTacToeTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TicTacToeTest.class);
	}
	
	public void testTicTacToeGame1() {
		TicTacToeModel t = new TicTacToeModel("Test game 1");
		
		/*
		 * Testing for the following case:
		 * 
		 * X.X
		 * OO.
		 * ...
		 */
		
		t.addItemToBoard("0", 'X');
		t.addItemToBoard("3", 'O');
		t.addItemToBoard("2", 'X');
		t.addItemToBoard("4", 'O');
		
		int bestMove = new TicTacToeAI(t.getBoard()).chooseMove();
		
		// Should equal 1 (best move)
		assertEquals(1, bestMove);
	}
	
	public void testTicTacToeGame2() {
		TicTacToeModel t = new TicTacToeModel("Test game 2");
		
		/*
		 * Testing for the following case:
		 * 
		 * O.O
		 * .X.
		 * X-X
		 */

		t.addItemToBoard("0", 'X');
		t.addItemToBoard("2", 'X');
		t.addItemToBoard("4", 'O');
		t.addItemToBoard("6", 'O');
		t.addItemToBoard("8", 'O');
		
		int bestMove = new TicTacToeAI(t.getBoard()).chooseMove();
		
		// Should equal 1 (best move)
		assertEquals(1, bestMove);
	}

	public void testTicTacToeGame3() {
		TicTacToeModel t = new TicTacToeModel("Test game 3");
		
		/*
		 * Testing for the following case:
		 * 
		 * XOX
		 * X.O
		 * 00X
		 */

		t.addItemToBoard("0", 'X');
		t.addItemToBoard("1", 'O');
		t.addItemToBoard("2", 'X');
		t.addItemToBoard("3", 'X');
		t.addItemToBoard("5", 'O');
		t.addItemToBoard("6", 'O');
		t.addItemToBoard("7", 'O');
		t.addItemToBoard("8", 'X');
		
		int bestMove = new TicTacToeAI(t.getBoard()).chooseMove();
		
		// Should equal 4 (best move)
		assertEquals(4, bestMove);
	}
}

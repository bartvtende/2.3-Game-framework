package test.java.org.hanzet23.gameframework;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.org.hanzet23.gameframework.games.othello.OthelloModel;
import main.java.org.hanzet23.gameframework.games.othello.OthelloMove;

public class OthelloTest extends TestCase {

	public OthelloTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(OthelloTest.class);
	}

	/**
	 * Test if there are valid moves
	 */
	public void testValidMoves() {
		OthelloModel o = new OthelloModel("Test valid moves");
		
		o.initializeBoard();

		ArrayList<OthelloMove> moves = o.ai.getValidMoves('X', o.getBoard());
		
		assertNotNull(moves);

		OthelloMove move1 = new OthelloMove(3, 2);
		OthelloMove move2 = new OthelloMove(2, 3);
		OthelloMove move3 = new OthelloMove(5, 4);
		OthelloMove move4 = new OthelloMove(4, 5);

		int counter = 0;

		for (OthelloMove move : moves) {
			if (move.getPosition() == move1.getPosition()
					|| move.getPosition() == move2.getPosition()
					|| move.getPosition() == move3.getPosition()
					|| move.getPosition() == move4.getPosition()) {
				counter++;
			}
		}

		// Should count to 4
		assertEquals(4, counter);
	}
}

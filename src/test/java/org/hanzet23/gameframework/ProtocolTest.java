package test.java.org.hanzet23.gameframework;

import main.java.org.hanzet23.gameframework.models.Protocol;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the Protocol class
 */
public class ProtocolTest extends TestCase {

	public ProtocolTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ProtocolTest.class);
	}
	
	public void testPlayerlist() {
		Protocol protocol = new Protocol();
		
		// Connect new player
		protocol.login("Bart");
		String[] players = protocol.getPlayerlist();
		
		// First player should equal "Bart"
		assertEquals("Bart", players[0]);
	}
	
	public void testGamelist() {
		Protocol protocol = new Protocol();

		// Get the actual gamelist
		String[] gamesActual = protocol.getGamelist();
		
		// Generate the expected gamelist
		String[] gamesExpected = {"Guess Game", "Guess Game Deluxe", "Ultra Guess Game"};

		assertEquals(gamesExpected, gamesActual);
	}
}

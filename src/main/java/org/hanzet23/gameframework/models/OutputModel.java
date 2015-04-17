package main.java.org.hanzet23.gameframework.models;

/**
 * This class represents the output stream to the game server. Sends commands to
 * the server through a socket connection
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 */
public class OutputModel {

	/**
	 * Sends the command string through the writer
	 * 
	 * @param command
	 */
	public synchronized void sendCommand(String command) {
		// Print the command to the console
		System.out.println("Client: " + command);
		// Send the command
		NetworkModel.getWriter().println(command);
	}

	/**
	 * Login with an username
	 * 
	 * @param name
	 */
	public void login(String name) {
		String command = "login " + name;
		sendCommand(command);
	}

	/**
	 * Logs the user out and closes the client's connection
	 */
	public void logout() {
		sendCommand("logout");
	}

	/**
	 * Get a list of all the games on the server
	 * 
	 * @return
	 */
	public void getGamelist() {
		sendCommand("get gamelist");
	}

	/**
	 * Get a list of all the players connected to the server
	 * 
	 * @return
	 */
	public void getPlayerlist() {
		sendCommand("get playerlist");
	}

	/**
	 * Subscribe for a game
	 * 
	 * @param name
	 */
	public void subscribe(String name) {
		String command = "subscribe " + name;
		sendCommand(command);
	}

	/**
	 * Make a game move
	 * 
	 * @param move
	 */
	public void move(String move) {
		String command = "move " + move;
		sendCommand(command);
	}

	/**
	 * Challenge a player for a game
	 * 
	 * @param player
	 * @param game
	 */
	public void challenge(String player, String game) {
		String command = "challenge \"" + player + "\" \"" + game + "\"";
		sendCommand(command);
	}

	/**
	 * Accept a challenge from an user
	 * 
	 * @param challengeNumber
	 */
	public void challengeAccept(String challengeNumber) {
		String command = "challenge accept " + challengeNumber;
		sendCommand(command);
	}

	/**
	 * Forfeit a match
	 */
	public void forfeit() {
		sendCommand("forfeit");
	}

}

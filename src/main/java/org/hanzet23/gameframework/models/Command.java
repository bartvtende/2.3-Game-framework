package main.java.org.hanzet23.gameframework.models;

import java.util.Stack;

public class Command {
	
	private Network network = null;
	
	public Command(int serverPort, String serverName) {
		this.network = new Network(serverPort, serverName);
	}

	public static void receiveCommand(String line) {		
		if (line.startsWith("SVR GAMELIST ")) {
			getGamelist(line);
		} else if (line.startsWith("SVR PLAYERLIST ")) {
			getPlayerlist(line);
		} else if (line.startsWith("SVR GAME MATCH ")) {
			getMatch(line);
		} else if (line.startsWith("SVR GAME YOURTURN ")) {
			getTurn(line);
		} else if (line.startsWith("SVR GAME MOVE ")) {
			getMove(line);
		} else if (line.startsWith("SVR GAME WIN ") || line.startsWith("SVR GAME LOSS ") || line.startsWith("SVR GAME DRAW ")) {
			getResult(line);
		} else if (line.startsWith("SVR GAME CHALLENGE ")) {
			getChallenge(line);
		} else if (line.startsWith("SVR GAME CHALLENGE CANCELLED")) {
			getChallengeCancelled(line);
		} else if (line.startsWith("SVR GAME ")) {
			getClose(line);
		} else {
			Command.printServerLine(line);
		}
	}
	
	public static void getGamelist(String line) {
		String[] games = Command.parseList(line);
		
		System.out.println("Received gamelist: " + line);
	}
	
	public static void getPlayerlist(String line) {
		String[] players = Command.parseList(line);
		
		System.out.println("Received playerlist: " + line);
	}
	
	public static void getMatch(String line) {
		System.out.println("Received match: " + line);
	}
		
	public static void getTurn(String line) {
		System.out.println("Received turn: " + line);
	}
	
	public static void getMove(String line) {
		System.out.println("Received move: " + line);
	}
	
	public static void getResult(String line) {
		System.out.println("Received result: " + line);
	}
	
	public static void getChallenge(String line) {
		System.out.println("Received challenge: " + line);
	}
	
	public static void getChallengeCancelled(String line) {
		System.out.println("Received challenge cancelled: " + line);
	}
	
	public static void getClose(String line) {
		System.out.println("Received closing connection: " + line);
	}
	
	/**
	 * Login with an username
	 * 
	 * @param name
	 */
	public void login(String name) {
		String command = "login " + name;
		network.sendCommand(command);
	}
	
	/**
	 * Logs the user out and closes the client's connection
	 */
	public void logout() {
		network.sendCommand("logout");
	}
	
	/**
	 * Get a list of all the games on the server
	 * 
	 * @return
	 */
	public void getGamelist() {
		network.sendCommand("get gamelist");
	}
	
	/**
	 * Get a list of all the players connected to the server
	 * 
	 * @return
	 */
	public void getPlayerlist() {
		network.sendCommand("get playerlist");
	}
	
	/**
	 * Subscribe for a game
	 * 
	 * @param name
	 */
	public void subscribe(String name) {
		String command = "subscribe " + name;
		network.sendCommand(command);
	}
	
	/**
	 * Make a game move
	 * 
	 * @param move
	 */
	public void move(String move) {
		String command = "move " + move;
		network.sendCommand(command);
	}
	
	/**
	 * Challenge a player for a game
	 * 
	 * @param player
	 * @param game
	 */
	public void challenge(String player, String game) {
		String command = "challenge \"" + player + "\" \"" + game + "\"";
		network.sendCommand(command);
	}
	
	/**
	 * Accept a challenge from an user
	 * 
	 * @param challengeNumber
	 */
	public void challengeAccept(String challengeNumber) {
		String command = "challenge accept " + challengeNumber;
		network.sendCommand(command);
	}
	
	/**
	 * Forfeit a match 
	 */
	public void forfeit() {
		network.sendCommand("forfeit");
	}
	

	/**
	 * Parse the string that includes a list and return an array
	 * 
	 * @param line
	 * @return
	 */
	public static String[] parseList(String line) {
		
		int firstBracket = line.indexOf('[');
		int lastBracket = line.indexOf(']');

		if (line.length() == 0 || firstBracket == -1 || lastBracket == -1) {
			return null;
		}
		
		firstBracket++;
		
		// Get the string without the brackets
		String newLine = line.substring(firstBracket, lastBracket);
		
		// Remove the quotation marks
		newLine = newLine.replace("\"", "");
		
		if (newLine == null) {
			Command.printClientLine("The parsed list is empty!");
			return null;
		}

		// Split the string with comma's
		String[] result = newLine.split("\\s*,\\s*");
		
		return result;
	}
	
	private static void parseMap(String line) {
		
	}

	/**
	 * Helper method to print the server string to the console
	 * 
	 * @param line
	 */
	public static void printServerLine(String line) {
		System.out.println("Server: " + line);
	}

	/**
	 * Helper method to print the server string to the console
	 * 
	 * @param line
	 */
	public static void printClientLine(String line) {
		System.out.println("Client: " + line);
	}
	
	public Network getNetwork() {
		return this.network;
	}	
}

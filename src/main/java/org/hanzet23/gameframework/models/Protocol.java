package main.java.org.hanzet23.gameframework.models;

import java.util.Stack;

public class Protocol {
	
	private Network network = null;
	
	private String lastCommand = null;
	private Stack<String> lastReceived = null;
	
	public Protocol() {
		this.network = Network.getInstance();
		this.lastReceived = new Stack<String>();
	}
	
	/**
	 * Login with an username
	 * 
	 * @param name
	 */
	public void login(String name) {
		String command = "login " + name;
		sendOutput(command, 1);
	}
	
	/**
	 * Logs the user out and closes the client's connection
	 */
	public void logout() {
		sendOutput("logout", 1);
	}
	
	/**
	 * Get a list of all the games on the server
	 * 
	 * @return
	 */
	public String[] getGamelist() {
		String[] lines = sendOutput("get gamelist", 2);
		String[] result = parseList(lines[1]);
		return result;
	}
	
	/**
	 * Get a list of all the players connected to the server
	 * @return
	 */
	public String[] getPlayerlist() {
		String[] lines = sendOutput("get playerlist", 2);
		String[] result = parseList(lines[1]);
		return result;
	}
	
	public boolean subscribe(String name) {
		String command = "subscribe " + name;
		String[] input = sendOutput(command, 1);
		if (checkForErrors(input[0])) {
			return false;
		}
		return true;
	}
	
	public boolean move(String move) {
		String command = "move " + move;
		String[] input = sendOutput(command, 1);
		if (checkForErrors(input[0])) {
			return false;
		}
		return true;
	}
	
	public boolean challenge(String player, String game) {
		String command = "challenge \"" + player + "\" \"" + game + "\"";
		String[] input = sendOutput(command, 1);
		if (checkForErrors(input[0])) {
			return false;
		}
		return true;
	}
	
	public boolean challengeAccept(String challengeNumber) {
		String command = "challenge accept " + challengeNumber;
		String[] input = sendOutput(command, 1);
		if (checkForErrors(input[0])) {
			return false;
		}
		return true;
	}
	
	public void forfeit() {
		sendOutput("forfeit", 1);
	}
	
	public void receivedMessage(String command) {
		String[] splittedCommand = command.split("\\s");

		if (splittedCommand[0].equals("SVR") && splittedCommand[1].equals("GAME")) {
			System.out.println("SERVER MESSAGE: " + command);
		} else {
			lastReceived.push(command);
			System.out.println("POPJE: " + lastReceived.pop());
		}
	}
	
	/**
	 * Send output to the server and receiving the input
	 * 
	 * @param command
	 */
	private synchronized String[] sendOutput(String command, int amountOfLines) {
		// Save command
		this.lastCommand = command;
		
		// Send output to server
		printClientLine(command);
		String[] lines = this.network.sendCommand(command, amountOfLines);

		
		for (int i = 0; i < amountOfLines; i++) {
			boolean errors = checkForErrors(lines[i]);
			if (!errors) {
				printServerLine(lines[i]);
			}
		}
		return lines;
	}

	/**
	 * Checks if the received string contains errors
	 * 
	 * @param line
	 * @return
	 */
	private boolean checkForErrors(String line) {
		String error = null;
		int length = 0; 
		if (line != null)
			 length = line.length();
		
		if (length > 3) {
			error = line.substring(0, 3);
		}

		// There was an error
		if ((error != null && error.equalsIgnoreCase("ERR")) || length == 0) {
			printServerLine("There was an error: " + line);
			return true;
		}
		return false;
	}

	/**
	 * Parse the string that includes a list and return an array
	 * 
	 * @param line
	 * @return
	 */
	private String[] parseList(String line) {
		int firstBracket = line.indexOf('[') + 1;
		int lastBracket = line.indexOf(']');
		
		// Get the string without the brackets
		String newLine = line.substring(firstBracket, lastBracket);		
		
		// Remove the quotation marks
		newLine = newLine.replace("\"", "");
		
		if (newLine == null) {
			printClientLine("The parsed list is empty!");
			return null;
		}

		// Split the string with comma's
		String[] result = newLine.split("\\s*,\\s*");
		
		return result;
	}
	
	private void parseMap(String line) {
		
	}
	
	/**
	 * Helper method to print the server string to the console
	 * 
	 * @param line
	 */
	private void printServerLine(String line) {
		System.out.println("Server: " + line);
	}

	/**
	 * Helper method to print the server string to the console
	 * 
	 * @param line
	 */
	private void printClientLine(String line) {
		System.out.println("Client: " + line);
	}

}

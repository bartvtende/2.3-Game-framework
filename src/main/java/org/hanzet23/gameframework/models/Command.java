package main.java.org.hanzet23.gameframework.models;

import java.util.Stack;

public class Command {
	
	public static Stack<String> receivedMessages = null;
	
	private Protocol protocol = null;
	
	public Command(int serverPort, String serverName) {
		this.protocol = new Protocol(serverPort, serverName);
		Command.receivedMessages = new Stack<String>();
	}

	public static void receiveCommand(String line) {
		String[] splittedCommand = line.split("\\s");

		if (splittedCommand[0].equals("SVR") && splittedCommand[1].equals("GAME")) {
			System.out.println("SERVER MESSAGE: " + line);
		} else {
			Command.receivedMessages.push(line);
			printServerLine(Command.receivedMessages.peek());
		}
	}
	
	/**
	 * Login with an username
	 * 
	 * @param name
	 */
	public void login(String name) {
		String command = "login " + name;
		protocol.sendOutput(command);
	}
	
	/**
	 * Logs the user out and closes the client's connection
	 */
	public void logout() {
		protocol.sendOutput("logout");
	}
	
	/**
	 * Get a list of all the games on the server
	 * 
	 * @return
	 */
	public void getGamelist() {
		protocol.sendOutput("get gamelist");
	}
	
	/**
	 * Get a list of all the players connected to the server
	 * @return
	 */
	public void getPlayerlist() {
		protocol.sendOutput("get playerlist");
	}
	
	public void subscribe(String name) {
		String command = "subscribe " + name;
		protocol.sendOutput(command);
	}
	
	public void move(String move) {
		String command = "move " + move;
		protocol.sendOutput(command);
	}
	
	public void challenge(String player, String game) {
		String command = "challenge \"" + player + "\" \"" + game + "\"";
		protocol.sendOutput(command);
	}
	
	public void challengeAccept(String challengeNumber) {
		String command = "challenge accept " + challengeNumber;
		protocol.sendOutput(command);
	}
	
	public void forfeit() {
		protocol.sendOutput("forfeit");
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
	
	public Protocol getProtocol() {
		return this.protocol;
	}
	
	public Stack<String> getReceivedMessages() {
		return Command.receivedMessages;
	}
	
}

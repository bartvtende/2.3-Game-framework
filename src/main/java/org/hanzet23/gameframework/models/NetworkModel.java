package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JOptionPane;

import main.java.org.hanzet23.gameframework.views.MainView;

public class NetworkModel implements Runnable {
	
	public static NetworkModel instance = null;
	
	private int serverPort = 7789;
	private String serverName = "bartvantende.nl";
	
	protected PrintWriter output = null;
	protected BufferedReader input = null;
	
	private Thread thread = null;
	
	private Socket client;

	private NetworkModel(int port, String serverName) {
		if (port != 0)
			this.serverPort = port;
		if (serverName != null)
			this.serverName = serverName;
		connectToServer();
	}
	
	public static NetworkModel getInstance() {
		if (instance == null) {
			instance = new NetworkModel(0, null);
		}
		return instance;
	}
	
	public static NetworkModel setInstance(int port, String serverName) {
		if (instance == null) {
			instance = new NetworkModel(port, serverName);
		}
		return instance;
	}
	
	/**
	 * Opens a socket connection to the game server
	 */
	public void connectToServer() {
		try {
			System.out.println("Connecting to " + serverName
					+ " on port: " + serverPort);
			
			// Open a new socket connection
			this.client = new Socket(serverName, serverPort);

			System.out.println("Connected to: "
					+ client.getRemoteSocketAddress());

			// Get the output
			this.output = new PrintWriter(client.getOutputStream(), true);

			// Get the input
			this.input = new BufferedReader(new InputStreamReader(
	                client.getInputStream()));

			// Print the first two received lines
			for (int i = 0; i < 2; i++) {
				System.out.println("Server: " + this.input.readLine());
			}

			this.thread = new Thread(this);
			this.thread.start();
		} catch (IOException e) {	
			JOptionPane.showMessageDialog(MainView.mainview, "Can't connect to the server, please try again.", "Can't connect!", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Closes a connection to the server
	 */
	public void closeConnection() {
		try {
			if (client != null)
				client.close();
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		} catch (IOException e) {
			System.out.println("Unable to close the socket connection!");
		}
	}
	
	public synchronized void sendCommand(String command) {
		// Print the command
		System.out.println("Client: " + command);
		// Send the command
		this.output.println(command);
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
	
	@Override
	public void run() {
		try {
			String line;
			while ((line = this.input.readLine()) != null) {
				CommandModel command = new CommandModel();
				command.receiveCommand(line);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return this.client;
	}
	
	public PrintWriter getOutput() {
		return this.output;
	}
	
	public BufferedReader getInput() {
		return this.input;
	}
}

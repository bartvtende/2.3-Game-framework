package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Network {
	
	private static Network instance = null;

	private static final int SERVER_PORT = 7789;
	private static final String SERVER_NAME = "localhost";
	
	protected PrintWriter output = null;
	protected BufferedReader input = null;
	
	private Socket client;

	private Network() {
		connectToServer();
		startConnectionThread();
	}

	/**
	 * Creates a new Network class
	 */
	private synchronized static void createInstance () {
        if (instance == null) {
        	instance = new Network();
        }
    }

	/**
	 * Singleton design pattern to ensure that only one connection is open
	 * 
	 * @return
	 */
    public static Network getInstance() {
        if (instance == null) {
        	createInstance();
        }
        return instance;
	}
	
	/**
	 * Opens a socket connection to the game server
	 * 
	 * @param port
	 */
	public void connectToServer() {
		try {
			System.out.println("Connecting to " + SERVER_NAME
					+ " on port: " + SERVER_PORT);
			
			// Open a new socket connection
			this.client = new Socket(SERVER_NAME, SERVER_PORT);

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
		} catch (IOException e) {
			e.printStackTrace();
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
	
	private void startConnectionThread() {
		NetworkConnection connection = new NetworkConnection(this.input);
		connection.start();
	}
	
	public synchronized String[] sendCommand(String command, int amountOfLines) {
		String[] lines = new String[amountOfLines];
		// Send the command
		this.output.println(command);

		// Receive the input
		try {
			for (int i = 0; i < amountOfLines; i++) {
				lines[i] = this.input.readLine();
			}
		} catch (IOException e) {
			System.out.println("Something went wrong reading the input in Network.sendCommand()");
		}
		return lines;
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

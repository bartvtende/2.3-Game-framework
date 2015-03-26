package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkModel implements Runnable {
	
	private int serverPort = 7789;
	private String serverName = "bartvantende.nl";
	
	protected PrintWriter output = null;
	protected BufferedReader input = null;
	
	private Thread thread = null;
	
	private Socket client;

	public NetworkModel(int port, String serverName) {
		if (port != 0)
			this.serverPort = port;
		if (serverName != null)
			this.serverName = serverName;
		connectToServer();
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
	
	public synchronized void sendCommand(String command) {
		// Print the command
		CommandModel.printClientLine(command);
		// Send the command
		this.output.println(command);
	}
	
	@Override
	public void run() {
		try {
			String line;
			while ((line = this.input.readLine()) != null) {
				CommandModel.receiveCommand(line);
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

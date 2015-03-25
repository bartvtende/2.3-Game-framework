package main.java.org.hanzet23.gameframework.models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Network {
	
	private static Network instance = null;

	private static final int SERVER_PORT = 7789;
	private static final String SERVER_NAME = "localhost";
	
	private DataOutputStream output = null;
	private DataInputStream input = null;
	
	private Socket client;

	private Network() {
		connectToServer();
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
			OutputStream outputToServer = client.getOutputStream();
			this.output = new DataOutputStream(outputToServer);

			this.output.writeUTF("Hello from " + client.getLocalSocketAddress());

			// Get the input
			InputStream inputFromServer = client.getInputStream();
			this.input = new DataInputStream(inputFromServer);

			System.out.println("Server says " + this.input.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes a connection to the server
	 */
	public void closeConnection() {
		try {
			client.close();
		} catch (IOException e) {
			System.out.println("Unable to close the socket connection!");
		}
	}
	
	public Socket getSocket() {
		return this.client;
	}
	
	public DataOutputStream getOutput() {
		return this.output;
	}
	
	public DataInputStream getInput() {
		return this.input;
	}

}

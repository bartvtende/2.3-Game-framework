package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import main.java.org.hanzet23.gameframework.views.MainView;

public class NetworkModel implements Runnable {
	
	public static NetworkModel instance = null;
	
	private int serverPort = 7789;
	private String serverName = "bartvantende.nl";
	
	private static InputModel inputModel = null;
	private static OutputModel outputModel = null;
	
	private static PrintWriter writer = null;
	private static BufferedReader reader = null;
	
	private Thread thread = null;
	private Socket client;

	private NetworkModel(int port, String serverName) {
		if (port != 0)
			this.serverPort = port;
		if (serverName != null)
			this.serverName = serverName;
		connectToServer();
		inputModel = new InputModel();
		outputModel = new OutputModel();
	}
	
	public static NetworkModel getInstance() {
		if (instance == null || instance.getSocket() == null) {
			instance = new NetworkModel(0, null);
		}
		return instance;
	}
	
	public static NetworkModel setInstance(int port, String serverName) {
		if (instance == null || instance.getSocket() == null) {
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
			client = new Socket(serverName, serverPort);

			System.out.println("Connected to: "
					+ client.getRemoteSocketAddress());

			// Get the output
			writer = new PrintWriter(client.getOutputStream(), true);

			// Get the input
			reader = new BufferedReader(new InputStreamReader(
	                client.getInputStream()));

			// Print the first two received lines
			for (int i = 0; i < 2; i++) {
				System.out.println("Server: " + reader.readLine());
			}

			thread = new Thread(this);
			thread.start();
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
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			System.out.println("Unable to close the socket connection!");
		}
	}
	
	@Override
	public void run() {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				InputModel command = new InputModel();
				command.receiveCommand(line);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return client;
	}
	
	public OutputModel getOutput() {
		return outputModel;
	}
	
	public InputModel getInput() {
		return inputModel;
	}
	
	public static PrintWriter getWriter() {
		return writer;
	}
	
	public BufferedReader getReader() {
		return reader;
	}
}

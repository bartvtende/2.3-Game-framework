package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import main.java.org.hanzet23.gameframework.games.BoardModel;
import main.java.org.hanzet23.gameframework.views.MainView;

/**
 * This class makes the socket connection to the server.
 * 
 * @authors Groep 2: Jonathan Berends, Bart van 't Ende, Joz Reijneveld en
 *          Jan-Bert van Slochteren
 */
public class NetworkModel implements Runnable {

	public static NetworkModel instance = null;

	// The BoardModel for this connection
	public static BoardModel board = null;

	// Default connection properties
	private int serverPort = 7789;
	private String serverName = "bartvantende.nl";

	// The input (receiving) and output (sending) classes
	private static InputModel input = null;
	private static OutputModel output = null;

	private static PrintWriter writer = null;
	private static BufferedReader reader = null;

	private Thread thread = null;
	private Socket client;

	/**
	 * Constructor for this class
	 * 
	 * @param port
	 * @param serverName
	 */
	private NetworkModel(int port, String serverName) {
		if (port != 0)
			this.serverPort = port;
		if (serverName != null)
			this.serverName = serverName;
		connectToServer();
		input = new InputModel();
		output = new OutputModel();
	}

	/**
	 * Singleton implementation: only able to make one instance of this class.
	 * Returns the (only) instance of this class
	 * 
	 * @return
	 */
	public static NetworkModel getInstance() {
		if (instance == null || instance.getSocket() == null) {
			instance = new NetworkModel(0, null);
		}
		return instance;
	}

	/**
	 * Singleton implementation: only able to make one instance of this class.
	 * Creates only one instance of this class, calls the constructor.
	 * 
	 * @param port
	 * @param serverName
	 * @return
	 */
	public static NetworkModel setInstance(int port, String serverName) {
		if (instance == null || instance.getSocket() == null) {
			instance = new NetworkModel(port, serverName);
		}
		return instance;
	}

	/**
	 * Opens a socket connection to the game server. Also start a new thread for
	 * receiving commands from the server
	 */
	public void connectToServer() {
		try {
			System.out.println("Connecting to " + serverName + " on port: "
					+ serverPort);

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
			JOptionPane.showMessageDialog(MainView.mainview,
					"Can't connect to the server, please try again.",
					"Can't connect!", JOptionPane.ERROR_MESSAGE);
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

	/**
	 * Thread listening to the BufferedReader class for commands from the server
	 */
	@Override
	public void run() {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				// Send the received command to the InputModel class
				input.receiveCommand(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter for the socket connection class
	 * 
	 * @return
	 */
	public Socket getSocket() {
		return client;
	}

	/**
	 * Getter for the OutputModel class
	 * 
	 * @return
	 */
	public OutputModel getOutput() {
		return output;
	}

	/**
	 * Getter for the InputModel class
	 * 
	 * @return
	 */
	public InputModel getInput() {
		return input;
	}

	/**
	 * Getter for the PrintWriter object
	 * 
	 * @return
	 */
	public static PrintWriter getWriter() {
		return writer;
	}

	/**
	 * Getter for the BufferedReader object
	 * 
	 * @return
	 */
	public BufferedReader getReader() {
		return reader;
	}
}

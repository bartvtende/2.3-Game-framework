package main.java.org.hanzet23.gameframework.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import main.java.org.hanzet23.gameframework.controllers.GamesController;
import main.java.org.hanzet23.gameframework.controllers.PlayersController;
import main.java.org.hanzet23.gameframework.games.tictactoe.TicTacToeModel;
import main.java.org.hanzet23.gameframework.views.MainView;

/**
 * This class represents the input stream to the game server. Receives commands
 * send by the server through a socket connection
 * 
 * @authors Groep 2: Jonathan Berends, Bart van 't Ende, Joz Reijneveld en
 *          Jan-Bert van Slochteren
 */
public class InputModel {
	
	/**
	 * Sends the command received by the server to the corresponding method
	 * 
	 * @param line
	 */
	public void receiveCommand(String line) {
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
		} else if (line.startsWith("SVR GAME WIN ")
				|| line.startsWith("SVR GAME LOSS ")
				|| line.startsWith("SVR GAME DRAW ")) {
			getResult(line);
		} else if (line.startsWith("SVR GAME CHALLENGE ")) {
			getChallenge(line);
		} else if (line.startsWith("SVR GAME CHALLENGE CANCELLED")) {
			getChallengeCancelled(line);
		} else if (line.startsWith("SVR GAME ")) {
			getClose(line);
		} else if (line.equals("ERR Duplicate name exists")) {
			getTakenUsername();
		}
		printServerLine(line);
	}

	/**
	 * Gets the list of games from the server
	 * 
	 * @param line
	 */
	public void getGamelist(String line) {
		// Parse the received games
		String[] games = parseList(line);

		// Setup the games in the GUI
		GamesController gamesPanel = MainView.mainview.games;
		gamesPanel.setGamesList(games);
		gamesPanel.setupGames();
		gamesPanel.revalidate();
	}

	/**
	 * Gets the list of players from the server
	 * 
	 * @param line
	 */
	public void getPlayerlist(String line) {
		// Parse the received players
		String[] players = parseList(line);
		ArrayList<String> allPlayers = new ArrayList<String>();
		// Get your logged in name
		String name = MainView.connection.getSelectedName();

		// Remove your name off the list of players
		for (String key : players) {
			if (!key.equals(name)) {
				allPlayers.add(key);
			}
		}

		String[] newPlayers = new String[allPlayers.size()];
		allPlayers.toArray(newPlayers);

		// Setup the players in the GUI
		PlayersController playerPanel = MainView.mainview.players;
		playerPanel.setPlayersList(newPlayers);
		playerPanel.setupPlayers();
		playerPanel.revalidate();
	}

	/**
	 * Gets a match from the server including info
	 * 
	 * @param line
	 */
	public void getMatch(String line) {
		// Parse the received match info
		HashMap<String, String> map = parseMap(line);

		// Get the variables
		String gameName = map.get("GAMTYPE");
		String opponent = map.get("OPPONENT");
		String playerToMove = map.get("PLAYERTOMOVE");

		String playerType = MainView.games.getPlayerType();
		String playingAs = MainView.connection.getSelectedName();

		// Make a player and game class
		PlayerModel player = new PlayerModel(playerType, playingAs, opponent);
		GameModel game = new TicTacToeModel(gameName);
		
		game.startGame();

		// Initialize the board
		BoardModel newBoard = new BoardModel(player, game);
		NetworkModel.board = newBoard;

		// Check if we have to start first
		/*
		if (!playerToMove.equals(opponent)) {
			// Make a move
			NetworkModel.board.move();
		}*/
	}

	/**
	 * Gets turn info
	 * 
	 * @param line
	 */
	public void getTurn(String line) {
		NetworkModel.board.move();
	}

	/**
	 * Gets the result of a move
	 * 
	 * @param line
	 */
	public void getMove(String line) {
		HashMap<String, String> map = parseMap(line);

		// Check the input
		printServerLine("Got a move back, info:");

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			printServerLine(key + ": " + value);
		}

		// If error: make move again
	}

	/**
	 * Gets the result of a game (WIN, LOSS or DRAW)
	 * 
	 * @param line
	 */
	public void getResult(String line) {
		HashMap<String, String> map = parseMap(line);

		// Send the result to the game class and stop the game

		String[] splitted = line.split("\\s");
		String result = splitted[2];
		System.out.println(result);

		// Delete the game class and stop the games view
		NetworkModel.board.stopGame();
		NetworkModel.board = null;
	}

	/**
	 * Accept a challenge from a player for a game
	 * 
	 * @param line
	 */
	public void getChallenge(String line) {
		HashMap<String, String> map = parseMap(line);

		String challengeNumber = map.get("CHALLENGENUMBER");

		NetworkModel network = NetworkModel.getInstance();

		network.getOutput().challengeAccept(challengeNumber);

		// Create a new static game class (and make a move)?
	}

	/**
	 * Challenge has been expired/denied
	 * 
	 * @param line
	 */
	public void getChallengeCancelled(String line) {
		HashMap<String, String> map = parseMap(line);

		// Delete the game class and stop the games view
	}

	/**
	 * Match is over, either the player disconnected or the player has forfeited
	 * 
	 * @param line
	 */
	public void getClose(String line) {
		HashMap<String, String> map = parseMap(line);
		
		System.out.println("Test close");

		// Delete the game class and stop the games view
		NetworkModel.board.stopGame();
		NetworkModel.board = null;
	}

	/**
	 * Connect again if the selected username is taken
	 */
	public void getTakenUsername() {
		// Remove views
		MainView.mainview.removeGames();
		MainView.mainview.removePlayers();

		JOptionPane.showMessageDialog(MainView.mainview,
				"Your username has been taken, trying another username.",
				"Username has been taken!", JOptionPane.ERROR_MESSAGE);
		NetworkModel network = NetworkModel.getInstance();

		int randomNumber = (new Random()).nextInt(999);
		network.getOutput().login("Winnaar #" + randomNumber);

		network.getOutput().getPlayerlist();
	}

	/**
	 * Parse the string that includes a list and return an array
	 * 
	 * @param line
	 * @return
	 */
	public String[] parseList(String line) {
		// Split the string with comma's
		String[] result = parseString(line, '[', ']');

		return result;
	}

	/**
	 * Parse the string that includes a map and return a HashMap
	 * 
	 * @param line
	 * @return
	 */
	public HashMap<String, String> parseMap(String line) {
		// Split the string with comma's
		String[] result = parseString(line, '{', '}');

		if (result == null) {
			return null;
		}

		HashMap<String, String> map = new HashMap<String, String>();

		for (String entry : result) {
			String[] splitted = entry.split("\\s*:\\s*");
			map.put(splitted[0], splitted[1]);
		}

		return map;
	}

	/**
	 * Helper method to parse a string into an array by using identifiers
	 * 
	 * @param line
	 * @param firstIdentifier
	 * @param endIdentifier
	 * @return
	 */
	private String[] parseString(String line, char firstIdentifier,
			char endIdentifier) {
		int firstBracket = line.indexOf(firstIdentifier);
		int lastBracket = line.indexOf(endIdentifier);

		if (line.length() == 0 || firstBracket == -1 || lastBracket == -1) {
			return null;
		}

		firstBracket++;

		// Get the string without the brackets
		String newLine = line.substring(firstBracket, lastBracket);

		// Remove the quotation marks
		newLine = newLine.replace("\"", "");

		if (newLine == null) {
			printClientLine("The parsed map is empty!");
			return null;
		}

		// Split the string with comma's
		String[] result = newLine.split("\\s*,\\s*");

		return result;
	}

	/**
	 * Helper method to print the server string to the console
	 * 
	 * @param line
	 */
	public void printServerLine(String line) {
		System.out.println("Server: " + line);
	}

	/**
	 * Helper method to print the server string to the console
	 * 
	 * @param line
	 */
	public void printClientLine(String line) {
		System.out.println("Client: " + line);
	}
}

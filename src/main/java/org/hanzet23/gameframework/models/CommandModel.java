package main.java.org.hanzet23.gameframework.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import main.java.org.hanzet23.gameframework.controllers.GamesController;
import main.java.org.hanzet23.gameframework.controllers.PlayersController;
import main.java.org.hanzet23.gameframework.views.MainView;

public class CommandModel {

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
		} else if (line.startsWith("SVR GAME WIN ") || line.startsWith("SVR GAME LOSS ") || line.startsWith("SVR GAME DRAW ")) {
			getResult(line);
		} else if (line.startsWith("SVR GAME CHALLENGE ")) {
			getChallenge(line);
		} else if (line.startsWith("SVR GAME CHALLENGE CANCELLED")) {
			getChallengeCancelled(line);
		} else if (line.startsWith("SVR GAME ")) {
			getClose(line);
		} else if (line.equals("ERR Duplicate name exists")) {
			getTakenUsername();
		} else {
			printServerLine(line);
		}
	}
	
	/**
	 * Gets the list of games from the server
	 * 
	 * @param line
	 */
	public void getGamelist(String line) {
		String[] games = parseList(line);
		
		// Setup the games in the GUI
		GamesController gamesPanel = MainView.mainview.games;
		gamesPanel.setGamesList(games);
		gamesPanel.setupGames();
		gamesPanel.revalidate();
		
		printServerLine(line);
	}
	
	/**
	 * Gets the list of players from the server
	 * 
	 * @param line
	 */
	public void getPlayerlist(String line) {
		String[] players = parseList(line);
		ArrayList<String> allPlayers = new ArrayList<String>();
		String name = MainView.connection.getSelectedName();
		
		System.out.println("Your loginname is: " + name);

		// Remove your name off the list
		for (String key : players) {
			if (!key.equals(name)) {
				allPlayers.add(key);
			}
		}
		
		String[] newPlayers = new String[allPlayers.size()];
		allPlayers.toArray(newPlayers);
		
		// Setup the games in the GUI
		PlayersController playerPanel = MainView.mainview.players;
		playerPanel.setPlayersList(newPlayers);
		playerPanel.setupPlayers();
		playerPanel.revalidate();
		
		System.out.println("Received playerlist: " + line);
	}
	
	/**
	 * Gets a match from the server including info
	 * 
	 * @param line
	 */
	public void getMatch(String line) {
		HashMap<String, String> map = parseMap(line);
		
		System.out.println("Received match: " + line);
	}
		
	/**
	 * Gets turn info
	 * 
	 * @param line
	 */
	public void getTurn(String line) {
		HashMap<String, String> map = parseMap(line);
		
		System.out.println("Received turn: " + line);
	}
	
	/**
	 * 	Gets the result of a move
	 * 
	 * @param line
	 */
	public void getMove(String line) {
		HashMap<String, String> map = parseMap(line);
		
		System.out.println("Received move: " + line);
	}
	
	/**
	 * Gets the result of a game (WIN, LOSS or DRAW)
	 * 
	 * @param line
	 */
	public void getResult(String line) {
		HashMap<String, String> map = parseMap(line);
		
		String[] splitted = line.split("\\s");
		String result = splitted[2];
	}
	
	/**
	 * Challenge a player for a game
	 * 
	 * @param line
	 */
	public void getChallenge(String line) {
		HashMap<String, String> map = parseMap(line);
		
		String challengeNumber = map.get("CHALLENGENUMBER");
		
		NetworkModel network = NetworkModel.getInstance();
		
		
		network.challengeAccept(challengeNumber);
		
		System.out.println("Received challenge: " + line);
	}
	
	/**
	 * Challenge has been expired/denied
	 * 
	 * @param line
	 */
	public void getChallengeCancelled(String line) {
		HashMap<String, String> map = parseMap(line);
		
		System.out.println("Received challenge cancelled: " + line);
	}
	
	/**
	 * Match is over, either the player disconnected or the player has forfeited
	 * 
	 * @param line
	 */
	public void getClose(String line) {
		HashMap<String, String> map = parseMap(line);
		
		System.out.println("Received closing connection: " + line);
	}
	
	
	public void getTakenUsername() {
		// Remove views
		MainView.mainview.removeGames();
		MainView.mainview.removePlayers();
		
		JOptionPane.showMessageDialog(MainView.mainview, "Your username has been taken, trying another username.", "Username has been taken!", JOptionPane.ERROR_MESSAGE);
		NetworkModel network = NetworkModel.getInstance();
		
		int randomNumber = (new Random()).nextInt(999);
		network.login("Winnaar #" + randomNumber);
		
		network.getPlayerlist();
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
	private String[] parseString(String line, char firstIdentifier, char endIdentifier) {
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

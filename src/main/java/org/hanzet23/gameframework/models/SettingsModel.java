package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This model manipulates the values within the settings file in the root of the
 * application
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class SettingsModel {

	// Default path to the settings file
	private String settingsPath = "settings.conf";

	// BufferedReader object for this class
	private BufferedReader reader = null;

	// LinkedHashMap holds the data from the settings file in an ordered way
	private LinkedHashMap<String, String> map = null;

	/**
	 * Constructor for the SettingsModel
	 */
	public SettingsModel() {
	}

	/**
	 * Constructor for the SettingsModel taking a custom settingsPath (used for
	 * testing)
	 */
	public SettingsModel(String settingsPath) {
		this.settingsPath = settingsPath;
	}

	/**
	 * Parses the settings from the settings file and returns them in an ordered
	 * LinkedHashMap
	 * 
	 * @return
	 */
	public LinkedHashMap<String, String> getSettings() {
		try {
			// Try to open the file in a BufferedReader
			File file = new File(settingsPath);
			this.reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to open the settings file!");
		}

		// Instantiate a new LinkedHashMap
		map = new LinkedHashMap<String, String>();
		String text = null;

		try {
			while ((text = this.reader.readLine()) != null) {
				// Parse the line with the '=' identifier
				String[] splitted = text.split("=");
				// Put the result in the LinkedHashMap
				map.put(splitted[0], splitted[1]);
			}
		} catch (IOException e) {
			System.out
					.println("Something went wrong parsing the settings file!");
		}
		return map;
	}

	/**
	 * Replaces an item in the LinkedHashMap
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean writeSettings(String key, String value) {
		// Get the settings
		HashMap<String, String> map = getSettings();

		// Check if the map even contains the key
		if (!map.containsKey(key)) {
			return false;
		} else {
			// Remove the key and store the new key and value
			map.replace(key, value);
			// Write the LinkedHashMap to the settings file
			addItem(map);
		}
		return true;
	}

	/**
	 * Writes the (new) LinkedHashMap back to the settings file
	 * 
	 * @param map
	 */
	public void addItem(HashMap<String, String> map) {
		try {
			// Open the file
			PrintWriter output = new PrintWriter(settingsPath);
			String line = null;
			// Loop over the settings map and write it to the file
			for (Map.Entry<String, String> entry : map.entrySet()) {
				line = entry.getKey() + "=" + entry.getValue();
				output.println(line);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out
					.println("Something went wrong saving the settings file!");
		}
	}

	/**
	 * Parses the contents in the games variable in the settings file for easy
	 * access
	 * 
	 * @return
	 */
	public LinkedHashMap<String, String> getGames() {
		// Instantiate a new LinkedHashMap
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		// Get the settings LinkedHashMap
		LinkedHashMap<String, String> settingsMap = getSettings();

		// Get the contents of the key "games"
		String gamesString = settingsMap.get("games");
		// Split the values
		String[] gamesSplitted = gamesString.split(",");

		// Loop through the splitted string
		for (String value : gamesSplitted) {
			// Split the string again and put it in the map
			String[] array = value.split(":");
			map.put(array[0], array[1]);
		}

		return map;
	}
}

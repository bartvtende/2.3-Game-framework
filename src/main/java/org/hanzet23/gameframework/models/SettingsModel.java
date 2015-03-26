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

public class SettingsModel {

	private String settingsPath = "settings.conf";
	private BufferedReader reader = null;
	private LinkedHashMap<String, String> map = null;

	public SettingsModel() {
	}
	
	public SettingsModel(String settingsPath) {
		this.settingsPath = settingsPath;
	}

	public LinkedHashMap<String, String> getSettings() {
		try {
			File file = new File(settingsPath);
			this.reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to open the settings file!");
		}
		
		map = new LinkedHashMap<String, String>();
		String text = null;

		try {
			while ((text = this.reader.readLine()) != null) {
				String[] splitted = text.split("=");
				map.put(splitted[0], splitted[1]);
			}
		} catch (IOException e) {
			System.out
					.println("Something went wrong parsing the settings file!");
		}
		return map;
	}
	
	public boolean writeSettings(String key, String value) {
		// Get the settings
		LinkedHashMap<String, String> map = getSettings();
		
		// Check if the map even contains the key
		if (!map.containsKey(key)) {
			return false;
		} else {
			// Remove the key and store the new key and value
			map.replace(key, value);
			addItem(map);
		}
		return true;
	}
	
	public void addItem(HashMap<String, String> map) {
		try {
			// Open the file
			PrintWriter output = new PrintWriter(settingsPath);
			String line = null;
			for (Map.Entry<String, String> entry: map.entrySet()) {
				line = entry.getKey() + "=" + entry.getValue();
				output.println(line);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong saving the settings file!");
		}
	}
}

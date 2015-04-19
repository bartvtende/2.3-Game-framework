package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.org.hanzet23.gameframework.models.SettingsModel;

/**
 * JFrame that constructs the settings file to a friendly view, to easily change
 * important game settings
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class SettingsController extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton update;
	private JFrame frame;

	private LinkedHashMap<String, String> map;
	private ArrayList<JTextField> textFieldList;

	/**
	 * Constructor for SettingsController
	 */
	public SettingsController() {
		frame = this;
		setupButton();

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));

		center = setupPanels(center);

		this.add(center, BorderLayout.CENTER);

		this.add(update, BorderLayout.SOUTH);
	}

	/**
	 * Sets up the "Update settings" button within the Settings view
	 */
	private void setupButton() {
		update = new JButton("Update settings");

		/**
		 * ActionListener for the settings refresh button, updates the settings
		 */
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();

				SettingsModel settingsModel = new SettingsModel();

				// Loop through each value in the textfield and settingsModel
				int i = 0;
				for (Entry<String, String> setting : map.entrySet()) {
					String newValue = textFieldList.get(i).getText();
					String oldValue = setting.getValue();

					// If values don't match, update the settings
					if (!newValue.equals(oldValue)) {
						settingsModel.writeSettings(setting.getKey(), newValue);
					}
					i++;
				}
			}
		});
	}

	/**
	 * Constructs a settings view from the user's settings file
	 * 
	 * @param center
	 * @return
	 */
	private JPanel setupPanels(JPanel center) {
		// Get all the settings from the settings file
		SettingsModel settingsModel = new SettingsModel();
		map = settingsModel.getSettings();
		textFieldList = new ArrayList<JTextField>();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		// Iterate through the HashMap and add new panels for each entry
		for (Entry<String, String> setting : map.entrySet()) {
			String key = setting.getKey().replaceAll("_", " ");
			key = key.substring(0, 1).toUpperCase() + key.substring(1);
			String value = setting.getValue();

			// Save the TextField in an ArrayList
			JTextField textField = new JTextField(value);
			textFieldList.add(textField);
			JPanel newPanel = new JPanel();
			newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
			newPanel.add(new JLabel(key));
			newPanel.add(textField);

			// Add the panel
			center.add(newPanel);
			// Add some spacing
			center.add(Box.createRigidArea(new Dimension(0, 5)));
		}
		return center;
	}
}

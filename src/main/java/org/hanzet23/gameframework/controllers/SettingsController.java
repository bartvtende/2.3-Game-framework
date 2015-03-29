package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.org.hanzet23.gameframework.models.SettingsModel;

public class SettingsController extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton update;
	private JFrame frame;

	private LinkedHashMap<String, String> map;
	private ArrayList<JTextField> textFieldList;

	public SettingsController() {
		frame = this;
		setupButton();

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));

		center = setupPanels(center);

		this.add(center, BorderLayout.CENTER);

		this.add(update, BorderLayout.SOUTH);
	}

	private void setupButton() {
		update = new JButton("Update settings");

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();

				SettingsModel settingsModel = new SettingsModel();

				int i = 0;
				for (Entry<String, String> setting : map.entrySet()) {
					String newValue = textFieldList.get(i).getText();
					String oldValue = setting.getValue();

					// If values don't match, update them
					if (!newValue.equals(oldValue)) {
						settingsModel.writeSettings(setting.getKey(), newValue);
					}
					i++;
				}
			}
		});
	}

	private JPanel setupPanels(JPanel center) {
		// Get all the settings from the settings file
		SettingsModel settingsModel = new SettingsModel();
		map = settingsModel.getSettings();
		textFieldList = new ArrayList<JTextField>();

		// Iterate through the HashMap and add new panels for each entry
		for (Entry<String, String> setting : map.entrySet()) {
			String key = setting.getKey();
			String value = setting.getValue();

			// Save the TextField in an ArrayList
			JTextField textField = new JTextField(value);
			textFieldList.add(textField);

			JPanel newPanel = new JPanel();
			newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
			newPanel.add(new JLabel(key));
			newPanel.add(textField);

			center.add(newPanel);
		}
		return center;
	}
}

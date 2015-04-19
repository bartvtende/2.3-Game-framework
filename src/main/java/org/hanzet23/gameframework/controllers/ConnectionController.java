package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.models.SettingsModel;
import main.java.org.hanzet23.gameframework.views.MainView;
/**
 * JPanel that holds all buttons and fields involved in creating the connection to the server. 
 * 
 * @author Jan-Bert
 *
 */
public class ConnectionController extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public String selectedName = null;

	private JLabel title = new JLabel("Connection");
	private JLabel nameLabel = new JLabel("Name");
	private JTextField name;
	private JToggleButton local;
	private JToggleButton network;
	private JButton connect;

	private boolean isLocal = false;

	/**
	 * Constructor for ConnectionController.  
	 */
	public ConnectionController() {
		// Setups
		this.setupNetwork();
		this.setupLocal();
		this.setupName();
		this.setupConnect();

		// Layout
		this.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.add(title);

		this.add(titlePanel, BorderLayout.NORTH);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		JPanel buttons = new JPanel();
		buttons.add(network);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(Box.createRigidArea(new Dimension(5, 0)));
		buttons.add(local);

		center.add(buttons);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridBagLayout());
		namePanel.add(nameLabel);
		
		center.add(namePanel);
		
		JPanel text = new JPanel();
		text.setLayout(new BoxLayout(text, BoxLayout.X_AXIS));
		text.add(name);
		center.add(text);

		this.add(center, BorderLayout.CENTER);
		this.add(connect, BorderLayout.SOUTH);
	}

	/**
	 * Creates the button for local games and the actions that will be performed when it is clicked.
	 * @return The JButton for local games.
	 */
	private JToggleButton setupLocal() {
		local = new JToggleButton("Local");
		
		local.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				local.setSelected(true);
				network.setSelected(false);
				isLocal = true;
			}
		});

		local.setSelected(false);
		return local;
	}

	/**
	 * Creates the button for network games and the actions that will be performed when it is clicked.
	 * @return The JButton for network games.
	 */
	private JToggleButton setupNetwork() {
		network = new JToggleButton("Network");

		network.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				network.setSelected(true);
				local.setSelected(false);
				isLocal = false;
			}
		});

		network.setSelected(true);
		return network;
	}

	/**
	 *  Creates the textfield for the player's username.
	 * @return The textfield for the player's username.
	 */
	private JTextField setupName() {
		name = new JTextField();
		name.setSize(50, name.getHeight());
		return name;
	}

	/**
	 * Creates the button for connecting via the selected methodand the actions that will be performed when it is clicked.
	 */
	private JButton setupConnect() {
		connect = new JButton("Connect");

		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int serverPort = 7789;
				String serverName = "localhost";
				
				// Connect to the network
				if (isLocal == false) {
					SettingsModel settings = new SettingsModel();
					LinkedHashMap<String, String> map = settings.getSettings();
					serverPort = Integer.parseInt(map.get("network_server_port"));
					serverName = map.get("network_server_ip");
				}
				
				// Connect to the server with the credentials
				NetworkModel network = NetworkModel.setInstance(serverPort, serverName);

				if (network.getSocket() != null) {
					// Activate views
					MainView.mainview.activateGames();
					MainView.mainview.activatePlayers();
					
					// Login with the selected username
					String playerName = name.getText();
					if (playerName.equals("")) {
						int randomNumber = (new Random()).nextInt(9999);
						playerName = "Speler #" + randomNumber;
					}
					selectedName = playerName;
					network.getOutput().login(playerName);
					
					// Get the list of games available on the server
					network.getOutput().getGamelist();
	
					// Get the list of players on the server
					network.getOutput().getPlayerlist();

				}
			}
		});
		return connect;
	}
	
	/**
	 * Gets the name the user entered in the textfield
	 * @return
	 */
	public String getSelectedName() {
		return selectedName;
	}
	
	/**
	 * Returns Either "local" or "network" depending on which button is selected.
	 */
	public String getConnectionType() {
		if (local.isSelected()) {
			return "local";
		} else {
			return "network";
		}
	}
}

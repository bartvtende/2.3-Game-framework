package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.models.SettingsModel;

public class ConnectionController extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel title = new JLabel("Connection");
	private JLabel nameLabel = new JLabel("Name");
	private JTextField name;
	private JToggleButton local;
	private JToggleButton network;
	private JButton connect;

	private boolean isLocal = true;

	public ConnectionController() {
		// Setups
		this.setupLocal();
		this.setupNetwork();
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
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(local);
		buttons.add(Box.createRigidArea(new Dimension(5, 0)));
		buttons.add(network);

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

		local.setSelected(true);
		return local;
	}

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

		network.setSelected(false);
		return network;
	}

	private JTextField setupName() {
		name = new JTextField();
		name.setSize(50, name.getHeight());
		return name;
	}

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
					// Login with the selected username
					String playerName = name.getText();
					if (playerName.equals("")) {
						playerName = "Winnaar";
					}
					network.login(playerName);
					
					// Get the list of games available on the server
					network.getGamelist();
	
					// Get the list of players on the server
					network.getPlayerlist();
				}
			}
		});
		return connect;
	}
}

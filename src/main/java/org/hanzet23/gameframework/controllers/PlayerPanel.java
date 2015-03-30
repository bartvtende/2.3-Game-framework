package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] DEMO_PLAYERS = { "BUB1", "Leroy_Jenkins",
			"Carl_Say_Again" };

	private JLabel title = new JLabel("Players");
	private JButton connect;
	private JList<String> playerList;

	private ArrayList<String> players;

	public PlayerPanel() {
		// Setups
		setupPlayers();
		setupConnect();

		playerList = new JList<String>(DEMO_PLAYERS);

		// Layout
		this.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.add(title);

		this.add(titlePanel, BorderLayout.NORTH);

		this.add(playerList, BorderLayout.CENTER);

		this.add(connect, BorderLayout.SOUTH);

	}

	private void setupPlayers() {
		players = new ArrayList<String>();
		for (String player : DEMO_PLAYERS) {
			players.add(player);
		}
	}

	private void setupConnect() {
		connect = new JButton("Connect");
		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Started a game with "
						+ (playerList.getSelectedValue().toString()));
			}

		});
	}
}

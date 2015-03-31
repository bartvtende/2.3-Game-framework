package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

public class PlayersController extends JPanel {

	private static final long serialVersionUID = 1L;
	private static String[] playersList = {"No players connected"};

	private JLabel title = new JLabel("Players");
	private JButton connect;
	private JButton refresh;
	private JList<String> playerList;

	public PlayersController() {
		// Setups
		setupPlayers();
		setupConnect();
		setupRefresh();

		playerList = new JList<String>(playersList);

		// Layout
		this.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.add(title);

		this.add(titlePanel, BorderLayout.NORTH);

		this.add(playerList, BorderLayout.CENTER);

		JPanel connectPanel = new JPanel();
		connectPanel.setLayout(new BoxLayout(connectPanel, BoxLayout.X_AXIS));
		connectPanel.add(connect);
		connectPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		connectPanel.add(refresh);
		this.add(connectPanel, BorderLayout.SOUTH);

	}

	public void setupPlayers() {
		if (playerList != null) {
			playerList.setListData(playersList);
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

	private void setupRefresh() {
		refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get the list of players on the server
				NetworkModel network = NetworkModel.getInstance();
				network.getPlayerlist();
			}
		});
	}
	
	public void setPlayersList(String[] players) {
		playersList = players;
	}
}

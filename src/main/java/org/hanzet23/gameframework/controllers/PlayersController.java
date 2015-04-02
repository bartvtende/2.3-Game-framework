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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.models.ComputerModel;
import main.java.org.hanzet23.gameframework.models.HumanModel;
import main.java.org.hanzet23.gameframework.models.NetworkModel;
import main.java.org.hanzet23.gameframework.models.PlayerModel;
import main.java.org.hanzet23.gameframework.views.MainView;

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
				// Check if a player is selected
				String playingAgainst = null;
				if (playerList.getSelectedValue() != null) {
					playingAgainst = playerList.getSelectedValue().toString();
				} else {
					JOptionPane.showMessageDialog(MainView.mainview, "You didn't select a player, please try again.", "Select a player!", JOptionPane.ERROR_MESSAGE);
				}
				
				String gamePlaying = null;
				if (MainView.games.getSelectedGame() != null) {
					gamePlaying = MainView.games.getSelectedGame();
				} else {
					JOptionPane.showMessageDialog(MainView.mainview, "You didn't select a game, please try again.", "Select a game!", JOptionPane.ERROR_MESSAGE);
				}
				
				String playerType = null;
				if (MainView.games.getPlayerType() != null) {
					playerType = MainView.games.getPlayerType();
				} else {
					JOptionPane.showMessageDialog(MainView.mainview, "You didn't select a player type, please try again.", "Select a player type!", JOptionPane.ERROR_MESSAGE);
				}

				NetworkModel network = NetworkModel.getInstance();
				network.challenge(playingAgainst, gamePlaying);
				
				String connectionType = MainView.connection.getConnectionType();
				String playingAs = MainView.connection.getSelectedName();
				
				PlayerModel playerModel = null;
				if (playerType.equals("Human")) {
					playerModel = new HumanModel(connectionType, playerType, gamePlaying, playingAs, playingAgainst);
				} else {
					playerModel = new ComputerModel(connectionType, playerType, gamePlaying, playingAs, playingAgainst);
				}
				
				System.out.println(PlayerModel.playerType);
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

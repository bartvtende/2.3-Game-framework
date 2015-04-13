 package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

public class GamesController extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] AVAILABLE_GAMES = { "Tic-Tac-Toe", "Othello",
			"Guess Game" };
	private String[] gamesList = AVAILABLE_GAMES;
	private boolean isHuman = false;

	private ArrayList<JToggleButton> games;
	private JToggleButton human;
	private JToggleButton computer;
	private JLabel title = new JLabel("Games");
	private JLabel playerLabel = new JLabel("Play as");
	private JPanel centerPanel = new JPanel();;

	public GamesController() {
		// Setups
		setupGames();
		setupComputer();
		setupHuman();

		// Layout
		this.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.add(title);

		this.add(titlePanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(centerPanel);

		this.add(scrollPane, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

		JPanel playerLabelPanel = new JPanel();
		playerLabelPanel.setLayout(new GridBagLayout());
		playerLabelPanel.add(playerLabel);

		JPanel playerButtonPanel = new JPanel();
		playerButtonPanel.setLayout(new BoxLayout(playerButtonPanel,
				BoxLayout.X_AXIS));
		playerButtonPanel.add(computer);
		playerButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		playerButtonPanel.add(human);

		southPanel.add(playerLabelPanel);
		southPanel.add(playerButtonPanel);

		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void setupGames() {
		centerPanel.removeAll();
		centerPanel.setLayout(new GridLayout(0, 1));

		games = new ArrayList<JToggleButton>();
		for (String game : gamesList) {
			JToggleButton gameButton = new JToggleButton(game);
			String key = game.toLowerCase();
			if (key.contains("tic") || key.contains("othello")
					|| key.contains("reversi")) {
				gameButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						for (JToggleButton button : games) {
							button.setSelected(false);
						}
						((JToggleButton) arg0.getSource()).setSelected(true);
					}
				});
			} else {
				gameButton.setEnabled(false);
			}
			games.add(gameButton);
		}

		for (JToggleButton game : games) {
			centerPanel.add(game);
		}
	}

	private void setupHuman() {
		human = new JToggleButton("Human");
		human.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				human.setSelected(true);
				computer.setSelected(false);
				isHuman = true;
			}
		});
	}

	private void setupComputer() {
		computer = new JToggleButton("Computer");
		computer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				computer.setSelected(true);
				human.setSelected(false);
				isHuman = false;
			}
		});

		computer.setSelected(true);
	}

	public void setGamesList(String[] list) {
		gamesList = list;
	}

	/**
	 * Returns the selected game
	 * 
	 * @return
	 */
	public String getSelectedGame() {
		for (JToggleButton gameButton : games) {
			if (gameButton.isSelected()) {
				return gameButton.getText();
			}
		}
		return null;
	}
	
	public String getPlayerType() {
		String playerType = null;
		if (isHuman) {
			playerType = "Human";
		} else {
			playerType = "Computer";
		}
		return playerType;
	}
}

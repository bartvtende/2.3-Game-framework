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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import main.java.org.hanzet23.gameframework.views.GamePanel;
import main.java.org.hanzet23.gameframework.views.MainView;

public class GamesController extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] AVAILABLE_GAMES = { "Tic-Tac-Toe", "Othello" };
	private String[] gamesList = AVAILABLE_GAMES;
	private boolean isHuman = true;

	private ArrayList<JButton> games;
	private JToggleButton human;
	private JToggleButton computer;
	private JLabel title = new JLabel("Games");
	private JLabel playerLabel = new JLabel("Play as");
	private JPanel centerPanel = new JPanel();;

	public GamesController() {
		// Setups
		setupGames();
		setupHuman();
		setupComputer();

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
		playerButtonPanel.add(human);
		playerButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		playerButtonPanel.add(computer);

		southPanel.add(playerLabelPanel);
		southPanel.add(playerButtonPanel);

		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void setupGames() {
		centerPanel.removeAll();
		centerPanel.setLayout(new GridLayout(0, 1));

		games = new ArrayList<JButton>();
		for (String game : gamesList) {
			JButton gameButton = new JButton(game);
			String key = game.toLowerCase();
			if (key.contains("tic") || key.contains("othello")
					|| key.contains("reversi")) {
				gameButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Start"
								+ ((JButton) arg0.getSource()).getText());
						MainView.mainview.activatePlayers();
					}
				});
			} else {
				gameButton.setEnabled(false);
			}
			games.add(gameButton);
		}

		for (JButton game : games) {
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

		human.setSelected(true);
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
	}

	public void setGamesList(String[] list) {
		gamesList = list;
	}
}

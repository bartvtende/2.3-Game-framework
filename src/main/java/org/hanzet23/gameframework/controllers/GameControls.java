package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GameControls extends JPanel {

	private static final long serialVersionUID = 1L;

	private JSpinner xSpinner;
	private JSpinner ySpinner;
	private JButton place;

	public GameControls() {
		setupSpinners();
		setupButton();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.add(new JLabel("X"), BorderLayout.WEST);
		panel.add(xSpinner, BorderLayout.EAST);

		this.add(panel);

		panel = new JPanel();
		panel.add(new JLabel("Y"), BorderLayout.WEST);
		panel.add(ySpinner, BorderLayout.EAST);

		this.add(panel);

		this.add(place);
	}

	private void setupSpinners() {
		// int l = get board.length
		// SpinnerNumberModel model = new SpinnerNumberModel(1, 1, l+1, 1);
		// xSpinner = new JSpinner(model);
		// DEMO NUMBERS
		int DEMOX = 7;
		SpinnerNumberModel modelX = new SpinnerNumberModel(1, 1, DEMOX + 1, 1);
		xSpinner = new JSpinner(modelX);

		// int l = get board[0].length
		// SpinnerNumberModel model = new SpinnerNumberModel(4, 1, l+1, 1);
		// xSpinner = new JSpinner(model);

		int DEMOY = 7;
		SpinnerNumberModel modelY = new SpinnerNumberModel(1, 1, DEMOY + 1, 1);
		ySpinner = new JSpinner(modelY);

	}

	private void setupButton() {
		place = new JButton("Place");

		place.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// make some stone placing method

				System.out.println(((Integer) xSpinner.getValue()).toString()
						+ " : " + ((Integer) ySpinner.getValue()).toString());
			}

		});
	}

}

package main.java.org.hanzet23.gameframework.views;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

/**
 * Frame in which the DDOS cheat can be fine tuned.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class HelpView extends JFrame {

	public static int number = 0;

	private static final long serialVersionUID = 1L;

	private JTextField field = new JTextField();
	private JButton click = new JButton("Get Rekt!");
	private Container cp;

	/**
	 * Constructor for HelpView.
	 */
	public HelpView() {
		setupClick();
		cp = this.getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.add(field);
		cp.add(click);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * Setup for the button and it's corresponding actions.
	 */
	private void setupClick() {
		click.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int value = new Integer(field.getText()).intValue();
				number = value;
				for (int i = 0; i < value; i++) {
					NetworkModel.getInstance().getOutput().getGamelist();
				}
			}

		});
	}

}

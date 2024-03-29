package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

/**
 * A tile for the Othello board.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class Tile extends JButton {

	private static final long serialVersionUID = 1L;

	private JPanel content;
	private final int tileSize = 50;
	private final static Color COLOR_1 = Color.BLACK;
	private final static Color COLOR_2 = Color.WHITE;
	private String state = "E";
	private int XPosition;
	private int YPosition;

	/**
	 * Creates a tile.
	 * 
	 * @param x
	 *            The X position of the tile on the board.
	 * @param y
	 *            The Y position of the tile on the board.
	 */
	public Tile(int x, int y) {
		XPosition = x;
		YPosition = y;

		JPanel empty = new JPanel();
		empty.setSize(tileSize, tileSize);
		this.content = empty;

		this.setContent("X");
		
		// Border
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Gets the position of the clicked tile
				String position = Integer.toString(XPosition * 8 + YPosition);

				// Send the move and it to the board
				NetworkModel.getInstance().getOutput().move(position);
				NetworkModel.getInstance();
				OthelloMove move = new OthelloMove(XPosition * 8, YPosition);
				NetworkModel.board.game.placeMove('O', move.getPosition(),
						false);

				System.out.println("Tile got clicked");
				setContent("X");
			}
		});
	}

	/**
	 * Sets the contents of the tile based on what String is passed on.
	 * 
	 * @param command
	 *            "E", "X" or "O".
	 */
	void setContent(String command) {
		this.setBackground(new Color(0, 100, 0));
		if (command.equals("E")) {
			this.remove(content);
			JPanel empty = new JPanel();
			empty.setBackground(this.getBackground());
			empty.setSize(tileSize, tileSize);
			content = empty;
			this.add(content);
			this.revalidate();
			this.setState(command);
		} else if (command.equals("X")) {
			this.remove(content);
			JPanel p1 = new CircleShape(tileSize, tileSize, COLOR_1,
					this.getBackground());
			content = p1;
			this.add(content);
			this.revalidate();
			this.setState(command);
		} else if (command.equals("O")) {
			this.remove(content);
			JPanel p2 = new CircleShape(tileSize, tileSize, COLOR_2,
					this.getBackground());
			content = p2;
			this.add(content);
			this.revalidate();
			this.setState(command);
		} else {
			System.out.println("Error: Wrong command in Tile.");
		}
	}

	/**
	 * Sets the current state of the tile based on what string is passed on.
	 * 
	 * @param state
	 *            "E", "X" or "O".
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the current state of the tile.
	 * 
	 * @return
	 */
	public String getState() {
		return state;
	}
}

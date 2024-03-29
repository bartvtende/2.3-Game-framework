package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

/**
 * A tile for the Tic-Tac-Toe board.
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

		this.setContent("Empty");
		
		// Border
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Gets the position of the clicked tile
				String position = Integer.toString(XPosition * 3 + YPosition);

				// Send the move and it to the board
				NetworkModel.getInstance().getOutput().move(position);
				NetworkModel.board.game.addItemToBoard(position, 'X');

				System.out.println("Tile got clicked");
				setContent("X");
			}
		});
	}

	/**
	 * Sets the contents of the tile based on what String is passed on.
	 * 
	 * @param command
	 *            "Empty", "X" or "O".
	 */
	void setContent(String command) {
		if (command.equals("Empty")) {
			this.remove(content);
			JPanel empty = new JPanel();
			empty.setSize(tileSize, tileSize);
			content = empty;
			this.add(content);
			this.revalidate();
		} else if (command.equals("X")) {
			this.remove(content);
			JPanel x = new CrossShape(tileSize, tileSize);
			content = x;
			this.add(content);
			this.revalidate();
		} else if (command.equals("O")) {
			this.remove(content);
			JPanel o = new CircleShape(tileSize, tileSize, Color.red,
					this.getBackground());
			content = o;
			this.add(content);
			this.revalidate();
		}
	}

	/**
	 * Returns the XPosition of the tile.
	 * 
	 * @return
	 */
	public int getXPosition() {
		return XPosition;
	}

	/**
	 * Sets the XPosition of the tile.
	 * 
	 * @param xPosition
	 */
	public void setXPosition(int xPosition) {
		XPosition = xPosition;
	}

	/**
	 * Returns the YPosition of the tile.
	 * 
	 * @return
	 */
	public int getYPosition() {
		return YPosition;
	}

	/**
	 * Sets the YPosition of the tile.
	 * 
	 * @param yPosition
	 */
	public void setYPosition(int yPosition) {
		YPosition = yPosition;
	}
}

package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.games.othello.Tile;

/**
 * JPanel with the Othello board.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class OthelloView extends JPanel {

	private static final long serialVersionUID = 1L;

	private Tile[][] tiles = new Tile[8][8];

	/**
	 * Constructor for OthelloView.
	 */
	public OthelloView() {
		this.setLayout(new GridLayout(tiles.length, tiles[0].length));
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				Tile tile = new Tile(i, j);
				tile.setBackground(this.getBackground());
				tiles[i][j] = tile;
				this.add(tile);
			}
		}

		this.setPreferredSize(new Dimension(400, 400));
	}

	/**
	 * Returns the Tile at the given x and y coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * Makes the GUI board based on the matrix that is passed as a parameter The
	 * value matrix must be filled with the values 'X', 'x', 'O', 'o', 'E' or
	 * 'e'.
	 * 
	 * @param board
	 */
	public void refresh(char[][] board) {
		this.removeAll();

		this.setLayout(new GridLayout(8, 8));

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char boardChar = board[i][j];
				if (boardChar == 'X' || boardChar == 'x') {
					Tile tile = new Tile(i, j);
					tile.setContent("X");

					tiles[i][j] = tile;
					this.add(tile);

				} else if (boardChar == 'O' || boardChar == 'o') {
					Tile tile = new Tile(i, j);
					tile.setContent("O");

					tiles[i][j] = tile;
					this.add(tile);
				} else if (boardChar == 'E' || boardChar == 'e') {
					Tile tile = new Tile(i, j);
					tile.setContent("E");

					tiles[i][j] = tile;
					this.add(tile);
				}
			}
		}

		this.revalidate();
	}

}

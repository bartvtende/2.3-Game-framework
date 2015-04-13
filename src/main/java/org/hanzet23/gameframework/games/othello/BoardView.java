package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import main.java.org.hanzet23.gameframework.games.othello.Tile;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Tile[][] tiles = new Tile[8][8];

	public BoardView() {
		this.setLayout(new GridLayout(tiles.length, tiles[0].length));
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				Tile tile = new Tile();
				tile.setBackground(this.getBackground());
				tiles[i][j] = tile;
				this.add(tile);
			}

		}

		this.setPreferredSize(new Dimension(400, 400));
	}

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
				System.out.println(boardChar);
				if (boardChar == 'X' || boardChar == 'x') {
					Tile tile = new Tile();
					tile.setContent("X");

					tiles[i][j] = tile;
					this.add(tile);

				} else if (boardChar == 'O' || boardChar == 'o') {
					Tile tile = new Tile();
					tile.setContent("O");

					tiles[i][j] = tile;
					this.add(tile);
				} else if (boardChar == 'E' || boardChar == 'e') {
					Tile tile = new Tile();
					tile.setContent("Empty");

					tiles[i][j] = tile;
					this.add(tile);
				}
			}
		}

		this.revalidate();
	}

}

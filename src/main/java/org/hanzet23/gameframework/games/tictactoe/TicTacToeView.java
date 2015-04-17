package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class TicTacToeView extends JPanel {

	private static final long serialVersionUID = 1L;

	private Tile[][] tiles = new Tile[3][3];

	public TicTacToeView() {
		this.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Tile tile = new Tile(i,j);
				tiles[i][j] = tile;
				this.add(tile);
			}
		}

		this.setPreferredSize(new Dimension(150, 150));
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

		this.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char boardChar = board[i][j];
				if (boardChar == 'X' || boardChar == 'x') {
					Tile tile = new Tile(i,j);
					tile.setContent("X");

					tiles[i][j] = tile;
					this.add(tile);
				} else if (boardChar == 'O' || boardChar == 'o') {
					Tile tile = new Tile(i,j);
					tile.setContent("O");

					tiles[i][j] = tile;
					this.add(tile);
				} else if (boardChar == 'E' || boardChar == 'e') {
					Tile tile = new Tile(i,j);
					tile.setContent("Empty");

					tiles[i][j] = tile;
					this.add(tile);
				}
			}
		}

		this.revalidate();
	}
}

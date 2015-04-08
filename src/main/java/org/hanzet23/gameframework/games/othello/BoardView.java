package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardView extends JPanel {

	private Tile[][] tiles = new Tile[8][8];
	
	public BoardView(){
		this.setLayout(new GridLayout(tiles.length,tiles[0].length));
		for(int i = 0; i<tiles.length;i++){
			for(int j = 0; j<tiles[0].length;j++){
				Tile tile = new Tile();
				tile.setBackground(this.getBackground());
				tiles[i][j] = tile;
				this.add(tile);
			}
			
		}
		
		this.setPreferredSize(new Dimension(400, 400));
	}
	
	
}

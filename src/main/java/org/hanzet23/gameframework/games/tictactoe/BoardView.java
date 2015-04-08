package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardView extends JPanel {

	private Tile[][] tiles = new Tile[3][3];
	
	public BoardView(){
		this.setLayout(new GridLayout(3,3));
		for(int i = 0; i<3;i++){
			for(int j = 0; j<3;j++){
				Tile tile = new Tile();
				tiles[i][j] = tile;
				this.add(tile);
			}
			
		}
		
		this.setPreferredSize(new Dimension(150, 150));
	}
	
	
}

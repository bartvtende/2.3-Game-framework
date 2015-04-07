package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Board extends JPanel {

	public Board(){
		this.setLayout(new GridLayout(3,3));
		for(int i = 0; i<9;i++){
			this.add(new Tile());
			this.setPreferredSize(new Dimension(150, 150));
		}
	}
	
	
}

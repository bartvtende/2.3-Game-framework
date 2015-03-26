package main.java.org.hanzet23.gameframework.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuBar(){
	//maak menu 1
    JMenu file = new JMenu("File");
    
    //Menu items
    JMenuItem newConnection = new JMenuItem("New connection");
    JMenuItem newGame = new JMenuItem("New game");
    
    newConnection.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFrame popup = new NewConnection();
			popup.setVisible(true);
			popup.pack();
		}
    	
    });
    
    newGame.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame popup = new NewGame();
			popup.setVisible(true);
			popup.pack();
		}
    	
    });
    
    file.add(newConnection);
    file.add(newGame);
    
    this.add(file);
    
	}
}

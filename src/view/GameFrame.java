package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.GameControls;
import controller.NewConnection;
import controller.NewGame;

public class GameFrame extends JFrame {
	
	private GamePanel gamePanel = new GamePanel();
	
	
	public GameFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setJMenuBar(createMenu());
		this.add(gamePanel, BorderLayout.CENTER);
		this.add(new GameControls(), BorderLayout.EAST);
	}
	private JMenuBar createMenu(){
		//maak JMenuBar
        JMenuBar menu = new JMenuBar();
        
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
        
        menu.add(file);
        
        return menu;
	}

}

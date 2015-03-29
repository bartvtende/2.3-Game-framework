package main.java.org.hanzet23.gameframework.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuBar() {
		// Menu 1
		JMenu options = new JMenu("Options");
		
		// Menu 2
		JMenu about = new JMenu("About");

		// Menu 1 items
		JMenuItem settings = new JMenuItem("Settings");
		JMenuItem exit = new JMenuItem("Exit");
		
		// Menu 2 items
		JMenuItem help = new JMenuItem("Help");
		
		/**
		 * ActionListener for the menu item "Settings"
		 */
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame popup = new SettingsController();
				popup.setVisible(true);
				popup.pack();
				
			}
		});

		/**
		 * ActionListener for the menu item "Exit"
		 */
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		/**
		 * ActionListener for the menu item "Help"
		 */
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//TODO HELP FRAME
			}

		});

		options.add(settings);
		options.add(exit);
		
		about.add(help);

		this.add(options);
		this.add(about);

	}
}

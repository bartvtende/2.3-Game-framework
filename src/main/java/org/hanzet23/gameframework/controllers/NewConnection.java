package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.util.StringUtils;

import main.java.org.hanzet23.gameframework.models.Command;

public class NewConnection extends JFrame {

	private JTextField IP = new JTextField();
	private JTextField port = new JTextField();
	private JButton connect;
	private JFrame frame;
	
	public NewConnection(){
		frame = this;
		setupButton();
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		
		JPanel ipPanel = new JPanel();
		ipPanel.setLayout(new BoxLayout(ipPanel, BoxLayout.Y_AXIS));
		ipPanel.add(new JLabel("IP address"));
		ipPanel.add(IP);
		
		JPanel portPanel = new JPanel();
		portPanel.setLayout(new BoxLayout(portPanel, BoxLayout.Y_AXIS));
		portPanel.add(new JLabel("port"));
		portPanel.add(port);
		
		center.add(ipPanel);
		center.add(portPanel);
		
		this.add(center, BorderLayout.CENTER);
		
		this.add(connect, BorderLayout.SOUTH);
	}
	
	private void setupButton(){
		connect = new JButton("Connect");
		connect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//DEMO
				frame.dispose();

				// Check if port is an integer, otherwise use port 7789
				int serverPort = 0;
				try {
					serverPort = Integer.parseInt(port.getText());
				} catch (NumberFormatException e) {
					System.out.println("Port is not a valid number, continuing with the default port.");
				}
				
				// Check the server IP
				String serverName = null;
				if (IP.getText().equals("")) {
					serverName = null;
				} else {
					serverName = IP.getText();
				}
				
				// Open a network connection
				Command command = new Command(serverPort, serverName);
				
				command.login("Bartje1");
				command.subscribe("Guess Game");
				
				command.getGamelist();
			}
			
		});
	}
}

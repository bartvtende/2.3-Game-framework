package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class ConnectionPanel extends JPanel {

	private JLabel title = new JLabel("Connection");
	private JTextField name;
	private JToggleButton local;
	private JToggleButton network;
	private boolean isLocal = true;
	private JButton connect;
	
	public ConnectionPanel(){
		
		//setups
		this.setupLocal();
		this.setupNetwork();
		this.setupName();
		this.setupConnect();
		
		//layout
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.setBackground(Color.pink);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBackground(Color.CYAN);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(local);
		buttons.add(Box.createRigidArea(new Dimension(5,0)));
		buttons.add(network);
		buttons.setBackground(Color.GREEN);
		
		center.add(buttons);
		
		JPanel text = new JPanel();
		text.add(name);
		text.setBackground(Color.magenta);
		center.add(text);
		
		this.add(center, BorderLayout.CENTER);
		this.add(connect, BorderLayout.SOUTH);
		
	}
	
	private JToggleButton setupLocal(){
		local = new JToggleButton("Local");
		local.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				local.setSelected(true);
				network.setSelected(false);
				isLocal = true;
			}
			
		});
		local.setSelected(true);
		return local;
	}
	
	private JToggleButton setupNetwork(){
		network = new JToggleButton("Network");
		network.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				network.setSelected(true);
				local.setSelected(false);
				isLocal = false;
			}
			
		});
		
		network.setSelected(false);
		return network;
	}
	
	private JTextField setupName(){
		name= new JTextField();
		name.setSize(50, name.getHeight());
		return name;
	}
	
	private JButton setupConnect(){
		connect = new JButton("Connect");
		
		connect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String over;
				if(isLocal == true){
					over = "local";
				}else{
					over = "network";
				}
				System.out.println("Tries to connect as "+name.getText()+" over "+over);
			}
			
		});
		return connect;
	}
}

package main.java.org.hanzet23.gameframework.controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox playerType;
	private JComboBox playerNumber;
	private JButton create;
	private JButton join;
	private JFrame frame;
	
	public NewGame(){
		frame = this;
		setupType();
		setupNumber();
		setupCreate();
		setupJoin();
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));;
		typePanel.add(new JLabel("Human or AI"));
		typePanel.add(playerType);
		
		center.add(typePanel);
		
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new BoxLayout(numberPanel, BoxLayout.Y_AXIS));
		numberPanel.add(new JLabel("Play as"));
		numberPanel.add(playerNumber);
		
		center.add(numberPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(create);
		buttonPanel.add(join);
		
		this.add(center, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	private void setupType(){
		String[] types = {"Human", "AI"};
		playerType = new JComboBox(types);
	}
	
	private void setupNumber(){
		String[] numbers = {"Player 1", "Player 2"};
		playerNumber = new JComboBox(numbers);
	}
	
	private void setupCreate(){
		create = new JButton("Create game");
		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//DEMO
				frame.dispose();
				System.out.println("Create as "+(String)playerType.getSelectedItem()+" play as "+(String)playerNumber.getSelectedItem());
			}
			
		});
	}
	
	private void setupJoin(){
		join = new JButton("Join game");
		join.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//DEMO
				frame.dispose();
				System.out.println("Create as "+(String)playerType.getSelectedItem()+" play as "+(String)playerNumber.getSelectedItem());
			}
			
		});
	}
}

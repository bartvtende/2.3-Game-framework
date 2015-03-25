package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
				System.out.println(IP.getText() +":"+ port.getText());
				
			}
			
		});
	}
}

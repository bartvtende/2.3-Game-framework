package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Tile extends JButton{

	private JPanel content;
	private final int tileSize = 50;
	private final static Color  COLOR_1 = Color.BLACK;
	private final static Color  COLOR_2 = Color.WHITE;
	
	
	public Tile(){
		JPanel empty = new JPanel();
		empty.setSize(tileSize, tileSize);
		this.content = empty;
		this.setBackground(Color.GREEN);
		
		this.setContent("Player1");
		//border

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(
				raisedbevel, loweredbevel);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Something with get player or
				System.out.println("Tile got clicked");
				setContent("Player2");
			}
		});
	}
	
	
	
	private void setContent(String command){
		this.setBackground(Color.GREEN);
		if(command.equals("Empty")){
			this.remove(content);
			JPanel empty = new JPanel();
			empty.setSize(tileSize, tileSize);
			content = empty;
			this.add(content);
			this.revalidate();
		}
		else if(command.equals("Player1")){
			this.remove(content);
			JPanel p1 = new CircleShape(tileSize, tileSize, COLOR_1, this.getBackground());
			content = p1;
			this.add(content);
			this.revalidate();
		}
		else if(command.equals("Player2")){
			this.remove(content);
			JPanel p2 = new CircleShape(tileSize, tileSize, COLOR_2, this.getBackground());
			content = p2;
			this.add(content);
			this.revalidate();
		}
		else{
			System.out.println("Error: Wrong command, shutting down.");
			System.exit(0);
		}
	}
}

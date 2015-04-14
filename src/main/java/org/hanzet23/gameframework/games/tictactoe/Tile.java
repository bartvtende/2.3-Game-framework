package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

public class Tile extends JButton {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private final int tileSize = 50;
	private int XPosition;
	private int YPosition;
	
	public Tile(int x, int y){
		XPosition = x;
		YPosition = y;
		
		JPanel empty = new JPanel();
		empty.setSize(tileSize, tileSize);
		this.content = empty;

        this.setContent("Empty");
		// border

		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Something with get player or
				String position = Integer.toString(XPosition * 3 + YPosition);
				NetworkModel.getInstance().getOutput().move(position);
				NetworkModel.getInstance().board.game.addItemToBoard(position, 'X');
				
				System.out.println("Tile got clicked");
				setContent("X");
			}
		});
	}

	public Tile() {
		System.out.println("Bad reference");
	}

	void setContent(String command) {
		if (command.equals("Empty")) {
			this.remove(content);
			JPanel empty = new JPanel();
			empty.setSize(tileSize, tileSize);
			content = empty;
			this.add(content);
			this.revalidate();
		} else if (command.equals("X")) {
			this.remove(content);
			JPanel x = new CrossShape(tileSize, tileSize);
			content = x;
			this.add(content);
			this.revalidate();
		} else if (command.equals("O")) {
			this.remove(content);
			JPanel o = new CircleShape(tileSize, tileSize, Color.red,
					this.getBackground());
			content = o;
			this.add(content);
			this.revalidate();
		}
	}

	public int getXPosition() {
		return XPosition;
	}

	public void setXPosition(int xPosition) {
		XPosition = xPosition;
	}

	public int getYPosition() {
		return YPosition;
	}

	public void setYPosition(int yPosition) {
		YPosition = yPosition;
	}
}

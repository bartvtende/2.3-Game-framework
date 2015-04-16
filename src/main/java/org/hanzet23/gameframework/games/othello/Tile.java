package main.java.org.hanzet23.gameframework.games.othello;

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
	private final static Color COLOR_1 = Color.BLACK;
	private final static Color COLOR_2 = Color.WHITE;
	private String state = "E";
	private int XPosition;
	private int YPosition;

	public Tile(int x, int y){
		XPosition = x;
		YPosition = y;
		
		JPanel empty = new JPanel();
		empty.setSize(tileSize, tileSize);
		this.content = empty;

		this.setContent("X");
		// border

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		BorderFactory.createCompoundBorder(raisedbevel,
				loweredbevel);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Something with get player or
				String position = Integer.toString(XPosition * 8 + YPosition);
				NetworkModel.getInstance().getOutput().move(position);
				NetworkModel.getInstance().board.game.addItemToBoard(position, 'X');
				
				System.out.println("Tile got clicked");
				setContent("X");
			}
		});
	}

	void setContent(String command) {
		this.setBackground(new Color(0, 100, 0));
		if (command.equals("E")) {
			this.remove(content);
			JPanel empty = new JPanel();
			empty.setBackground(this.getBackground());
			empty.setSize(tileSize, tileSize);
			content = empty;
			this.add(content);
			this.revalidate();
			this.setState(command);
		} else if (command.equals("X")) {
			this.remove(content);
			JPanel p1 = new CircleShape(tileSize, tileSize, COLOR_1,
					this.getBackground());
			content = p1;
			this.add(content);
			this.revalidate();
			this.setState(command);
		} else if (command.equals("O")) {
			this.remove(content);
			JPanel p2 = new CircleShape(tileSize, tileSize, COLOR_2,
					this.getBackground());
			content = p2;
			this.add(content);
			this.revalidate();
			this.setState(command);
		} else {
			System.out.println("Error: Wrong command in Tile.");
		}
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

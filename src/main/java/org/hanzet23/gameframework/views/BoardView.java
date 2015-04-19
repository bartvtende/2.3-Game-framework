package main.java.org.hanzet23.gameframework.views;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.java.org.hanzet23.gameframework.models.BoardModel;

/**
 * Constructs the board view for a game
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class BoardView extends Canvas implements MouseListener {

	private static final long serialVersionUID = 1L;

	// Cells height
	private int height;
	// Cells width
	private int width;

	private final static int CELL_SIZE = 10;

	// the dimension of the board
	private Dimension size;

	/**
	 * The board on which the game will be displayed
	 * 
	 * @param width
	 *            Amount of cells wide
	 * @param height
	 *            Amount of cells high
	 * 
	 */
	public BoardView(int width, int height) {
		super();
		setHeight(height);
		setWidth(width);

		// Create size thing
		size = new Dimension(width * CELL_SIZE, height * CELL_SIZE);

		// all the sizes
		this.setPreferredSize(size);
		this.setSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);

		for (int i = 0; i < width - 1; i++) {
			System.out.println(this.getGraphics());

			this.getGraphics().drawLine((i + 1) * CELL_SIZE, 0,
					(i + 1) * CELL_SIZE, this.getSize().height);
		}

		for (int i = 0; i < height - 1; i++) {
			this.getGraphics().drawLine(0, (i + 1) * CELL_SIZE,
					this.getSize().width, (i + 1) * CELL_SIZE);
		}

	}

	public BoardView(BoardModel board) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int X = arg0.getX();
		int Y = arg0.getY();

		System.out.println(X + ", " + Y);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void setHeight(int size) {
		this.height = size;
	}

	public void setWidth(int size) {
		this.width = size;
	}

}
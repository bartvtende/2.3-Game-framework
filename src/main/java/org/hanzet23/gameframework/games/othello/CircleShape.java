package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * JPanel with a circle.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class CircleShape extends JPanel {

	private static final long serialVersionUID = 1L;

	private double[] pointsX = { 0.1, 0.9 };
	private double[] pointsY = { 0.1, 0.9 };
	private Color circle;

	/**
	 * Constructor for CircleShape
	 * 
	 * @param x
	 *            The width of the panel.
	 * @param y
	 *            The height of the panel.
	 * @param circle
	 *            The color of the circle.
	 * @param backGround
	 *            The color of the background of the panel.
	 */
	public CircleShape(int x, int y, Color circle, Color backGround) {
		updatePoints(x, y);
		this.setBackground(backGround);
		this.circle = circle;

		this.setPreferredSize(new Dimension(x, y));
	}

	/**
	 * Draws the circle.
	 * 
	 * @param g
	 *            Graphics used for drawing the circle.
	 */
	private void doDrawing(Graphics g) {
		g.setColor(circle);

		g.fillOval(new Double(pointsX[0]).intValue(),
				new Double(pointsY[0]).intValue(), new Double(pointsX[1]
						- pointsX[0]).intValue(), new Double(pointsY[1]
						- pointsY[0]).intValue());
	}

	/**
	 * Updates the points which are used for drawing the circle based of the
	 * size of the panel.
	 * 
	 * @param x
	 *            The width of the panel.
	 * @param y
	 *            The height of the panel.
	 */
	private void updatePoints(int x, int y) {
		for (int i = 0; i < pointsX.length; i++) {
			pointsX[i] = pointsX[i] * x;
		}

		for (int i = 0; i < pointsY.length; i++) {
			pointsY[i] = pointsY[i] * y;
		}
	}

	/**
	 * The overridden method that also paints the circle.
	 * 
	 * @param g
	 *            The graphics used for painting the component.
	 */
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}
}

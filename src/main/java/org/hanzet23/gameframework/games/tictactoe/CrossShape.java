package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

/**
 * JPanel with a cross.
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
public class CrossShape extends JPanel {

	private static final long serialVersionUID = 1L;

	private double points[][] = { { 0.2, 0.1 }, { 0.5, 0.4 }, { 0.8, 0.1 },
			{ 0.9, 0.2 }, { 0.6, 0.5 }, { 0.9, 0.8 }, { 0.8, 0.9 },
			{ 0.5, 0.6 }, { 0.2, 0.9 }, { 0.1, 0.8 }, { 0.4, 0.5 },
			{ 0.1, 0.2 }, { 0.2, 0.1 } };

	private Color color;

	/**
	 * Constructor for CrossShape.
	 * 
	 * @param width
	 *            The width of the JPanel.
	 * @param height
	 *            The height of the JPanel.
	 */
	public CrossShape(double width, double height) {
		this(width, height, Color.BLACK);
	}

	/**
	 * Constructor for CrossShape
	 * 
	 * @param width
	 *            The width of the JPanel.
	 * @param height
	 *            The height of the JPanel.
	 * @param color
	 *            The color of the cross.
	 */
	public CrossShape(double width, double height, Color color) {
		this.color = color;
		updatePoints(width, height);
		this.setPreferredSize(new Dimension(new Double(width).intValue(),
				new Double(height).intValue()));

	}

	/**
	 * Updates the points which are used for drawing the cross based of the size
	 * of the panel.
	 * 
	 * @param x
	 *            The width of the panel.
	 * @param y
	 *            The height of the panel.
	 */
	private void updatePoints(double width, double height) {
		for (int i = 0; i < points.length; i++) {
			points[i][0] = points[i][0] * width;
			points[i][1] = points[i][1] * height;
		}
	}

	/**
	 * Draws the cross.
	 * 
	 * @param g
	 *            Graphics used for drawing the cross.
	 */
	// source: http://zetcode.com/gfx/java2d/shapesandfills/
	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		// g2d.translate(25, 5);

		GeneralPath cross = new GeneralPath();

		cross.moveTo(points[0][0], points[0][1]);

		for (int k = 1; k < points.length; k++)
			cross.lineTo(points[k][0], points[k][1]);

		cross.closePath();
		g2d.setColor(color);
		g2d.fill(cross);
	}

	/**
	 * The overridden method that also paints the cross.
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

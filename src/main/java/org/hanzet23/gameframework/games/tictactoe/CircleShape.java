package main.java.org.hanzet23.gameframework.games.tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CircleShape extends JPanel {

	private static final long serialVersionUID = 1L;

	private double[] PointsX = { 0.1, 0.9, 0.23, 0.77 };
	private double[] PointsY = { 0.1, 0.9, 0.23, 0.77 };
	private Color background;
	private Color circle;

	public CircleShape(int x, int y, Color circle, Color background) {
		updatePoints(x, y);
		this.background = background;
		this.circle = circle;

		this.setPreferredSize(new Dimension(x, y));
	}

	private void doDrawing(Graphics g) {
		g.setColor(background);
		this.setBackground(background);
		g.setColor(circle);

		g.fillOval(new Double(PointsX[0]).intValue(),
				new Double(PointsY[0]).intValue(), new Double(PointsX[1]
						- PointsX[0]).intValue(), new Double(PointsY[1]
						- PointsY[0]).intValue());
		g.setColor(background);
		g.fillOval(new Double(PointsX[2]).intValue(),
				new Double(PointsY[2]).intValue(), new Double(PointsX[3]
						- PointsX[2]).intValue(), new Double(PointsY[3]
						- PointsY[2]).intValue());
	}

	private void updatePoints(int x, int y) {
		for (int i = 0; i < PointsX.length; i++) {
			PointsX[i] = PointsX[i] * x;
		}

		for (int i = 0; i < PointsY.length; i++) {
			PointsY[i] = PointsY[i] * y;
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}
}

package main.java.org.hanzet23.gameframework.games.othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CircleShape extends JPanel {

	private double[] pointsX = {0.1, 0.9};
	private double[] pointsY = {0.1, 0.9};
	private Color circle;
	
	public CircleShape(int x, int y, Color circle, Color backGround){
		updatePoints(x,y);
		this.setBackground(backGround);
		this.circle = circle;
		
		this.setPreferredSize(new Dimension(x,y));
	}
	
	private void doDrawing(Graphics g){
		g.setColor(circle);
		
		g.fillOval(new Double(pointsX[0]).intValue(), new Double(pointsY[0]).intValue() , new Double(pointsX[1]-pointsX[0]).intValue(), new Double(pointsY[1]-pointsY[0]).intValue());
	}
	
	private void updatePoints(int x, int y){
		for(int i = 0; i<pointsX.length;i++){
			pointsX[i] = pointsX[i] * x;
		}
		
		for(int i = 0; i<pointsY.length;i++){
			pointsY[i] = pointsY[i] * y;
		}
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}
}

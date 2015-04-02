package main.java.org.hanzet23.gameframework.models;

public class BoardModel {
	
	public static String[][] gameGrid = null;
	
	public BoardModel(int number) {
		gameGrid = new String[number][number];
	}

}
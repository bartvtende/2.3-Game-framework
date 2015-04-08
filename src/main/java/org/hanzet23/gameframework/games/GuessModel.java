package main.java.org.hanzet23.gameframework.games;

import java.util.Random;
import java.util.Scanner;

import main.java.org.hanzet23.gameframework.models.NetworkModel;

public class GuessModel extends GameModel {

	private final int BOARD_RANGE = 3; 
	
	public GuessModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
	}

	@Override
	public void moveComputer() {
		// Wait 2 seconds
		try {
		    Thread.sleep(2000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		// Select a random number and send it
		String randomNumber = Integer.toString(new Random().nextInt(10) + 1);
		
		NetworkModel.getInstance().getOutput().move(randomNumber);
	}

	@Override
	public void moveHuman() {
		// Read the command line and send that number
		Scanner in = new Scanner(System.in);

		int i = in.nextInt();
		String s = in.next();
		
		NetworkModel.getInstance().getOutput().move(s);
	}
	
}

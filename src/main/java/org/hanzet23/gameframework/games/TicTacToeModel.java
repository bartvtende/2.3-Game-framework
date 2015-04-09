package main.java.org.hanzet23.gameframework.games;

public class TicTacToeModel extends GameModel {
	
	private final int BOARD_RANGE = 3;

	public TicTacToeModel(String gameName) {
		super(gameName);
		this.board = new char[BOARD_RANGE][BOARD_RANGE];
	}

	@Override
	public void moveHuman() {
		// Luister naar de actionlisteners
	}

	@Override
	public void moveComputer() {
		// Gebruik minimax AI
	}

	@Override
	public void startGame() {
		// Maak view
	}

	@Override
	public void stopGame() {
		// Remove the view
	}
	
}

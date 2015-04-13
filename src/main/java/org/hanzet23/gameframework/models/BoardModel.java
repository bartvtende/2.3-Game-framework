package main.java.org.hanzet23.gameframework.models;

public class BoardModel {
	
	public final int GAME_STOPPED = -1;
	public final int GAME_INITIALIZED = 0;
	public final int GAME_STARTED = 1;
	
	public PlayerModel player = null;
	public GameModel game = null;
	
	private int state = GAME_STOPPED;
	
	public BoardModel(PlayerModel player, GameModel game) {
		this.player = player;
		this.game = game;
		state = GAME_INITIALIZED;
	}
	
	public void startGame() {
		state = GAME_STARTED;
		game.startGame();
	}
	
	public void stopGame() {
		state = GAME_STOPPED;
		game.stopGame();
	}

	public void move() {
		if (player.getPlayerType().equalsIgnoreCase("Human")) {
			game.moveHuman();
		} else if (player.getPlayerType().equalsIgnoreCase("Computer")) {
			game.moveComputer();
		} else {
			System.out.println("Something went wrong when making a move, try again");
		}
	}

}

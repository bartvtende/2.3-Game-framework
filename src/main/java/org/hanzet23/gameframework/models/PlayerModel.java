package main.java.org.hanzet23.gameframework.models;

public abstract class PlayerModel {

	// Either local or network
	public static String connectionType = "";
	// Either human or computer
	public static String playerType = "";
	// Chosen game
	public static String gamePlaying = "";
	// You
	public static String playingAs = "";
	// Your opponent
	public static String playingAgainst = "";

	public PlayerModel(String connectionType, String playerType,
			String gamePlaying, String playingAs, String playingAgainst) {
		PlayerModel.connectionType = connectionType;
		PlayerModel.playerType = playerType;
		PlayerModel.gamePlaying = gamePlaying;
		PlayerModel.playingAs = playingAs;
		PlayerModel.playingAgainst = playingAgainst;
	}

}

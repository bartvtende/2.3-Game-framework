package main.java.org.hanzet23.gameframework.models;

public abstract class PlayerModel {

	public static String connectionType = "";
	public static String playerName = "";
	public static String gamePlaying = "";
	public static String playingAs = "";
	public static String playingAgainst = "";

	public PlayerModel(String connectionType, String playerName,
			String gamePlaying, String playingAs, String playingAgainst) {
		this.connectionType = connectionType;
		this.playerName = playerName;
		this.gamePlaying = gamePlaying;
		this.playingAs = playingAs;
		this.playingAgainst = playingAgainst;
	}

}

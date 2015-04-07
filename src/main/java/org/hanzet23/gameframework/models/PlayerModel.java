package main.java.org.hanzet23.gameframework.models;

public class PlayerModel {

	// Either human or computer
	public String playerType = "";
	// You
	public String playingAs = "";
	// Your opponent
	public String playingAgainst = "";

	public PlayerModel(String playerType, String playingAs,
			String playingAgainst) {
		this.playerType = playerType;
		this.playingAs = playingAs;
		this.playingAgainst = playingAgainst;
	}

	public String getPlayerType() {
		return playerType;
	}

	public String getPlayingAs() {
		return playingAs;
	}

	public String getPlayingAgainst() {
		return playingAgainst;
	}

}

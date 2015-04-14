package main.java.org.hanzet23.gameframework.models;

public class PlayerModel {

	// Either human or computer
	public String playerType = "";
	// You
	public String playingAs = "";
	// Your opponent
	public String playingAgainst = "";
	// First tile to begin
	public char tile = 0;

	public PlayerModel(String playerType, String playingAs,
			String playingAgainst, char tile) {
		this.playerType = playerType;
		this.playingAs = playingAs;
		this.playingAgainst = playingAgainst;
		this.tile = tile;
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
	
	public char getTile() {
		return tile;
	}

}

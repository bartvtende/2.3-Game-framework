package main.java.org.hanzet23.gameframework.models;

public class PlayerModel {

	// Either human or computer
	public String playerType = "";
	// You
	public String playingAs = "";
	// Your opponent
	public String playingAgainst = "";
	// Your tile
	public char tile = 0;
	// Tile of the opponent
	public char tileOpp = 0;

	public PlayerModel(String playerType, String playingAs,
			String playingAgainst, char tile, char tileOpp) {
		this.playerType = playerType;
		this.playingAs = playingAs;
		this.playingAgainst = playingAgainst;
		this.tile = tile;
		this.tileOpp = tileOpp;
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
	
	public char getTileOpp() {
		return tileOpp;
	}

}

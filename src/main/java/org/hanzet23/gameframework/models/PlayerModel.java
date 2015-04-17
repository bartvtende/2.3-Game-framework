package main.java.org.hanzet23.gameframework.models;

/**
 * This class contains all the data from the players playing a game
 * 
 * @author Bart van 't Ende
 * @author Jan-Bert van Slochteren
 * @author Jonathan Berends
 * @author Joz Reijneveld
 *
 */
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

	/**
	 * Construct the new PlayerModel from the received variables
	 * 
	 * @param playerType
	 * @param playingAs
	 * @param playingAgainst
	 * @param tile
	 * @param tileOpp
	 */
	public PlayerModel(String playerType, String playingAs,
			String playingAgainst, char tile, char tileOpp) {
		this.playerType = playerType;
		this.playingAs = playingAs;
		this.playingAgainst = playingAgainst;
		this.tile = tile;
		this.tileOpp = tileOpp;
	}

	/**
	 * Getter for the playerType variable
	 * 
	 * @return
	 */
	public String getPlayerType() {
		return playerType;
	}

	/**
	 * Getter for the playingAs variable
	 * 
	 * @return
	 */
	public String getPlayingAs() {
		return playingAs;
	}

	/**
	 * Getter for the playingAgainst variable
	 * 
	 * @return
	 */
	public String getPlayingAgainst() {
		return playingAgainst;
	}

	/**
	 * Getter for the tile variable
	 * 
	 * @return
	 */
	public char getTile() {
		return tile;
	}

	/**
	 * Getter for the tileOpp variable
	 * 
	 * @return
	 */
	public char getTileOpp() {
		return tileOpp;
	}

}

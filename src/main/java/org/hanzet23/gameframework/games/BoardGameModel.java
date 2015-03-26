package main.java.org.hanzet23.gameframework.games;

/**
 * Dit is de abstracte klasse is de super van alle games die worden ontwikkeld in deze framework
 * In dit object worden basis functies gedefineerd die iedere board game moet bevatten.
 * Methodes die specifiek zijn aan de game zelf worden verder gedefineerd in de game klassen zelf. 
 * Created by Joz Reijnevweld on 26/03/15.
 */
public abstract class BoardGameModel {
    private char[][] board;
    private int playerState = -1; // -1 staat voor dat er nog geen player is geselecteerd

    public BoardGameModel(){
        //TODO BoardGameModel moet nog geimplementeerd worden
        //Moet gechecked worden welke parameters nodig zijn voor communicatie tussen objecten.
    }

    /**
     * Deze methode reset of print de board.
     * Dit komt voor als het spel start of voorbij is. 
     */
    public void SetResetGameBoard(){ 
        //TODO SetResetGameBoard moet nog geimplementeerd worden 
    }

    /**
     * Deze methode print het board in de meest recente status
     * Dit word aangeroepen wanneer een verandering is gemaakt aan het board situatie. 
     */
    public void PrintCurrentBoard(){ 
        //TODO PrintCurrentBoard moet nog geimplementeerd worden
    }

    /**
     * Deze methode checked of de board volledig is gevult.
     * Indien dit het geval is dan return deze een true. 
     */
    public boolean CheckIfBoardIsFull(){ return true; }

    /**
     * Deze methode checked of er een winnaar is.
     * Om te checken of er een winnaar is, zal deze gebruik maken van andere methodes
     * die specifiek zijn aan de desbetreffende board game.
     * Er word verwacht dat deze methode word @Override 
     */
    public abstract boolean CheckIfThereIsAWin();

    /**
     * Deze methode veranderd de status van een speler.
     * Dit houd in wanneer een speler aan de beurt is.
     */
    public void ChangePlayerState(){  
        //TODO ChangePlayerState moet nog geimplementeerd worden
    }

    /**
     * Deze methode zal een markering zetten in de board
     */
    public abstract void setMarker();
}

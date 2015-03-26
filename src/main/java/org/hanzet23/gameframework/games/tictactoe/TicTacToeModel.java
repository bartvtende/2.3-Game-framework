package main.java.org.hanzet23.gameframework.games.tictactoe;

import main.java.org.hanzet23.gameframework.games.BoardGameModel;

/**
 * Dit is een child klasse die de tictactoe game functies bevalt
 * Created by Joz Reijnevweld on 26/03/15.
 */
public class TicTacToeModel extends BoardGameModel {
    /**
     * Deze methode check of een player een win heeft in het diagonale.
     * @return boolean wanneer er een win is
     */
    private boolean checkDiagnalWin(){
        //TODO CheckDiagnalWin moet nog geimplementeerd worden.
        return false;
    }

    /**
     * Deze methode checked of een player een win heeft in het horizontale. 
     * @return boolean wanneer er een win is.
     */
    private boolean checkHorizontalWin(){
        //TODO CheckHorizontalWin moet nog geimplementeerd worden.
        return false;
    }

    /**
     * Deze methode checked of een player een win heeft in het verticale. 
     * @return boolean wanneer er een win is.
     */
    private boolean checkVerticalWin(){
        //TODO CheckVerticalWin moet nog geimplementeerd worden.
        return false;
    }

    @Override
    public boolean checkIfThereIsAWin() {
        if(checkDiagnalWin() && checkHorizontalWin() && checkVerticalWin()){ return true; }
        else { return false; }
    }

    @Override
    public void setMarker() {
        //TODO setMarker moet nog geimplementeerd worden
    }
}

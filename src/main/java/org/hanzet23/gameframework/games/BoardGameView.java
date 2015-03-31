package main.java.org.hanzet23.gameframework.games;

import java.awt.GridLayout;

/**
 * Deze klasse is verantwoordelijk om de mainpanel te vullen met alle grafische onderdelen
 * die nodig zijn voor het desbetreffende game
 * Created by Ivonov on 26/03/15.
 */
public class BoardGameView {
    private int rows;
    private int columns;
    private GridLayout graphicBoard;

    /**
     * Constructor bro
     * @param rows aantal rows
     * @param columns aantal columns
     */
    public BoardGameView(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        createBoard();
    }

    /**
     * Deze methode maakt een grafische board aan en voegt deze toe aan
     * de gui container.
     */
    public void createBoard(){
        graphicBoard = new GridLayout(rows, columns);
        //TODO createBoard moet worden geimplementeerd
        
    }

    /**
     * Deze methode zal de gegevens in de graphicBoard resetten en een redraw aanvragen
     */
    public void redrawBoard(){
        //TODO redrawBoard moet worden geimplementeerd
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import reversi.Game;

/**
 *
 * @author p1509283
 */
public class Board {

    private GridPane grid;
    private Pawn[][] matrix;
    
    
    public Board(int X_SIZE, int Y_SIZE) {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true); 
        grid.setPadding(new Insets(10));
        matrix = new Pawn[Y_SIZE][];
        for (int y = 0; y < Y_SIZE; y++) {
            for (int x = 0; x < X_SIZE; x++) {
                matrix[x] = new Pawn[X_SIZE];
                this.matrix[y][x] = null;
            }
        }
        this.matrix[Y_SIZE/2-1][X_SIZE/2-1]= new Pawn(Game.getPlayer1());
        this.matrix[Y_SIZE/2][X_SIZE/2-1]= new Pawn(Game.getPlayer2());
        this.matrix[Y_SIZE/2][X_SIZE/2]= new Pawn(Game.getPlayer1());
        this.matrix[Y_SIZE/2-1][X_SIZE/2]= new Pawn(Game.getPlayer2());
        
        displayCircle();
        //displayButtons();
    }
    
    private void displayCircle(){
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(this.matrix[y][x] != null){
                    if (this.matrix[y][x].getPlayer().equals(Game.getPlayer1()))
                        this.grid.add(new Pawn(Game.getPlayer1()).getCircle(), x, y);
                    if (this.matrix[y][x].getPlayer().equals(Game.getPlayer2()))
                        this.grid.add(new Pawn(Game.getPlayer2()).getCircle(), x, y);
                }
            }
        }
    }
    private void displayButtons(){
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(this.matrix[y][x] == null){
                    Button btn = new Button();
                    btn.setPadding(new Insets(40));
                    grid.add(btn, x, y);
                }
            }
        }
    }

    public GridPane getGrid() {
        return grid;
    }
    
}

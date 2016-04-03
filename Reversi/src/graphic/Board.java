/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import reversi.Game;
import rules.RulesChecker;

/**
 *
 * @author p1509283
 */
public class Board {

    private static GridPane grid;
    private static Pawn[][] matrix;
    
    
    public static void initBoard(int X_SIZE, int Y_SIZE) {
        grid = new GridPane();
        matrix = new Pawn[Y_SIZE][];
        for (int y = 0; y < Y_SIZE; y++) {
            for (int x = 0; x < X_SIZE; x++) {
                matrix[x] = new Pawn[X_SIZE];
                matrix[y][x] = null;
            }
        }
        matrix[Y_SIZE/2-1][X_SIZE/2-1]= new Pawn(Game.getPlayer1());
        matrix[Y_SIZE/2][X_SIZE/2-1]= new Pawn(Game.getPlayer2());
        matrix[Y_SIZE/2][X_SIZE/2]= new Pawn(Game.getPlayer1());
        matrix[Y_SIZE/2-1][X_SIZE/2]= new Pawn(Game.getPlayer2());
        
        refreshDisplay();
    }
    
    private static void displayCircles(){
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] != null){
                    if (matrix[y][x].getPlayer().equals(Game.getPlayer1()))
                        grid.add(new Pawn(Game.getPlayer1()).getCircle(), x, y);
                    if (matrix[y][x].getPlayer().equals(Game.getPlayer2()))
                        grid.add(new Pawn(Game.getPlayer2()).getCircle(), x, y);
                }
            }
        }
    }
    private static void displayButtons(){
        int[][] btnMatrix = RulesChecker.playableCells(RulesChecker.toIntMatrix(matrix), Game.getCurrentPlayer());
        for (int y = 0; y < btnMatrix.length; y++) {
            for (int x = 0; x < btnMatrix[y].length; x++) {
                    Button btn = new Button();
                    btn.setId(x+"/"+y);
                    btn.setMaxSize(Game.getPAWN_SIZE()*2, Game.getPAWN_SIZE()*2);
                    btn.setMinSize(Game.getPAWN_SIZE()*2, Game.getPAWN_SIZE()*2);
                    if(btnMatrix[y][x] == 1){
                        btn.setStyle("-fx-background-color: #425A46; -fx-border-color: black; -fx-border-width: 1;");
                        btn.setOnAction((ActionEvent event) -> {
                            String[] s = btn.getId().split("/");
                            int x1 = Integer.parseInt(s[0]);
                            int y1 = Integer.parseInt(s[1]);
                            Board.getMatrix()[y1][x1] = new Pawn(Game.getCurrentPlayer());
                            Game.swapCurrentPlayer();
                            Board.refreshDisplay();
                        });
                    }
                    else{
                         btn.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1;");
                    }
                    grid.add(btn, x, y);
            }
        }
    }

    public static GridPane getGrid() {
        return grid;
    }

    public static Pawn[][] getMatrix() {
        return matrix;
    }

    public static void setMatrix(Pawn[][] matrix) {
        Board.matrix = matrix;
    }

    private static void refreshDisplay() {
        grid.getChildren().clear();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: green; -fx-border-color: black; -fx-border-width: 1;");
        displayButtons();
        displayCircles();        
    }
    
    
}

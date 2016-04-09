/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import controller.Player;
import graphic.Board;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author p1509019
 */
public class Game extends Application {

    private static final int X_SIZE = 8;
    private static final int Y_SIZE = 8;
    public static final int PAWN_SIZE = 40;

    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    
    private static int difficulty;

    public static void swapCurrentPlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // init players
        player1 = new Player("Joueur 1", 1, true);
        player2 = new Player("Joueur 2", 2, true);
        currentPlayer = player1;
        
        // init difficulty
        difficulty = 1;
        
        int padding = 20;
        double scoreHeightFactor = 1.2;
        primaryStage.setTitle("Reversi");
        primaryStage.setHeight((8*PAWN_SIZE*2)*scoreHeightFactor);
        primaryStage.setWidth(8*PAWN_SIZE*2+padding);
        primaryStage.setResizable(false);

        Board.initScores();
        Board.initBoard(X_SIZE, Y_SIZE);
        
        StackPane childTScore1 = new StackPane();
        StackPane childTScore2 = new StackPane();
        StackPane root = new StackPane();
       
        root.getChildren().add(Board.getGrid());   
        childTScore1.getChildren().add(Board.getScorePlayer1());
        childTScore2.getChildren().add(Board.getScorePlayer2());
        
        childTScore1.setAlignment(Pos.TOP_LEFT);
        childTScore2.setAlignment(Pos.TOP_RIGHT);
        childTScore1.setMouseTransparent(true);
        childTScore2.setMouseTransparent(true);
        
        root.getChildren().add(childTScore1);
        root.getChildren().add(childTScore2);
        
        Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.GREEN);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static int getPAWN_SIZE() {
        return PAWN_SIZE;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }
    
    public static Player getPlayerByNumber(int n){
        switch(n){
            case 1: return player1;
            default : return player2;
        }
    }

    public static int getDifficulty() {
        return difficulty;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

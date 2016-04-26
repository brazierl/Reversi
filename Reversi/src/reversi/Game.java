/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import controller.Player;
import graphic.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
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
    
    private void initScene(Stage primaryStage, StackPane root) {
        int padding = 20;
        double scoreHeightFactor = 1.4;
        primaryStage.setTitle("Reversi");
        primaryStage.setHeight((X_SIZE * PAWN_SIZE * 2) * scoreHeightFactor);
        primaryStage.setWidth(Y_SIZE * PAWN_SIZE * 2 + padding);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    private void initScores(StackPane root) {
        StackPane childTScore1 = new StackPane();
        StackPane childTScore2 = new StackPane();
        childTScore1.getChildren().add(Board.getScorePlayer1());
        childTScore2.getChildren().add(Board.getScorePlayer2());
        
        childTScore1.setAlignment(Pos.BOTTOM_LEFT);
        childTScore2.setAlignment(Pos.BOTTOM_RIGHT);
        childTScore1.setMouseTransparent(true);
        childTScore2.setMouseTransparent(true);
        
        root.getChildren().add(childTScore1);
        root.getChildren().add(childTScore2);
    }
    
    private void initMenu(Stage primaryStage, StackPane root) {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fichier");
        MenuItem reset = new MenuItem("Réinitialiser");
        reset.setOnAction((ActionEvent t) -> {
            initGame(primaryStage);
        });
        menuFile.getItems().addAll(reset);
        Menu menuView = new Menu("Partie");
        MenuItem pvp = new MenuItem("Joueur vs Joueur");
        pvp.setOnAction((ActionEvent t) -> {
            player1.setHuman(true);
            player2.setHuman(true);
        });
        MenuItem pvc = new MenuItem("Joueur vs Ordinateur");
        pvc.setOnAction((ActionEvent t) -> {
            player1.setHuman(true);
            player2.setHuman(false);
        });
        MenuItem cvc = new MenuItem("Ordinateur vs Ordinateur");
        cvc.setOnAction((ActionEvent t) -> {
            player1.setHuman(false);
            player2.setHuman(false);
        });
        
        Menu diff = new Menu("Difficulté");
        MenuItem diff1 = new MenuItem("Facile");
        MenuItem diff2 = new MenuItem("Moyen");
        MenuItem diff3 = new MenuItem("Difficile");
        
        if(difficulty==1)
            diff1.setStyle("-fx-font-weight:bold;");
        if(difficulty==2)
            diff2.setStyle("-fx-font-weight:bold;");
        if(difficulty==3)
            diff3.setStyle("-fx-font-weight:bold;");
        
        diff1.setOnAction((ActionEvent t) -> {
            difficulty = 1;
            diff1.setStyle("-fx-font-weight:bold;");
            diff2.setStyle("-fx-font-weight:normal;");
            diff3.setStyle("-fx-font-weight:normal;");
        });
        
        diff2.setOnAction((ActionEvent t) -> {
            difficulty = 2;
            diff1.setStyle("-fx-font-weight:normal;");
            diff2.setStyle("-fx-font-weight:bold;");
            diff3.setStyle("-fx-font-weight:normal;");
        });
        
        diff3.setOnAction((ActionEvent t) -> {
            difficulty = 3;
            diff1.setStyle("-fx-font-weight:normal;");
            diff2.setStyle("-fx-font-weight:normal;");
            diff3.setStyle("-fx-font-weight:bold;");
        });
        
        diff.getItems().addAll(diff1, diff2, diff3);
        
        menuView.getItems().addAll(pvp, pvc, cvc, diff);
        
        menuBar.getMenus().addAll(menuFile, menuView);
        root.setAlignment(Pos.TOP_CENTER);
        
        root.getChildren().addAll(menuBar);
    }
    
    private void initGame(Stage primaryStage) {
        StackPane root = new StackPane();
        
        Board.initScores();
        
        Board.initBoard(X_SIZE, Y_SIZE);
        root.getChildren().add(Board.getGrid());
        
        initScores(root);
        
        initMenu(primaryStage, root);
        
        initScene(primaryStage, root);
    }
    
    @Override
    public void start(Stage primaryStage) {
        // init players
        player1 = new Player("Joueur 1", 1, true);
        player2 = new Player("Joueur 2", 2, false);
        currentPlayer = player1;

        // init difficulty
        difficulty = 1;
        
        initGame(primaryStage);
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
    
    public static Player getPlayerByNumber(int n) {
        switch (n) {
            case 1:
                return player1;
            default:
                return player2;
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

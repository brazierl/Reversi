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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    
    @Override
    public void start(Stage primaryStage) {
        player1 = new Player("La bite", 1, true);
        player2 = new Player("La shnek", 2, true);
        primaryStage.setTitle("Reversi");
        primaryStage.setHeight(8*(PAWN_SIZE*3/2)*2);
        primaryStage.setWidth(8*(PAWN_SIZE*3/2)*2);
        primaryStage.setResizable(false);
        
        Board board = new Board(X_SIZE,Y_SIZE);
                
        StackPane root = new StackPane();
        root.getChildren().add(board.getGrid());
        
        Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.GREEN);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        // BULLSHIT
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

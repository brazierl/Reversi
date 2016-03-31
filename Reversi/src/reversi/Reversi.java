/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author p1509019
 */
public class Reversi extends Application {
    private static final int X_SIZE = 8;
    private static final int Y_SIZE = 8;
    private static final int PAWN_SIZE = 40;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reversi");
        primaryStage.setHeight(8*(PAWN_SIZE*3/2)*2);
        primaryStage.setWidth(8*(PAWN_SIZE*3/2)*2);
        primaryStage.setResizable(false);
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setGridLinesVisible(true);
        for(int x = 0; x<X_SIZE; x++){
            for(int y = 0; y<Y_SIZE; y++){
                Button btn = new Button();
                btn.setGraphic(new Circle(PAWN_SIZE));
                grid.add(btn, x, y);
            }
        }      
        
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

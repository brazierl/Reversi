/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import reversi.Game;
import rules.Move;

/**
 *
 * @author p1509283
 */
public class Board {

    private static GridPane grid;
    private static Text scorePlayer1;
    private static Text scorePlayer2;
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
        matrix[Y_SIZE / 2 - 1][X_SIZE / 2 - 1] = new Pawn(Game.getPlayer1());
        matrix[Y_SIZE / 2][X_SIZE / 2 - 1] = new Pawn(Game.getPlayer2());
        matrix[Y_SIZE / 2][X_SIZE / 2] = new Pawn(Game.getPlayer1());
        matrix[Y_SIZE / 2 - 1][X_SIZE / 2] = new Pawn(Game.getPlayer2());

        refreshDisplay();
    }

    private static void displayCircles() {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != null) {
                    if (matrix[y][x].getPlayer().equals(Game.getPlayer1())) {
                        grid.add(new Pawn(Game.getPlayer1()).getCircle(), x, y);
                    }
                    if (matrix[y][x].getPlayer().equals(Game.getPlayer2())) {
                        grid.add(new Pawn(Game.getPlayer2()).getCircle(), x, y);
                    }
                }
            }
        }
    }

    private static void displayButtons() {
        int[][] btnMatrix = Move.playableCells(Pawn.toIntMatrix(matrix), Game.getCurrentPlayer());
        int nbPlayableCells = 0;
        for (int y = 0; y < btnMatrix.length; y++) {
            for (int x = 0; x < btnMatrix[y].length; x++) {
                if (displayButton(y, x, btnMatrix[y][x])) {
                    nbPlayableCells++;
                }
            }
        }
        if (!Game.getCurrentPlayer().isHuman()) {
            grid.setDisable(true);
        }
        if (nbPlayableCells == 0 && !isMatrixFull()) {
            Game.swapCurrentPlayer();
            refreshDisplay();
        }
    }

    private static boolean displayButton(int y, int x, int cell) {
        Button btn = new Button();
        boolean ans = false;
        btn.setId(x + "/" + y);
        btn.setMaxSize(Game.getPAWN_SIZE() * 2, Game.getPAWN_SIZE() * 2);
        btn.setMinSize(Game.getPAWN_SIZE() * 2, Game.getPAWN_SIZE() * 2);
        btn.setStyle("-fx-background-color: #425A46; -fx-border-color: black; -fx-border-width: 1;");
        if (cell == 1) {
            ans = true;
            if (Game.getCurrentPlayer().isHuman()) {
                btn.setOnAction((ActionEvent event) -> {
                    String[] s = btn.getId().split("/");
                    int x1 = Integer.parseInt(s[0]);
                    int y1 = Integer.parseInt(s[1]);
                    matrix = Controller.onClickPawn(matrix, x1, y1);
                    Game.swapCurrentPlayer();
                    refreshDisplay();
                });
            }
        } else {
            btn.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1;");
        }
        grid.add(btn, x, y);
        return ans;
    }

    private static void playAI() {
        int[][] btnMatrix = Move.playableCells(Pawn.toIntMatrix(matrix), Game.getCurrentPlayer());
        int[] coordAI = Controller.nextMove(matrix, btnMatrix);
        if (coordAI != null) {
            matrix = Controller.onClickPawn(matrix, coordAI[1], coordAI[0]);
            grid.setDisable(false);
        }
        Game.swapCurrentPlayer();
        refreshDisplay();
    }

    private static void updateScores() {
        int[] checkScores = Move.getScores(Pawn.toIntMatrix(matrix));
        Game.getPlayer1().setScore(checkScores[0]);
        Game.getPlayer2().setScore(checkScores[1]);
    }

    public static void displayScores() {
        scorePlayer1.setText(Game.getPlayer1().getName() + " : " + Game.getPlayer1().getScore());
        scorePlayer2.setText(Game.getPlayer2().getName() + " : " + Game.getPlayer2().getScore());
    }

    public static void initScores() {
        scorePlayer1 = new Text(30, 30, Game.getPlayer1().getName() + " : " + Game.getPlayer1().getScore());
        scorePlayer2 = new Text(100, 15, Game.getPlayer2().getName() + " : " + Game.getPlayer2().getScore());
        scorePlayer1.setFont(Font.font("Algerian", 40));
        scorePlayer2.setFont(Font.font("Algerian", 40));
        scorePlayer2.setTextAlignment(TextAlignment.RIGHT);
    }

    public static GridPane getGrid() {
        return grid;
    }

    public static Text getScorePlayer1() {
        return scorePlayer1;
    }

    public static Text getScorePlayer2() {
        return scorePlayer2;
    }

    public static Pawn[][] getMatrix() {
        return matrix;
    }

    public static void setMatrix(Pawn[][] matrix) {
        Board.matrix = matrix;
    }

    private static void refreshDisplay() {
        if (isMatrixFull()) {
            displayGameOver();
        } else {
            if (!Game.getCurrentPlayer().isHuman()) {
                playAI();
            }
            grid.getChildren().clear();
            grid.setAlignment(Pos.CENTER);
            grid.setStyle("-fx-background-color: green;");
            displayCircles();
            updateScores();
            displayScores();
            displayButtons();

        }
    }

    private static boolean isMatrixFull() {
        for (Pawn[] matrix1 : matrix) {
            for (Pawn item : matrix1) {
                if (item == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayGameOver() {
        grid.setDisable(true);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphic;

import controller.Player;
import javafx.scene.shape.Circle;
import reversi.Game;

/**
 *
 * @author p1509283
 */
public class Pawn {

    private Player player;
    private Circle circle;
    
    public Pawn(Player player) {
        this.player = player;
        this.circle = new Circle(Game.getPAWN_SIZE());
        if (player.getNumber() == Colors.BLACK.getNumber())
            this.circle.setFill(Colors.BLACK.getGraphicColor());
        else 
            this.circle.setFill(Colors.WHITE.getGraphicColor());     
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
    
    public static int[][] toIntMatrix(Pawn[][] matrix) {
        int[][] playerMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            playerMatrix[y] = new int[matrix[y].length];
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == null) {
                    playerMatrix[y][x] = 0;
                } else {
                    playerMatrix[y][x] = matrix[y][x].getPlayer().getNumber();
                }
            }
        }
        return playerMatrix;
    }

    public static Pawn[][] toMatrix(int[][] matrix) {
        Pawn[][] playerMatrix = new Pawn[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            playerMatrix[y] = new Pawn[matrix[y].length];
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 0) {
                    playerMatrix[y][x] = null;
                } else {
                    playerMatrix[y][x] = new Pawn(Game.getPlayerByNumber(matrix[y][x]));
                }
            }
        }
        return playerMatrix;
    }
}

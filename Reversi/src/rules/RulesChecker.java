/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import controller.Player;
import graphic.Pawn;

/**
 *
 * @author p1509283
 */
public class RulesChecker {

    /*public static int[] playableCells(int[][] matrix, Player p) {
        for (int y = 0; y < matrix.length; y++) {
            checkRow(matrix, y, p);
        }
        for (int x = 0; x < matrix[0].length; x++) {
            checkCol(matrix, x, p);
        }

    }

    private static int[] checkRow(int[][] matrix, int rowId, Player p) {
        for (int x = 1; x < matrix[rowId].length; x++) {
            if (matrix[rowId][x-1]=!matrix[rowId][x]) {
                
            }
        }

    }

    private static int[] checkCol(int[][] matrix, int x, Player p) {

    }

    public static int[][] toIntMatrix(Pawn[][] matrix) {
        int[][] playerMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                playerMatrix[y] = new int[matrix[y].length];
                playerMatrix[y][x] = matrix[y][x].getPlayer().getNumber();
            }
        }
        return playerMatrix;
    }*/

}

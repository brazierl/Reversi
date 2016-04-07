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

    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int TOP = 3;
    public final static int BOTTOM = 4;

    public static int[][] playableCells(int[][] matrix, Player p) {
        // ROWS
        int[][] resMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            resMatrix[y] = new int[matrix[y].length];
            int[] rows = checkRow(matrix, y, p);
            int[] diagsLeft = checkDiagonalRow(matrix, y, p, LEFT);
            int[] diagsRight = checkDiagonalRow(matrix, y, p, RIGHT);
            for (int i = 0; i < rows.length; i++) {
                if (rows[i] != 0) {
                    resMatrix[y][rows[i]] = 1;
                }
            }
            for (int i = 0; i < diagsLeft.length; i++) {
                if (diagsLeft[i] != 0) {
                    resMatrix[y][diagsLeft[i]] = 1;
                }
            }
            for (int i = 0; i < diagsRight.length; i++) {
                if (diagsRight[i] != 0) {
                    resMatrix[y][diagsRight[i]] = 1;
                }
            }
        }

        // COLUMNS
        for (int x = 0; x < matrix[0].length; x++) {
            int[] cols = checkCol(matrix, x, p);
            int[] diagsTop = checkDiagonalCol(matrix, x, p, TOP);
            int[] diagsBot = checkDiagonalCol(matrix, x, p, BOTTOM);
            for (int i = 0; i < cols.length; i++) {
                if (cols[i] != 0) {
                    resMatrix[cols[i]][x] = 1;
                }
            }
            for (int i = 0; i < diagsTop.length; i++) {
                if (diagsTop[i] != 0) {
                    resMatrix[diagsTop[i]][x] = 1;
                }
            }
            for (int i = 0; i < diagsBot.length; i++) {
                if (diagsBot[i] != 0) {
                    resMatrix[diagsBot[i]][x] = 1;
                }
            }
        }

        return resMatrix;
    }

    private static int[] checkRow(int[][] matrix, int rowId, Player p) {
        int[] res = new int[matrix[0].length];
        int id = 0;
        for (int x = 0; x < matrix[rowId].length - 2; x++) {
            if (matrix[rowId][x] == 0 && matrix[rowId][x + 1] != p.getNumber() && matrix[rowId][x + 1] != 0) {
                for (int i = 1; i < (matrix[rowId].length - x); i++) {
                    if (matrix[rowId][x + i] == p.getNumber()) {
                        res[id] = x;
                        id++;
                    }
                    if (matrix[rowId][x + i] == 0) {
                        break;
                    }
                }
            }
            if (matrix[rowId][x] != 0 && matrix[rowId][x + 1] != p.getNumber() && matrix[rowId][x + 1] != 0) {
                for (int i = x; i >= 0; i--) {
                    if (matrix[rowId][i] == p.getNumber()) {
                        res[id] = x + 2;
                        id++;
                    }
                    if (matrix[rowId][i] == 0) {
                        break;
                    }
                }
            }
        }
        return res;
    }

    private static int[] checkCol(int[][] matrix, int colId, Player p) {
        int[] res = new int[matrix[0].length];
        int id = 0;
        for (int y = 0; y < matrix.length - 2; y++) {
            if (matrix[y][colId] == 0 && matrix[y + 1][colId] != p.getNumber() && matrix[y + 1][colId] != 0) {
                for (int i = 1; i < matrix.length - y; i++) {
                    if (matrix[y + i][colId] == p.getNumber()) {
                        res[id] = y;
                        id++;
                    }
                    if (matrix[y + i][colId] == 0) {
                        break;
                    }
                }
            }
            if (matrix[y][colId] != 0 && matrix[y + 1][colId] != p.getNumber() && matrix[y + 1][colId] != 0) {
                if (matrix[y - 1][colId] != matrix[y][colId]) {
                    for (int i = y; i >= 0; i--) {
                        if (matrix[i][colId] == p.getNumber()) {
                            res[id] = y + 2;
                            id++;
                        }
                        if (matrix[i][colId] == 0) {
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    private static int[] checkDiagonalRow(int[][] matrix, int rowId, Player p, int side) {
        int[] res = new int[matrix[0].length];
        /*int id = 0;
         for (int x = 0; x < matrix.length - 2; x++) {
         if (matrix[rowId + x][x] == 0 && matrix[rowId + x + 1][x + 1] != p.getNumber() && matrix[rowId + x + 1][x + 1] != 0) {
         for (int i = 1; i < matrix.length - y; i++) {
         if (matrix[y + i][colId] == p.getNumber()) {
         res[id] = y;
         id++;
         }
         if (matrix[y + i][colId] == 0) {
         break;
         }
         }
         }
         if (matrix[rowId - x][x] == 0 && matrix[rowId - x + 1][x + 1] != p.getNumber() && matrix[rowId - x + 1][x + 1] != 0) {
         {
         if (matrix[y - 1][colId] != matrix[y][colId]) {
         for (int i = y; i >= 0; i--) {
         if (matrix[i][colId] == p.getNumber()) {
         res[id] = y + 2;
         id++;
         }
         if (matrix[i][colId] == 0) {
         break;
         }
         }
         }
         }
         }
         }*/
        return res;
    }

    private static int[] checkDiagonalCol(int[][] matrix, int colId, Player p, int side) {
        int[] res = new int[matrix[0].length];
        /*int id = 0;
         for (int d = 0; d < matrix.length - 2; d++) {
         if (matrix[rowId][colId] == 0 && matrix[rowId + 1][colId + 1] != p.getNumber() && matrix[rowId + 1][colId + 1] != 0) {
         for (int i = 1; i < matrix.length - y; i++) {
         if (matrix[y + i][colId] == p.getNumber()) {
         res[id] = y;
         id++;
         }
         if (matrix[y + i][colId] == 0) {
         break;
         }
         }
         }
         if (matrix[y][colId] != 0 && matrix[y + 1][colId] != p.getNumber() && matrix[y + 1][colId] != 0) {
         if (matrix[y - 1][colId] != matrix[y][colId]) {
         for (int i = y; i >= 0; i--) {
         if (matrix[i][colId] == p.getNumber()) {
         res[id] = y + 2;
         id++;
         }
         if (matrix[i][colId] == 0) {
         break;
         }
         }
         }
         }
         }*/
        return res;
    }

    /*public static int[][] flipPawn(int[][] matrix, int [][] lastPlayed, Player p){
        
     }
     */
    public static int[] getScores(int[][] matrix) {
        int score1 = 0;
        int score2 = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != 0) {
                    if (matrix[y][x]==1) {
                        score1++;
                    }
                    if (matrix[y][x]==2) {
                        score2++;
                    }
                }
            }
        }
        int[] scores = {score1, score2};
        return scores;
    }

    public static int[][] toIntMatrix(Pawn[][] matrix) {
        int[][] playerMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            playerMatrix[y] = new int[matrix[y].length];
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == null) {
                    playerMatrix[y][x] = 0;
                } else {
                    int p = matrix[y][x].getPlayer().getNumber();
                    playerMatrix[y][x] = p;
                }
            }
        }
        return playerMatrix;
    }

}

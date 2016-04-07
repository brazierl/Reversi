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

    private final static int LEFT = 1;
    private final static int RIGHT = 2;
    private final static int TOP = 3;
    private final static int BOTTOM = 4;

    public static int[][] playableCells(int[][] matrix, Player p) {
        // ROWS
        int[][] resMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            resMatrix[y] = new int[matrix[y].length];
            int[] rows = checkRow(matrix, y, p);
            //int[] diags1 = checkDiagonal(matrix, y, 0, p);
            //int[] diags2 = checkDiagonal(matrix, y, matrix[0].length, p);
            for (int i = 0; i < rows.length; i++) {
                if (rows[i] != -1 && matrix[y][rows[i]]==0) {
                    resMatrix[y][rows[i]] = 1;
                }
            }
        }

        // COLUMNS
        for (int x = 0; x < matrix[0].length; x++) {
            int[] cols = checkCol(matrix, x, p);
            //int[] diags3 = checkDiagonal(matrix, 0, x, p);
            //int[] diags4 = checkDiagonal(matrix, matrix.length-1, x, p);
            for (int i = 0; i < cols.length; i++) {
                if (cols[i] != -1 && matrix[cols[i]][x]==0) {
                    resMatrix[cols[i]][x] = 1;
                }
            }
        }
        

        return resMatrix;
    }

    private static int[] checkRow(int[][] matrix, int rowId, Player p) {
        int[] res = emptyLine(matrix.length);
        int id = 0;
        for (int x = 0; x < matrix[rowId].length - 2; x++) {
            if (matrix[rowId][x] == 0 && matrix[rowId][x + 1] != p.getNumber() && matrix[rowId][x + 1] != 0) {
                for (int i = 2; i < (matrix[rowId].length - x); i++) {
                    if (matrix[rowId][x + i] == p.getNumber()) {
                        res[id] = x;
                        id++;
                    }
                    else{
                        res[id] = -1;
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
        int[] res = emptyLine(matrix[0].length);
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
        return res;
    }

    private static int[][] checkDiagonal(int[][] matrix, Player p) {
        // On teste chaque case vide en diagonale dans les 4 sens 
        int[][] res = emptyMatrix(matrix[0].length);
        int id = 0;
        int x = 0;
        int y = 0;
        int colId=0;
        for(int leftCol=0; leftCol<matrix[0].length; leftCol++){
            if(matrix[y][x] == 0 && matrix[y+1][x+1] != p.getNumber() && matrix[y+1][x+1] != 0){
                int i = 2;
                while(x+i>=0 && x+i<matrix[0].length && y+i>=0 && y+i<matrix.length){
                    if (matrix[y + i][x + i] == p.getNumber()) {
                            //res[id] = y;
                            id++;
                        }
                    if (matrix[y + i][x + i] == 0) {
                        break;
                    }
                    i++;
                }
            }
            if(matrix[y][x] == 0 && matrix[y-1][x-1] != p.getNumber() && matrix[y-1][x-1] != 0){
                int i = 2;
                while(x+i>=0 && x+i<matrix[0].length && y+i>=0 && y+i<matrix.length){
                    if (matrix[y - i][x - i] == p.getNumber()) {
                            //res[id] = y;
                            id++;
                        }
                    if (matrix[y + i][x + i] == 0) {
                        break;
                    }
                    i++;
                }
            }
        }
        if(colId==matrix[0].length-1){
            if(matrix[y][x] == 0 && matrix[y-1][x-1] != p.getNumber() && matrix[y-1][x-1] != 0){
                int i = 2;
                while(x+i>=0 && x+i<matrix[0].length && y+i>=0 && y+i<matrix.length){
                    if (matrix[y - i][x - i] == p.getNumber()) {
                            //res[id] = y;
                            id++;
                        }
                    if (matrix[y + i][x + i] == 0) {
                        break;
                    }
                    i++;
                }
            }
            if (matrix[y][x] != 0 && matrix[y-1][x-1] != p.getNumber() && matrix[y-1][x-1] != 0) {
                int i = 2;
                while(x+i>=0 && x+i<matrix[0].length && y+i>=0 && y+i<matrix.length){
                    if (matrix[y - i][x - i] == p.getNumber()) {
                            //res[id] = y;
                            id++;
                        }
                    if (matrix[y + i][x + i] == 0) {
                        break;
                    }
                    i++;
                }
            }
        }
        return res;
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
    
    private static int[] emptyLine(int size){
        int[] emptyLine = new int[size];
        for(int i = 0; i<size; i++){
            emptyLine[i]=-1;
        }
        return emptyLine;
    }
    
    private static int[][] emptyMatrix(int size){
        int[][] emptyMatrix = new int[size][];
        for(int i = 0; i<size; i++){
            emptyMatrix[i]=emptyLine(size);
        }
        return emptyMatrix;
    }
    
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

}

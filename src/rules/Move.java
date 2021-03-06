/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import controller.Player;
/**
 * Move class. Represents a move played by a player. Also manage the legal moves and the color of the pawns.
 * @author p1509283
 */
public class Move {
    
    private int x;
    private int y;
    /**
     * The grid before the move was played.
     */
    private int[][] previousMatrix;

    public Move(int x, int y, int[][] previousMatrix) {
        this.x = x;
        this.y = y;
        this.previousMatrix = previousMatrix;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getPreviousMatrix() {
        return previousMatrix;
    }

    public void setPreviousMatrix(int[][] previousMatrix) {
        this.previousMatrix = previousMatrix;
    }
    
    /**
     * Return a matrix of the playable cells on a board.
     * 
     * @param matrix current grid
     * @param p current player
     * @return new matrix where the player can do his next move. 1 if the tile ise playable, 0 otherwise.
     */
    public static int[][] playableCells(int[][] matrix, Player p) {
        int[][] resMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            resMatrix[y] = new int[matrix[y].length];
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == 0 && (checkRow(matrix, y, x, p) || checkCol(matrix, y, x, p) || checkDiagonal(matrix, y, x, p)) && matrix[y][x] == 0) {
                    resMatrix[y][x] = 1;
                }
            }
        }
        return resMatrix;
    }

    /**
     * Check if the cell is playable in the row.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return true is it is playable.
     */
    private static boolean checkRow(int[][] matrix, int rowId, int colId, Player p) {
        int id = 0;
        if (colId < matrix[rowId].length - 2) {
            if (matrix[rowId][colId + 1] != p.getNumber() && matrix[rowId][colId + 1] != 0) {
                for (int i = 2; i < (matrix[rowId].length - colId); i++) {
                    if (matrix[rowId][colId + i] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId][colId + i] == 0) {
                        break;
                    }
                }
            }
        }
        if (colId > 1) {
            if (matrix[rowId][colId - 1] != p.getNumber() && matrix[rowId][colId - 1] != 0) {
                for (int i = colId - 1; i >= 0; i--) {
                    if (matrix[rowId][i] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId][i] == 0) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the cell is playable in the column.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return 
     */
    private static boolean checkCol(int[][] matrix, int rowId, int colId, Player p) {
        int id = 0;
        if (rowId < matrix.length - 2) {
            if (matrix[rowId + 1][colId] != p.getNumber() && matrix[rowId + 1][colId] != 0) {
                for (int i = 2; i < matrix.length - rowId; i++) {
                    if (matrix[rowId + i][colId] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId + i][colId] == 0) {
                        break;
                    }
                }
            }
        }
        if (rowId > 1) {
            if (matrix[rowId - 1][colId] != p.getNumber() && matrix[rowId - 1][colId] != 0) {
                for (int i = rowId - 1; i >= 0; i--) {
                    if (matrix[i][colId] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[i][colId] == 0) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the cell is playable in the diagonal.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return 
     */
    private static boolean checkDiagonal(int[][] matrix, int rowId, int colId, Player p) {
        int id = 0;
        if (rowId < matrix.length - 2 && colId < matrix[rowId].length - 2) {
            if (matrix[rowId + 1][colId + 1] != p.getNumber() && matrix[rowId + 1][colId + 1] != 0) {
                for (int i = 2; i <= Math.min(matrix.length-rowId-1,matrix.length-colId-1); i++) {
                    if (matrix[rowId + i][colId + i] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId + i][colId + i] == 0) {
                        break;
                    }
                }
            }
        }
        if (rowId > 1 && colId < matrix[rowId].length - 2) {
            if (matrix[rowId - 1][colId + 1] != p.getNumber() && matrix[rowId - 1][colId + 1] != 0) {
                for (int i = 2; i <= Math.min(rowId,matrix.length-colId-1); i++) {
                    if (matrix[rowId - i][colId + i] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId - i][colId + i] == 0) {
                        break;
                    }
                }
            }
        }
        if (rowId < matrix.length - 2 && colId > 2) {
            if (matrix[rowId + 1][colId - 1] != p.getNumber() && matrix[rowId + 1][colId - 1] != 0) {
                for (int i = 2; i <= Math.min(matrix.length-rowId-1,colId); i++) {
                    if (matrix[rowId + i][colId - i] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId + i][colId - i] == 0) {
                        break;
                    }
                }
            }
        }
        if (rowId > 1 && colId > 1) {
            if (matrix[rowId - 1][colId - 1] != p.getNumber() && matrix[rowId - 1][colId - 1] != 0) {
                for (int i = 2; i <= Math.min(rowId,colId); i++) {
                    if (matrix[rowId - i][colId - i] == p.getNumber()) {
                        return true;
                    }
                    if (matrix[rowId - i][colId - i] == 0) {
                        break;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Give the new aspect of the board after a move.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return new matrix
     */
    public static int[][] returnMatrixMove(int[][] matrix, int rowId, int colId,Player p){
        int[][] resMatrix = matrix.clone();
        int[][] resRow = checkRowToReturn(matrix.clone(), rowId, colId, p);
        int[][] resCol = checkColToReturn(matrix.clone(), rowId, colId, p);
        int[][] resDiag = checkDiagonalToReturn(matrix.clone(), rowId, colId, p);
        for (int y = 0; y < resMatrix.length; y++) {
            resMatrix[y] = matrix[y].clone();
            for (int x = 0; x < resMatrix[y].length; x++) {
                if(resRow[y][x] == 1 || resCol[y][x] == 1 || resDiag[y][x] == 1)
                    resMatrix[y][x]=p.getNumber();
            }
        }
        resMatrix[rowId][colId] = p.getNumber();
        return resMatrix;
    }

    /**
     * Check the row to change the cells.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return new matrix. 1 if the column has changed, 0 otherwise.
     */
    private static int[][] checkRowToReturn(int[][] matrix, int rowId, int colId, Player p) {
        int id = 0;
        int[][] resMatrix = convertToZeroMatrix(matrix);
        if (colId < matrix[rowId].length - 2) {
            if (matrix[rowId][colId + 1] != p.getNumber() && matrix[rowId][colId + 1] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = 1; i < (matrix[rowId].length - colId); i++) {
                    if (matrix[rowId][colId + i] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[rowId][colId + i] == 0) {
                        break;
                    }
                    tmp[rowId][colId + i]=1;
                }
            }
        }
        if (colId > 1) {
            if (matrix[rowId][colId - 1] != p.getNumber() && matrix[rowId][colId - 1] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = colId-1; i >= 0; i--) {
                    if (matrix[rowId][i] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[rowId][i] == 0) {
                        break;
                    }
                    tmp[rowId][i]=1;
                }
            }
        }
        return resMatrix;
    }

    /**
     * Check the column to change the cells.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return new matrix. 1 if the column has changed, 0 otherwise.
     */
    private static int[][] checkColToReturn(int[][] matrix, int rowId, int colId, Player p) {
        int id = 0;
        int[][] resMatrix = convertToZeroMatrix(matrix);
        if (rowId < matrix.length - 2) {
            if (matrix[rowId + 1][colId] != p.getNumber() && matrix[rowId + 1][colId] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = 1; i < matrix.length - rowId; i++) {
                    if (matrix[rowId + i][colId] == p.getNumber()) {
                        resMatrix = tmp.clone();
                        break;
                    }
                    if (matrix[rowId + i][colId] == 0) {
                        break;
                    }
                    tmp[rowId + i][colId]=1;
                }
            }
        }
        if (rowId > 1) {
            if (matrix[rowId - 1][colId] != p.getNumber() && matrix[rowId - 1][colId] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = rowId-1; i >= 0; i--) {
                    if (matrix[i][colId] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[i][colId] == 0) {
                        break;
                    }
                    tmp[i][colId]=1;
                }
            }
        }
        return resMatrix;
    }

    /**
     * Check the diagonal to change the cells.
     * 
     * @param matrix
     * @param rowId
     * @param colId
     * @param p
     * @return new matrix. 1 if the column has changed, 0 otherwise.
     */
    private static int[][] checkDiagonalToReturn(int[][] matrix, int rowId, int colId, Player p) {
        int id = 0;
        int[][] resMatrix = convertToZeroMatrix(matrix);
        if (rowId < matrix.length - 2 && colId < matrix[rowId].length - 2) {
            if (matrix[rowId + 1][colId + 1] != p.getNumber() && matrix[rowId + 1][colId + 1] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = 1; i <= Math.min(matrix.length-rowId-1,matrix.length-colId-1); i++) {
                    if (matrix[rowId + i][colId + i] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[rowId + i][colId + i] == 0) {
                        break;
                    }
                    tmp[rowId + i][colId + i]=1;
                }
            }
        }
        if (rowId > 1 && colId < matrix[rowId].length - 2) {
            if (matrix[rowId - 1][colId + 1] != p.getNumber() && matrix[rowId - 1][colId + 1] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = 1; i <= Math.min(rowId,matrix.length-colId-1); i++) {
                    if (matrix[rowId - i][colId + i] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[rowId - i][colId + i] == 0) {
                        break;
                    }
                    tmp[rowId - i][colId + i]=1;
                }
            }
        }
        if (rowId < matrix.length - 2 && colId > 1) {
            if (matrix[rowId + 1][colId - 1] != p.getNumber() && matrix[rowId + 1][colId - 1] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = 1; i <= Math.min(matrix.length-rowId-1,colId); i++) {
                    if (matrix[rowId + i][colId - i] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[rowId + i][colId - i] == 0) {
                        break;
                    }
                    tmp[rowId + i][colId - i]=1;
                }
            }
        }
        if (rowId > 1 && colId > 1) {
            if (matrix[rowId - 1][colId - 1] != p.getNumber() && matrix[rowId - 1][colId - 1] != 0) {
                int[][] tmp = getCloneMatrix(resMatrix);
                for (int i = 1; i <= Math.min(rowId,colId); i++) {
                    if (matrix[rowId - i][colId - i] == p.getNumber()) {
                        resMatrix = getCloneMatrix(tmp);
                        break;
                    }
                    if (matrix[rowId - i][colId - i] == 0) {
                        break;
                    }
                    tmp[rowId - i][colId - i]=1;
                }
            }
        }
        return resMatrix;
    }
        
    /**
     * Get the scores of each play the board
     * @param matrix to check
     * @return int array. First element is player 1's score, second element is player 2's score.
     */
    public static int[] getScores(int[][] matrix) {
        int score1 = 0;
        int score2 = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != 0) {
                    if (matrix[y][x] == 1) {
                        score1++;
                    }
                    if (matrix[y][x] == 2) {
                        score2++;
                    }
                }
            }
        }
        int[] scores = {score1, score2};
        return scores;
    }
    
    /**
     * Give an empty matrix depending of the one in parameter.
     * @param matrix
     * @return empty matrix
     */
    private static int[][] convertToZeroMatrix(int[][] matrix){
        int[][] resMatrix = matrix.clone();
        for (int y = 0; y < resMatrix.length; y++) {
            resMatrix[y] = matrix[y].clone();
            for (int x = 0; x < resMatrix[0].length; x++) {
                resMatrix[y][x]=0;
            }
        }
        return resMatrix;
    }
    
    /**
     * Clone the matrix in parameter.
     * 
     * @param matrix
     * @return new matrix.
     */
    private static int[][] getCloneMatrix(int[][] matrix){
        int[][] resMatrix = matrix.clone();
        for (int y = 0; y < resMatrix.length; y++) {
            resMatrix[y] = matrix[y].clone();
        }
        return resMatrix;
    }

    /**
     * Check if the matrix has playable cell for the player.
     * 
     * @param matrix
     * @param p
     * @return false if no playable cell.
     */
    public static boolean hasPlayableCell(int[][] matrix, Player p){
        int[][] resMatrix = new int[matrix.length][];
        for (int y = 0; y < matrix.length; y++) {
            resMatrix[y] = new int[matrix[y].length];
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == 0 && (checkRow(matrix, y, x, p) || checkCol(matrix, y, x, p) || checkDiagonal(matrix, y, x, p)) && matrix[y][x] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

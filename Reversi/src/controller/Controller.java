/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import graphic.Pawn;
import java.util.ArrayList;
import javafx.util.Pair;
import reversi.Game;
import rules.Move;

/**
 *
 * @author p1509283
 */
public class Controller {

    /**
     * Play a move on a grid and return the future grid.
     * 
     * @param matrix current grid
     * @param x position to play
     * @param y position to play
     * @return Pawn[][] Grid after a move.
     */
    public static Pawn[][] onClickPawn(Pawn[][] matrix, int x, int y) {
        return Pawn.toMatrix(Move.returnMatrixMove(Pawn.toIntMatrix(matrix), y, x, Game.getCurrentPlayer()));
    }
    
    /**
     * Play a next move for the AI. 
     * With diffculty 1 (easy) random move. 
     * With diffculty 2 (medium) minimax algorithm with a depth of 6. 
     * With diffculty 3 (hard) minimax algorithm with a depth of 10.
     * 
     * @param matrix Grid state where the next move will be played
     * @param playableCells playable cells on the grid
     * @return An int array where the first element is the y position on the grid and the second is x.
     */
    public static int[] nextMove(Pawn[][] matrix, int[][] playableCells) {
        ArrayList<int[]> coords = toCoords(playableCells);
        if (coords.size() > 0) {
            //Pair<Integer, Move> p;
            Pair<Double, Move> p;
            switch (Game.getDifficulty()) {
                default:
                    return coords.get((int) Math.round(Math.random() * (coords.size() - 1)));
                case 2:
                    if (coords.size() < 0) {
                        //p = miniMax(matrix, coords, 4, Game.getCurrentPlayer());
                        p = negaMax(matrix, coords, 6, Game.getCurrentPlayer(), Double.MIN_VALUE, Double.MAX_VALUE);
                        if (p.getValue() != null) {
                            return new int[]{p.getValue().getY(), p.getValue().getX()};
                        }
                    }
                    return null;

                case 3:
                    if (coords.size() < 0) {
                        //p = miniMax(matrix, coords, 6, Game.getCurrentPlayer());
                        p = negaMax(matrix, coords, 10, Game.getCurrentPlayer(), Double.MIN_VALUE, Double.MAX_VALUE);
                        if (p.getValue() != null) {
                            return new int[]{p.getValue().getY(), p.getValue().getX()};
                        }
                    }
                    return null;
            }
        }
        return null;
    }

    /**
     * Simple AI recursive AI to choose the best move
     *
     * @param matrix used to know the grid state
     * @param coords used to know the playable cells
     * @param depth used to stop the recursive algorithm
     * @param player the current player
     * @return a pair with the move made and the score associated (calculate
     * with the eval function)
     */
    public static Pair<Integer, Move> miniMax(Pawn[][] matrix, ArrayList<int[]> coords, int depth, Player player) {
        Player secondPlayer = player.equals(Game.getPlayer1()) ? Game.getPlayer2() : Game.getPlayer1();
        if (isMatrixFull(matrix) || depth == 0) {
            return new Pair(getScore(matrix, player), null);
        }
        Move bestMove = null;
        int bestScore;
        Pawn[][] tmp;
        if (player.equals(Game.getCurrentPlayer())) {
            bestScore = Integer.MIN_VALUE;
            for (int[] coord : coords) {
                tmp = onClickPawn(matrix, coord[1], coord[0]);
                Pair<Integer, Move> p = miniMax(tmp, toCoords(Move.playableCells(Pawn.toIntMatrix(tmp), player)), depth - 1, player);
                int score = p.getKey();
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = new Move(coord[1], coord[0], Pawn.toIntMatrix(matrix));
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int[] coord : coords) {
                tmp = onClickPawn(matrix, coord[1], coord[0]);
                Pair<Integer, Move> p = miniMax(tmp, toCoords(Move.playableCells(Pawn.toIntMatrix(tmp), player)), depth - 1, (player.equals(Game.getPlayer1()) ? Game.getPlayer2() : Game.getPlayer1()));
                int score = p.getKey();
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = new Move(coord[1], coord[0], Pawn.toIntMatrix(matrix));
                }
            }
        }
        return new Pair(bestScore, bestMove);
    }

    /**
     * Improve version of the minimax algorithm with alpha-bêta branch suppression
     * 
     * @param matrix used to know the grid state
     * @param coords used to know the playable cells
     * @param depth used to stop the recursive algorithm
     * @param player the current player
     * @param alpha minimum node to explore
     * @param beta maximum node to explore
     * @return a pair with the move made and the score associated (calculate
     * with the eval function)
     */
    public static Pair<Double, Move> negaMax(Pawn[][] matrix, ArrayList<int[]> coords, int depth, Player player, double alpha, double beta) {
        if (isMatrixFull(matrix) || depth <= 0) {
            return new Pair(eval(Pawn.toIntMatrix(matrix), player), null);
        }
        Move bestMove = null;
        Pawn[][] tmp;
        for (int[] coord : coords) {
            tmp = onClickPawn(matrix, coord[1], coord[0]);
            Pair<Double, Move> p = negaMax(tmp, toCoords(Move.playableCells(Pawn.toIntMatrix(tmp), player)), depth - 1, player, -beta, -alpha);
            Double score = p.getKey();
            if (score >= alpha) {
                alpha = score;
                bestMove = new Move(coord[1], coord[0], Pawn.toIntMatrix(matrix));
                if (alpha >= beta) {
                    break;
                }
            }
        }
        return new Pair(alpha, bestMove);
    }
/**
 * Test if the game is over
 * 
 * @param matrix grid to check
 * @return true if the matrix is full false otherwise
 */
    private static boolean isMatrixFull(Pawn[][] matrix) {
        for (Pawn[] matrix1 : matrix) {
            for (Pawn item : matrix1) {
                if (item == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Convert playable cells to an ArrayList of int[]. First element of an array is y position, second element is x. 
     * 
     * @param playableCells give the playable cell on a grid
     * @return ArrayList of int[].
     */
    private static ArrayList<int[]> toCoords(int[][] playableCells) {
        ArrayList<int[]> coords = new ArrayList();
        for (int y = 0; y < playableCells.length; y++) {
            for (int x = 0; x < playableCells[y].length; x++) {
                if (playableCells[y][x] == 1) {
                    int[] coord = new int[2];
                    coord[0] = y;
                    coord[1] = x;
                    coords.add(coord);
                }
            }
        }
        return (ArrayList) coords.clone();
    }

    /**
     * Use to know the score of a player on a grid. 
     * 
     * @param matrix the grid
     * @param p the player
     * @return Number of Pawn of the player on the grid
     */
    private static int getScore(Pawn[][] matrix, Player p) {
        int score = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != null) {
                    if (matrix[y][x].getPlayer().equals(p)) {
                        score++;
                    }
                }
            }
        }
        return score;
    }
    /**
     * Evaluation function of the grid.
     * 
     * @param grid grid to test.
     * @param currentP current player
     * @return a value for the current grid.
     */
    public static double eval(int[][] grid, Player currentP) {
        Player otherP = currentP.equals(Game.getPlayer1()) ? Game.getPlayer2() : Game.getPlayer1();
        int my_tiles = 0, opp_tiles = 0, i, j;
        double p = 0, c = 0, d = 0;

        int[][] V = new int[8][];
        
        // Tiles coefficients 
        V[0] = new int[]{20, -3, 11, 8, 8, 11, -3, 20};
        V[1] = new int[]{-3, -7, -4, 1, 1, -4, -7, -3};
        V[2] = new int[]{11, -4, 2, 2, 2, 2, -4, 11};
        V[3] = new int[]{8, 1, 2, -3, -3, 2, 1, 8};
        V[4] = new int[]{8, 1, 2, -3, -3, 2, 1, 8};
        V[5] = new int[]{11, -4, 2, 2, 2, 2, -4, 11};
        V[6] = new int[]{-3, -7, -4, 1, 1, -4, -7, -3};
        V[7] = new int[]{20, -3, 11, 8, 8, 11, -3, 20};

        // Piece difference, frontier disks and disk squares
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (grid[i][j] == currentP.getNumber()) {
                    d += V[i][j];
                    my_tiles++;
                } else if (grid[i][j] == otherP.getNumber()) {
                    d -= V[i][j];
                    opp_tiles++;
                }
            }
        }
        if (my_tiles > opp_tiles) {
            p = (100.0 * my_tiles) / (my_tiles + opp_tiles);
        } else if (my_tiles < opp_tiles) {
            p = -(100.0 * opp_tiles) / (my_tiles + opp_tiles);
        } else {
            p = 0;
        }
        
        // Corner occupancy
        my_tiles = opp_tiles = 0;
        if (grid[0][0] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[0][0] == otherP.getNumber()) {
            opp_tiles++;
        }
        if (grid[0][7] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[0][7] == otherP.getNumber()) {
            opp_tiles++;
        }
        if (grid[7][0] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[7][0] == otherP.getNumber()) {
            opp_tiles++;
        }
        if (grid[7][7] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[7][7] == otherP.getNumber()) {
            opp_tiles++;
        }
        c = 25 * (my_tiles - opp_tiles);
        // final weighted score
        double score = (10 * p) + (801.724 * c) + (10 * d);
        return score;
        
        
        
        /*
        Player otherP = currentP.equals(Game.getPlayer1()) ? Game.getPlayer2() : Game.getPlayer1();
        int my_tiles = 0, opp_tiles = 0, x, y, k, my_front_tiles = 0, opp_front_tiles = 0, i, j;
        double p = 0, c = 0, l = 0, m = 0, f = 0, d = 0;

        int X1[] = {-1, -1, 0, 1, 1, 1, 0, -1};
        int Y1[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int[][] V = new int[8][];

        V[0] = new int[]{20, -3, 11, 8, 8, 11, -3, 20};
        V[1] = new int[]{-3, -7, -4, 1, 1, -4, -7, -3};
        V[2] = new int[]{11, -4, 2, 2, 2, 2, -4, 11};
        V[3] = new int[]{8, 1, 2, -3, -3, 2, 1, 8};
        V[4] = new int[]{8, 1, 2, -3, -3, 2, 1, 8};
        V[5] = new int[]{11, -4, 2, 2, 2, 2, -4, 11};
        V[6] = new int[]{-3, -7, -4, 1, 1, -4, -7, -3};
        V[7] = new int[]{20, -3, 11, 8, 8, 11, -3, 20};

// Piece difference, frontier disks and disk squares
        for (y = 0; y < 8; y++) {
            for (x = 0; x < 8; x++) {
                if (grid[y][x] == currentP.getNumber()) {
                    d += V[y][x];
                    my_tiles++;
                } else if (grid[y][x] == otherP.getNumber()) {
                    d -= V[y][x];
                    opp_tiles++;
                }
                if (grid[y][x] != 0) {
                    for (k = 0; k < 8; k++) {
                        j = y + Y1[k];
                        i = x + X1[k];
                        if (j >= 0 && j < 8 && i >= 0 && i < 8 && grid[j][i] == 0) {
                            if (grid[y][x] == currentP.getNumber()) {
                                my_front_tiles++;
                            } else {
                                opp_front_tiles++;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (my_tiles > opp_tiles) {
            p = (100.0 * my_tiles) / (my_tiles + opp_tiles);
        } else if (my_tiles < opp_tiles) {
            p = -(100.0 * opp_tiles) / (my_tiles + opp_tiles);
        } else {
            p = 0;
        }

        if (my_front_tiles > opp_front_tiles) {
            f = -(100.0 * my_front_tiles) / (my_front_tiles + opp_front_tiles);
        } else if (my_front_tiles < opp_front_tiles) {
            f = (100.0 * opp_front_tiles) / (my_front_tiles + opp_front_tiles);
        } else {
            f = 0;
        }
// Corner occupancy
        my_tiles = opp_tiles = 0;
        if (grid[0][0] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[0][0] == otherP.getNumber()) {
            opp_tiles++;
        }
        if (grid[0][7] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[0][7] == otherP.getNumber()) {
            opp_tiles++;
        }
        if (grid[7][0] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[7][0] == otherP.getNumber()) {
            opp_tiles++;
        }
        if (grid[7][7] == currentP.getNumber()) {
            my_tiles++;
        } else if (grid[7][7] == otherP.getNumber()) {
            opp_tiles++;
        }
        c = 25 * (my_tiles - opp_tiles);
        
// Corner closeness
        my_tiles = opp_tiles = 0;
        if (grid[0][0] == 0) {
            if (grid[0][1] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[0][1] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[1][1] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[1][1] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[1][0] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[1][0] == otherP.getNumber()) {
                opp_tiles++;
            }
        }
        if (grid[0][7] == 0) {
            if (grid[0][6] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[0][6] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[1][6] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[1][6] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[1][7] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[1][7] == otherP.getNumber()) {
                opp_tiles++;
            }
        }
        if (grid[7][0] == 0) {
            if (grid[7][1] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[7][1] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[6][1] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[6][1] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[6][0] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[6][0] == otherP.getNumber()) {
                opp_tiles++;
            }
        }
        if (grid[7][7] == 0) {
            if (grid[6][7] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[6][7] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[6][6] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[6][6] == otherP.getNumber()) {
                opp_tiles++;
            }
            if (grid[7][6] == currentP.getNumber()) {
                my_tiles++;
            } else if (grid[7][6] == otherP.getNumber()) {
                opp_tiles++;
            }
        }
        l = -12.5 * (my_tiles - opp_tiles);

// Mobility
        my_tiles = getScore(Pawn.toMatrix(grid), currentP);
        opp_tiles = getScore(Pawn.toMatrix(grid), otherP);
        if (my_tiles > opp_tiles) {
            m = (100.0 * my_tiles) / (my_tiles + opp_tiles);
        } else if (my_tiles < opp_tiles) {
            m = -(100.0 * opp_tiles) / (my_tiles + opp_tiles);
        } else {
            m = 0;
        }
         
// final weighted score
        double score = (10 * p) + (801.724 * c) + (382.026 * l) + (78.922 * m) + (74.396 * f) + (10 * d);
        return score;*/
    }

}

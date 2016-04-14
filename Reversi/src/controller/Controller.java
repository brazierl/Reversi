/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import graphic.Pawn;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;
import reversi.Game;
import rules.Move;

/**
 *
 * @author p1509283
 */
public class Controller {

    public static Pawn[][] onClickPawn(Pawn[][] matrix, int x1, int y1) {
        return Pawn.toMatrix(Move.returnMatrixMove(Pawn.toIntMatrix(matrix), y1, x1, Game.getCurrentPlayer()));
    }

    public static int[] nextMove(Pawn[][] matrix, int[][] playableCells) {
        ArrayList<int[]> coords = toCoords(playableCells);
        if (coords.size() > 0) {
            switch (Game.getDifficulty()) {
                default:
                    return coords.get((int) Math.round(Math.random() * (coords.size() - 1)));
                case 2:
                    Pair<Integer,Move> p = miniMax(matrix, coords, 4, Game.getCurrentPlayer());
                    if(p.getValue()!=null)
                        return new int[] {p.getValue().getY(),p.getValue().getX()};
                    else
                        return null;
                case 3:
                    return null;
            }
        }
        return null;
    }

    public static Pair<Integer,Move> miniMax(Pawn[][] matrix, ArrayList<int[]> coords, int depth, Player player) {
        if (isMatrixFull(matrix) || depth==0) {
            return new Pair(getScore(matrix, player), null);
        }
        Move bestMove = null;
        int bestScore;
        Pawn[][] tmp;
        if (player==Game.getCurrentPlayer()) { //=Programme
            bestScore = Integer.MIN_VALUE;
            for (int[] coord : coords) {
                tmp = onClickPawn(matrix, coord[1], coord[0]);
                Pair<Integer,Move> p = miniMax(tmp.clone(), toCoords(Move.playableCells(Pawn.toIntMatrix(tmp),player)),depth - 1, player);
                int score = p.getKey();
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = new Move(coord[1], coord[0], Pawn.toIntMatrix(matrix));
                }
            }
        } else { //type MIN = adversaire
            bestScore = Integer.MAX_VALUE;
            for (int[] coord : coords) {
                tmp = onClickPawn(matrix, coord[1], coord[0]);
                Pair<Integer,Move> p = miniMax(tmp.clone(), toCoords(Move.playableCells(Pawn.toIntMatrix(tmp),player)),depth - 1, player);
                int score = p.getKey();
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = new Move(coord[1], coord[0], Pawn.toIntMatrix(matrix));
                }
            }
        }
        return new Pair(bestScore, bestMove);
    }
    
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
    
    private static ArrayList<int[]> toCoords(int[][] playableCells){
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
        return (ArrayList)coords.clone();
    }
    
    private static int getScore(Pawn[][] matrix, Player p){
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
    
}

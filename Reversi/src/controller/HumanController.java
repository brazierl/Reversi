/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import graphic.Pawn;
import reversi.Game;
import rules.RulesChecker;

/**
 *
 * @author p1509283
 */
public class HumanController extends Controller {
 
    public static Pawn[][] onClickPawn(Pawn[][] matrix, int x1, int y1) {
        return Pawn.toMatrix(RulesChecker.returnMatrixMove(Pawn.toIntMatrix(matrix), y1, x1, Game.getCurrentPlayer()));
    }
}

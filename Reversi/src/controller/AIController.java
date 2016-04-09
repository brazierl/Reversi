/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import graphic.Pawn;
import java.util.ArrayList;
import reversi.Game;
import rules.RulesChecker;

/**
 *
 * @author p1509283
 */
public class AIController extends Controller{

    public static int[] nextMove(Pawn[][] matrix, int[][] playableCells) {
        ArrayList<int[]> coords = new ArrayList();
        for(int y = 0; y<playableCells.length; y++){
            for(int x = 0; x<playableCells[y].length; x++){
                if(playableCells[y][x]==1){
                    int[] coord = new int[2];
                    coord[0]=y;
                    coord[1]=x;
                    coords.add(coord);
                }
            }
        }
        switch(Game.getDifficulty()){
            default: 
                    return coords.get((int)Math.round(Math.random()*(coords.size()-1)));
            case 2:
                return null;
            case 3 :
                return null;
        }
    }
    
    
    
}

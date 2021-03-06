/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphic;

import javafx.scene.paint.Color;

/**
 * Enum to manage the color of the Pawn
 * @author p1509283
 */
public enum Colors {
    WHITE(1,Color.WHITE),BLACK(2,Color.BLACK);
    
    private final Color graphicColor;
    private final int number;
    
    Colors(int i,Color c){
        this.number = i;
        this.graphicColor = c;
    }

    public Color getGraphicColor() {
        return graphicColor;
    }

    public int getNumber() {
        return number;
    }
    
}

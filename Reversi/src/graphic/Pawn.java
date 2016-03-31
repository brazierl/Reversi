/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphic;

import com.sun.prism.paint.Color;
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
    
    
}

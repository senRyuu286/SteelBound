/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import java.awt.event.MouseEvent;
import main.game;
import ui.menuButton;

/**
 *
 * @author Justin ramas
 */

public class state {
    
    protected game game;
    
    public state(game game) {
        this.game = game;
    }
    
    public boolean isIn(MouseEvent e, menuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }
    
    public game getGame() {
        return game;
    }
}

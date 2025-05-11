/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import inputs.*;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import static main.game.GAME_HEIGHT;
import static main.game.GAME_WIDTH;

/**
 *
 * @author Justin ramas
 */

public class gamePanel extends JPanel {
    
    private mouseInputs mouseInputs;
    private game game;
    
    public gamePanel(game game) {
        setFocusable(true);
        mouseInputs = new mouseInputs(this);
        this.game = game;
        
        setPanelSize();
        addKeyListener(new keyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        
        System.out.println("SIZE: " + GAME_WIDTH + " + " + GAME_HEIGHT);
    }
    
    public void updateGame() {
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        game.render(g);
    }   

    public game getGame() {
        return game;
    }
}

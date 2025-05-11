/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import entities.player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import levels.levelManager;
import main.game;

/**
 *
 * @author Justin ramas
 */
public class playing extends state implements stateMethods {
    private player player;
    private levelManager levelManager;
    
    public playing (game game) {
        super(game);
        initClasses();
    }
    
    private void initClasses() {
        this.levelManager = new levelManager(game);
        this.player = new player(0, 0,(int)(120 * game.SCALE), (int)(65 * game.SCALE));
        player.loadlvlData(levelManager.getCurrentLevel().getLevelData());
    }
    
    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    
    public player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttack(true);
        }        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseRelease(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJump(true);
                break; 
            case KeyEvent.VK_A:  
                player.setLeft(true);
                break; 
//            case KeyEvent.VK_S:
//                gamePanel.getGame().getPlayer().setDown(true);
//                break; 
            case KeyEvent.VK_D:
                player.setRight(true);
                break;  
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;  
            case KeyEvent.VK_BACK_SPACE:
                gameStates.state = gameStates.MENU;
                break;
        }       
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJump(false);
                break; 
            case KeyEvent.VK_A:  
                player.setLeft(false);
                break; 
//            case KeyEvent.VK_S:
//                gamePanel.getGame().getPlayer().setDown(false);
//                break; 
            case KeyEvent.VK_D:
                player.setRight(false);
                break;    
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;      
        }             
    }
}

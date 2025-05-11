/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;

import gamestates.gameStates;
import static gamestates.gameStates.MENU;
import static gamestates.gameStates.PLAYING;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.gamePanel;

/**
 *
 * @author Justin ramas
 */

public class mouseInputs implements MouseListener, MouseMotionListener{

    private gamePanel gamePanel;
    
    public mouseInputs(gamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(gameStates.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(gameStates.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            default:
                break;
        }          
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(gameStates.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseRelease(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mouseRelease(e);
                break;
            default:
                break;
        }       
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(gameStates.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            default:
                break;
        }       
    }
}

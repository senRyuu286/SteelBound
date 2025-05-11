/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.game;
import ui.menuButton;
import utils.loadNsave;

/**
 *
 * @author Justin ramas
 */

public class menu extends state implements stateMethods {

    private menuButton[] buttons = new menuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;
    
    public menu(game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    @Override
    public void update() {
        for (menuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        
        Color bgColor = new Color(24, 24, 27);
        
        g.setColor(bgColor);
        g.fillRect(0, 0, 1248, 672);
        
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        
        for (menuButton mb : buttons) {
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (menuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseRelease(MouseEvent e) {
        for (menuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                    break;
                }
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (menuButton mb : buttons) {
            mb.setMouseOver(false);
        }
        
        for (menuButton mb : buttons) {
            if(isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStates.state = gameStates.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void loadButtons() {
        buttons[0] = new menuButton(game.GAME_WIDTH / 2, (int)(150 * game.SCALE), 0, gameStates.PLAYING);
        buttons[1] = new menuButton(game.GAME_WIDTH / 2, (int)(220 * game.SCALE), 1, gameStates.OPTIONS);
        buttons[2] = new menuButton(game.GAME_WIDTH / 2, (int)(290 * game.SCALE), 2, gameStates.QUIT);
    }

    private void resetButtons() {
        for (menuButton mb : buttons) {
            mb.resetBools();
        }
    }

    private void loadBackground() {
        backgroundImg = loadNsave.getSpriteAtlas("/ui/menu_bg.png");
        menuWidth = (int)(backgroundImg.getWidth() * game.SCALE);
        menuHeight = (int)(backgroundImg.getWidth() * game.SCALE);
        
        menuX = game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int)(45 * game.SCALE);
    }
}

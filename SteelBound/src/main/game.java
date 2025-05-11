/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gamestates.gameStates;
import static gamestates.gameStates.MENU;
import static gamestates.gameStates.PLAYING;
import gamestates.menu;
import gamestates.playing;
import java.awt.Graphics;

/**
 *
 * @author Justin ramas
 */

public class game implements Runnable {
    
    private gameWindow gameWindow;
    private gamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    
    private playing playing;
    private menu menu;
    
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    
    public game() {
        initClasses();
        
        gamePanel = new gamePanel(this);
        gameWindow = new gameWindow(gamePanel);
        gamePanel.requestFocus();
        
        startGameLoop();
    }
    
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update() {
        switch(gameStates.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
                System.exit(0);
                break;    
            default:
                break;
        }
    }
    
    public void render(Graphics g) {
        switch(gameStates.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        
        while(true) {
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
                    
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    private void initClasses() {
        menu = new menu(this);
        playing = new playing(this);
    }
    
    public void windowFocusLost() {
        if (gameStates.state == gameStates.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }
    
    public menu getMenu() {
        return menu;
    }
    
    public playing getPlaying() {
        return playing;
    }
}

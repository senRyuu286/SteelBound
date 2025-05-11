/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import gamestates.gameStates;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utils.constants.UI.buttons.*;
import utils.loadNsave;

/**
 *
 * @author Justin ramas
 */
public class menuButton {
    
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    
    private gameStates state;
    private BufferedImage[] imgs;
    
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    
    public menuButton(int xPos, int yPos, int rowIndex, gameStates state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        this.rowIndex = rowIndex;
        loadImgs();
        
        initBounds();
    }
    
    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = loadNsave.getSpriteAtlas("/ui/ButtonAtlas.png");
    
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }
    
    public void draw (Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }
    
    public void update () {
        index = 0;
        
        if (mouseOver) {
            index = 1;
        }
        
        if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }
    
    public Rectangle getBounds() {
        return bounds;
    }
    
    public void applyGamestate() {
        gameStates.state = state;
    }
    
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}

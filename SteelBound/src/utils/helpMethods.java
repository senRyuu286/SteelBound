/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.geom.Rectangle2D;
import main.game;

/**
 *
 * @author Justin ramas
 */

public class helpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] leveldata) {
        if(!isSolid(x, y, leveldata)) {
            if (!isSolid(x+width, y + height, leveldata)) {
                if(!isSolid(x + width, y, leveldata)) {
                    if(!isSolid(x, y + height, leveldata)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean isSolid(float x, float y, int[][] leveldata) {
        if (x < 0 || x >= game.GAME_WIDTH) {
            return true;
        }
        
        if (y < 0 || y >= game.GAME_HEIGHT) {
            return true;
        }
        
        float xIndex = x / game.TILES_SIZE;
        float yIndex = y / game.TILES_SIZE;
        
        int value = leveldata[(int)yIndex][(int)xIndex];
        
        if (value > 48 || value < 0 || !(value >= 0 && value <= 6 || value == 12 || value == 13)) {
            return true;
        }

        return false;
    }
    
    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int)(hitbox.x / game.TILES_SIZE);
        
        if (xSpeed > 0) {
            int tileXPos = currentTile * game.TILES_SIZE;
            int xOffset = (int)(game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            return currentTile * game.TILES_SIZE;
        }
    }
    
    public static float getEntityPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int)(hitbox.y / game.TILES_SIZE);
        
        if (airSpeed > 0) {
            int tileYPos = currentTile * game.TILES_SIZE;
            int yOffset = (int)(game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            return currentTile * game.TILES_SIZE;
        }
    }
    
    public static boolean isEntityOnFloor (Rectangle2D.Float hitbox, int[][] leveldata) {
        if (!isSolid(hitbox.x, hitbox.y+hitbox.height + 1, leveldata)) {
            if (!isSolid(hitbox.x+hitbox.width, hitbox.y + hitbox.height + 1, leveldata)) {
                return false;
            }
        }
        return true;
    }
}



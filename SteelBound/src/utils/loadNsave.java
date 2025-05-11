/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.game;

/**
 *
 * @author Justin ramas
 */

public class loadNsave {
    
    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        
        InputStream is = loadNsave.class.getResourceAsStream(filename);
        
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    
    public static BufferedImage[] getPlayerAtlas() {
        BufferedImage[] img = new BufferedImage[10];
        
        String[] fileLoc = new String[10];
        
        fileLoc[0] = "/knight/used/_Idle.png";
        fileLoc[1] = "/knight/used/_Run.png";
        fileLoc[2] = "/knight/used/_Jump.png";
        fileLoc[3] = "/knight/used/_Fall.png";
        fileLoc[4] = "/knight/used/_Hit.png";
        fileLoc[5] = "/knight/used/_DeathNoMovement.png";
        fileLoc[6] = "/knight/used/_AttackNoMovement.png";
        fileLoc[7] = "/knight/used/_Attack2NoMovement.png";
        
        for (int i = 0; i < 8; i++) {
            InputStream is = loadNsave.class.getResourceAsStream(fileLoc[i]);
            
            try {
                img[i] = ImageIO.read(is);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return img;
    }
    
    public static int[][] getLevelData() {
        int[][] levelData = new int[game.TILES_IN_HEIGHT][game.TILES_IN_WIDTH];
        
        BufferedImage img = getSpriteAtlas("/tiles/level_one_data.png");
        
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                
                if (value >= 49) {
                    value = 0;
                }
                levelData[j][i] = value;
            }
        }
        return levelData;
    }
}

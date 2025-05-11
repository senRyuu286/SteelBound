/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.game;
import utils.loadNsave;

/**
 *
 * @author Justin ramas
 */

public class levelManager {
    
    private game game;
    private BufferedImage[] levelSprite;
    private level levelOne;
    
    public levelManager(game game) {
        this.game = game;       
        importOutsideSprites();
        levelOne = new level(loadNsave.getLevelData());
    }
    

    private void importOutsideSprites() {
        levelSprite = new BufferedImage[49];
        BufferedImage img = loadNsave.getSpriteAtlas("/tiles/tileSheet.png");
        
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 7; i++) {
                int index = j*7 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }    
    
    public void draw(Graphics g) {
        
        for (int j = 0; j < game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i*game.TILES_SIZE, j*game.TILES_SIZE, game.TILES_SIZE, game.TILES_SIZE, null);
            }
        }
    }
    
    public void update() {
        
    }
    
    public level getCurrentLevel() {
        return levelOne;
    }
}

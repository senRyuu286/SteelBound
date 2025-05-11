/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

/**
 *
 * @author Justin ramas
 */
public class level {
    
    private int[][] leveldata;
    
    public level(int[][] leveldata) {
        this.leveldata = leveldata;
    }
    
    public int getSpriteIndex(int x, int y) {
        return leveldata[y][x];
    }
    
    public int[][] getLevelData() {
        return leveldata;
    }
}

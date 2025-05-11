/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import main.game;
import static utils.constants.playerConstants.*;

/**
 *
 * @author Justin ramas
 */

public class constants {
    
    public static class UI {
        public static class buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * game.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * game.SCALE);
        }
    }
    
    public static class directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        
    }
    
    public static class playerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALL = 3;
        public static final int HIT = 4;
        public static final int DEATH = 5;
        public static final int ATTACK1 = 6;
        public static final int ATTACK2 = 7;
    }
    
    public static int getSpriteAmount (int player_action) {
        int i = 1;
        
        switch (player_action) {
            case IDLE: i = 10; break;
            case RUNNING: i = 10; break;
            case JUMP: i = 3; break;
            case FALL: i = 3; break;
            case HIT: i = 1; break;
            case DEATH: i = 10; break;
            case ATTACK1: i = 4; break;     
            case ATTACK2: i = 6; break;    
        }
        
        return i;
    }
}

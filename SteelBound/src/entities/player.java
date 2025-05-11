/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.game;
import static utils.constants.getSpriteAmount;
import static utils.constants.playerConstants.*;
import static utils.helpMethods.canMoveHere;
import static utils.helpMethods.getEntityPosUnderRoofOrAboveFloor;
import static utils.helpMethods.getEntityXPosNextToWall;
import static utils.helpMethods.isEntityOnFloor;
import utils.loadNsave;

/**
 *
 * @author Justin ramas
 */
public class player extends Entity{
    
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = FALL;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 1.0f * game.SCALE;
    private int[][] leveldata;
    private float xDrawOffset = 44 * game.SCALE;
    private float yDrawOffset = 35 * game.SCALE;
    private float airSpeed = 0f;
    private float gravity = 0.04f * game.SCALE;
    private float jumpSpeed = -2.25f * game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * game.SCALE;
    private boolean inAir = false;
    
    public player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, (int)(20 * game.SCALE), (int)(28 * game.SCALE));
        
    }
    
    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
//        drawHitbox(g);
    }
    
    private void loadAnimations() {
            BufferedImage[] img = new BufferedImage[10];
            
            img = loadNsave.getPlayerAtlas();
            
            int i, j = 0;
            animations = new BufferedImage[10][10];

            for (j = 0; j < 8; j++) {
                for (i = 0; i < getSpriteAmount(j); i++) {
                    animations[j][i] = img[j].getSubimage(i*120, 0, 120, 80);
                }
            }
    }
    
    public void loadlvlData(int[][] leveldata) {
        this.leveldata = leveldata;
        if (!isEntityOnFloor(hitbox, leveldata)) {
            inAir = true;
        }
    }
    
    private void updateAnimationTick() {
        aniTick++;
        
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        
        int startAni = playerAction;
        
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        
        if (inAir) {
            if (airSpeed < 0) {
                playerAction = JUMP;
            } else {
                playerAction = FALL;
            }
        }
        
        if (attacking) {
            playerAction = ATTACK1;
        }
        
        if (startAni != playerAction) {
            resetAniTick();
        }
    }
    
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;
        
        if (jump) {
            jump();
        }
        
        if (!left && !right && !inAir) {
            return;
        }
        
        float xSpeed = 0, ySpeed = 0;
        
        if (left) {
            xSpeed -= playerSpeed;
        }
        
        if (right) {
            xSpeed += playerSpeed;
        }
        
        if (!inAir) {
            if (!isEntityOnFloor(hitbox, leveldata)) {
                inAir = true;
            }
        }
        
        if (inAir) {
            
            if (canMoveHere (hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, leveldata)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updatexPos(xSpeed);
            } else {
                hitbox.y = getEntityPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updatexPos(xSpeed);
            }
                
        } else {
            updatexPos(xSpeed);
        }
        
        moving = true;
        
        
//        if (canMoveHere(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height, leveldata)) {
//            hitbox.x += xSpeed;
//            hitbox.y += ySpeed;
//            moving = true;
//        }
    }
    
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
    
    public void setAttack(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void updatexPos(float xSpeed) {
        if (canMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, leveldata)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
        }    
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void jump() {
        if (inAir) {
            return;
        }
            
        inAir = true;
        airSpeed = jumpSpeed;
    }
    
    public void setJump(boolean jump) {
        this.jump = jump;
    }
}


package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    //GLIDING
    private boolean gliding;

    //player stuff
    private int health;
    private int maxHealth;
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;

    //fireball
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    //private ArrayList<FireBall> fireballs;

    //scratch
    private boolean scratching;
    private int scratchRange;
    private int ScratchDamage;


    //animtaions
    private ArrayList<BufferedImage[]> sprites;

    private final int[] numFrames = {
            2, 8, 1, 2, 4, 2, 5
    };

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6;

    public Player(TileMap tm) {
        super(tm);
        //read from spritesheet
        width = 30;
        height = 30;

        //real
        cwidth = 20;
        cheight = 20;
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;

        facingRight = true;
        health = maxHealth = 5;
        fire = maxFire = 2500;
        fireCost = 200;
        fireBallDamage = 5;
        //fireBalls = new ArrayList<FireBall>();


        scratchDamage = 8;
        scratchRange = 40;

        //load sprites
        try {
            BufferedImage spriteSheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Sprites/Player/playersprites.gif"
                    )
            );
            for (int i = 0; i < 7; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != 6) {
                        bi[j] = spriteSheet.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );
                    } else {
                        bi[j] = spriteSheet.getSubimage(
                                j * width * 2,
                                i * height,
                                width,
                                height
                        );
                    }

                }
                sprites.add(bi);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getFire() {
        return fire;
    }

    public int getMaxFire() {
        return maxFire;
    }

    public void setFiring() {
        firing = true;
    }

    public void setScratching() {
        scratching = true;
    }

    public void setGliding(boolean b) {
        gliding = b;
    }

    public void update() {
        //??
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //set animation
        if (scratching) {
            if (currentAction != SCRATCHING) {
                currentAction = SCRATCHING;
                animation.setFrame(sprites.get(SCRATCHING));
                //??
                animation.setDelay(50);
                //width of the sprite
                width = 60;
            }
        } else if (firing) {
            if (currentAction != FIREBALL) {
                currentAction = FIREBALL;
                animation.setFrame(sprites.get(FIREBALL));
                animation.setDelay(100);
                width = 30;
            }
        }
        //dy is direction ?
        //falling animations
        else if (dy > 0) {
            if (currentAction != GLIDING) {
                currentAction = GLIDING;
                animation.setFrame(sprites.get(GLIDING));
                //??
                animation.setDelay(100);
                //width of the sprite
                width = 30;
            } else if (currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrame(sprites.get(FALLING));
                animation.setDelay(100);
                width = 30;
            }

        }
        //jumping
        //if -ve dy is added to y things would go up
        else if (dy < 0) {
            if (currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrame(sprites.get(JUMPING));
                //since only 1 sprite??
                animation.setDelay(-1);
                width = 30;
            }
        }

        else if (left || right) {
            if (currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrame(sprites.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }
        }
        else{
            //Idle frames
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrame(sprites.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }
        }
        animation.update();

        //???
        //dont want player to move when he is attacking
        if(currentAction != SCRATCHING && currentAction != FIREBALL){
            if(right) facingRight = true;
            if(left) facingRight = false;
        }
    }

    public void draw(Graphics2D g){
        //?? should be called in every map object why ?
        setMapPostion();

        //draw player
        if(flinching){
            long elapsed = (System.nanoTime() - flinchTimer)/1000000;
            //why ??
            //gives an appearance of blinking every 100 miliseconds
            if(elapsed/100 %2 == 0){
                return;
            }
        }
        if(facingRight){
            g.drawImage(
                    animation.getImage(),
                    (int)(x + xmap - width/2),
                    (int) (y + ymap - height/2),
                    null);
        }
        else{
            //drawing a flipped sprite
            g.drawImage(
                    animation.getImage(),
                    (int)(x + xmap - width/2 + width),
                    (int) (y + ymap - height/2),
                    -width,
                    height,
                    null);
        }
    }

    private void getNextPosition() {
        //movement
        //dont go beyond maximum speed
        //dx is the delta distance ?? -- sounds reasonable
        if(left){
            dx -= moveSpeed;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }
        else{
            //?? -- if not left or right
            // left and right above have some meaning?
            if(dx > 0){
                dx -= stopSpeed;
                if(dx < 0){
                    dx = 0;
                }
            }
            else if(dx < 0){
                dx += stopSpeed;
                if(dx > 0){
                    dx = 0;
                }
            }
        }
        return nextPosition;
    }
}

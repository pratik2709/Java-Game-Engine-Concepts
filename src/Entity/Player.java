package Entity;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject{

    //GLIDING
    private boolean gliding;

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

    public Player(TileMap tm){
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
        try{
            BufferedImage spriteSheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Sprites/Player/playersprites.gif"
                    )
            );
            for(int i = 0; i < 7; i++){
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for(int j = 0; j < numFrames[i]; j++){
                    if(i != 6){
                        bi[j] = spriteSheet.getSubimage(
                                j*width,
                                i*height,
                                width,
                                height
                        );
                    }
                    else{
                        bi[j] = spriteSheet.getSubimage(
                                j*width*2,
                                i*height,
                                width,
                                height
                        );
                    }

                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

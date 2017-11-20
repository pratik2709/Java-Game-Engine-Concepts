package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FireBall extends MapObject{

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    //animation that plays when it hits
    private BufferedImage[] hitSprites;

    public FireBall(TileMap tm, boolean right) {
        super(tm);
        moveSpeed = 3.8;
        //going right
        if(right) dx = moveSpeed;
        else dx = - moveSpeed;

        width =30;
        height =30;
        cheight =14;
        cwidth = 14;

        //load sprites
        try{
            BufferedImage spriteSheet = ImageIO.read(
                    getClass().getResourceAsStream("/Sprites/Player/fireball.gif")
            );
            sprites = new BufferedImage[4];
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spriteSheet.getSubimage(
                        i*width,
                        0,
                        width,
                        height
                        );
            }
            hitSprites = new BufferedImage[3];
            for(int i = 0; i < hitSprites.length; i++){
                hitSprites[i] = spriteSheet.getSubimage(
                        i*width,
                        height,
                        width,
                        height
                        );
            }
            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(70);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //has the fireball has hit something
    public void setHit(){
        if(hit) return;
        hit = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        //stop moving
        dx = 0;
    }

    //should you take it out of the game ?
    public boolean shouldRemove(){
        return remove;
    }

    public void update(){
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        animation.update();

        if(dx == 0 && !hit){
            setHit();
        }

        //remove from the screen
        if(hit && animation.hasPlayedOnce()){
            remove = true;
        }
    }

    public void draw(Graphics2D g){
        setMapPostion();
        super.draw(g);
    }


}

package Entity;

import TileMap.TileMap;

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

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

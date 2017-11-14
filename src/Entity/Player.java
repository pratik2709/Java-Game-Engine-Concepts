package Entity;
import TileMap.TileMap;
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


    }
}

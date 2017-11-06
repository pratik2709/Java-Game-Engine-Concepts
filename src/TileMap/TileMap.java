package TileMap;

import Main.Game;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class TileMap {
    private int x;
    private int y;

    //bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    //map attributes
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    //tilset
    private BufferedImage tileset;
    private Tile[][] tiles;
    private int numOfTilesAcross;


    //drawing
    private int numRowOffset;
    private int numColOffset;
    private int numOfRowsToDraw;
    private int numOfColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numOfRowsToDraw = GamePanel.HEIGHT / tileSize;
        numOfColsToDraw = GamePanel.WIDTH / tileSize;
        double tween = 0.07;
    }

    public void loadTiles(String s) {
        try {
            ImageIO.read(getClass().getResourceAsStream(s));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void loadMap(String s) {
        try {
            ImageIO.read(getClass().getResourceAsStream(s));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}

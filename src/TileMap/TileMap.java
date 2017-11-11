package TileMap;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            numOfTilesAcross = tileset.getWidth() / tileSize;
            //2d array with 2 rows and n number of cols
            tiles = new Tile[2][numOfTilesAcross];

            BufferedImage subImage;
            for (int col = 0; col < numOfTilesAcross; col++) {
                subImage = tileset.getSubimage(
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize
                );
                //1st row of the matrix
                tiles[0][col] = new Tile(subImage, Tile.NORMAL);
                subImage = tileset.getSubimage(
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize);

                //2nd row of matrix
                tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void loadMap(String s) {
        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numCols * tileSize;

            //remove whitespace
            //??
            String delimiters = "//s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for (int cols = 0; cols < numCols; cols++) {
                    map[row][cols] = Integer.parseInt(tokens[cols]);
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

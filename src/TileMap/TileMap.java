package TileMap;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
    private int rowOffset;
    private int colOffset;
    private int numOfRowsToDraw;
    private int numOfColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numOfRowsToDraw = GamePanel.HEIGHT / tileSize;
        numOfColsToDraw = GamePanel.WIDTH / tileSize;
        double tween = 0.07;
    }

    //each tile is an image which is being loaded in a 2D array
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
            height = numRows * tileSize;

            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;
            //remove whitespace
            //??
            String delimiters = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for (int cols = 0; cols < numCols; cols++) {
                    map[row][cols] = Integer.parseInt(tokens[cols]);
                }
            }
//            System.out.println(Arrays.deepToString(map));


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getTileSize(){
        return tileSize;
    }

    public int getx(){
        return (int)x;
    }

    public int gety(){
        return (int)y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getNumRows() { return numRows; }
    public int getNumCols() { return numCols; }

    //???
    public int getType(int row, int col){
        int rc = map[row][col];
        int r = rc/ numOfTilesAcross;
        int c = rc% numOfTilesAcross;
        return tiles[r][c].getType();
    }

    public void setPosition(double x, double y){
        this.x += x;
        this.y += y;

        fixbounds();

        //??
        //which part of the screen to draw
        //what part of your world is visible
        colOffset = (int)-this.x/tileSize;
        rowOffset = (int)-this.y/tileSize;
    }

    //xmin and others not initialized
    private void fixbounds() {
        if(x < xmin) x = xmin;
        if(x > xmax) x = xmax;
        if(y < ymin) y = ymin;
        if(y > ymax) y = ymax;
    }

    public void draw(Graphics2D g){
        //?
        for(int row = rowOffset; row < rowOffset + numOfRowsToDraw; row++){
            for(int col = colOffset; col < colOffset + numOfColsToDraw; col++){

                if(col >= numCols) break;

                if(map[row][col] == 0) continue;

                //??
                int rc = map[row][col];
                int r = rc/ numOfTilesAcross;
                int c = rc% numOfTilesAcross;

                g.drawImage(tiles[r][c].getImage(),
                        (int)x + col*tileSize,
                        (int)y + row* tileSize,
                        null);

            }
        }
    }


}

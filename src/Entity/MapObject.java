package Entity;

import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Tile;

import java.awt.*;

public abstract class MapObject {

    //tile stuff
    //protected so subclasses can see
    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;

    //position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    //dimensions
    protected int width;
    protected int height;

    //collision box
    protected int cwidth;
    protected int cheight;

    //collision
    protected int currRow;
    protected int currCol;
    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;
    protected boolean bottomLeft;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomRight;

    //animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    //where should the sprite be facing
    protected boolean facingRight;

    //movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    //movement attributes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public boolean intersects(MapObject o) {
        /*
         __
        |  |
        |__|__
           |  |
           |__|

       If you're standing at x = 30 with cwidth = 20, then your right edge should be 39. (30 + (20 / 2) - 1)﻿

        */

        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return new Rectangle(
                (int) x - cwidth,
                (int) y - cheight,
                cwidth,
                cheight
        );
    }

    public void calculateCorners(double x, double y) {
        //since x is the center of the object
        int leftTile = (int) (x - cwidth / 2) / tileSize;
        //no idea about -1
        //because of 0 to 9
        int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cheight / 2) / tileSize;
        int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;

        if (topTile <= 0 || bottomTile >= tileMap.getNumRows() || leftTile <= 0
                || rightTile >= tileMap.getNumCols()) {
            topLeft = topRight = bottomLeft = bottomRight;
            return;
        }

        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int br = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;
    }

    public void checkTileMapCollision() {
        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        //going in the y direction
        //4 corners method for calculating collision
        calculateCorners(x, ydest);

        if (dy < 0) {
            if (topLeft || topRight) {
                dy = 0;
                //right below the obstacle tile
                ytemp = currRow * tileSize + cheight / 2;

            } else {
                // minus up??? not down
                ytemp += dy;
            }
        }
        if (dy > 0) {
            if (bottomLeft || bottomRight) {
                dy = 0;
                falling = false;
                //1 above the tile after landing
                ytemp = (currRow + 1) * tileSize - cheight / 2;
            } else {
                ytemp += dy;
            }
        }


        //going in  xdirection
        calculateCorners(xdest, y);

        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                //just left
                xtemp = currCol * tileSize + cwidth / 2;

            } else {
                xtemp += dx;
            }
        }
        if (dx > 0) {
            if (topRight || bottomRight) {
                dx = 0;
                falling = false;
                //just right
                xtemp = (currRow + 1) * tileSize - cwidth / 2;
            } else {
                xtemp += dx;
            }
        }

        if (!falling) {
            calculateCorners(x, ydest + 1);
            if (!bottomLeft && !bottomRight) {
                //not standing on solid ground
                falling = true;
            }
        }
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCWidth() {
        return cwidth;
    }

    public int getCHeight() {
        return cheight;
    }

    public void setPosition(double x, double y) {
        //global position
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //important: global and local
    public void setMapPostion() {
        // local position
        //as player moves right , map will move left and xmap goes down
        xmap = tileMap.getx();
        ymap = tileMap.gety();
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }

    public boolean notOnScreen() {
        return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH ||
                y + ymap + height < 0 || y + ymap - height > GamePanel.HEIGHT;
    }

}

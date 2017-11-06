package TileMap;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    private BufferedImage image;

    private int x;
    private int y;
    private double dx;
    private double dy;
    private int moveScale;



    public Background(String s, int ms) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setPosition(int x, int y) {
        this.x = (x * moveScale)%GamePanel.WIDTH;
        this.y = (y * moveScale)%GamePanel.HEIGHT;
    }


    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        System.out.println(x);
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, null);
        if (x < 0) {
            g.drawImage(
                    image,
                    x + GamePanel.WIDTH,
                    y,
                    null
            );
        } else if (x > 0) {
            g.drawImage(
                    image,
                    x - GamePanel.WIDTH,
                    y,
                    null
            );
        }
    }
}

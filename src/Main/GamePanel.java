package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
//WTF
import java.awt.event.*;


public class GamePanel extends JPanel implements Runnable, KeyListener{
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    //game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    //canvas
    private BufferedImage image;
    private Graphics2D g;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }

    //what does this do ??
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void run(){
        init();
        //game loop
        while (running){
            update();
            draw();
            drawToScreen();
        }
    }

    private void drawToScreen() {
        // draw on a component ?
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        //dispose graphics context
        g2.dispose();
    }

    private void update() {
    }

    private void draw() {

    }

    public void init(){
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // ??
        g = (Graphics2D) g;
        running = true;
    }



    public void keyTyped(keyEvent key){}
    public void keyPressed(keyEvent key){}
    public void keyReleased(keyEvent key){}



}

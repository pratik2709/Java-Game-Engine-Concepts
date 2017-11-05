package Main;

import GameState.GameStateManager;

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

    //game state manager
    private GameStateManager gsm;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
        gsm = new GameStateManager();
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

        long start;
        long elapsed;
        long wait;

        //game loop
        while (true){
            start = System.nanoTime();

//            update();
            draw();
            drawToScreen();

            //no clue
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed/1000000;

            try{
                Thread.sleep(wait);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void drawToScreen() {
        // draw on a component ?
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT* SCALE, null);
        //dispose graphics context
        g2.dispose();
    }

//    private void update() {
//        gsm.update(g);
//    }

    private void draw() {
        gsm.draw(g);
    }

    public void init(){
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // ??
        g = (Graphics2D) image.getGraphics();
        running = true;
    }



    public void keyTyped(KeyEvent key){

    }
    public void keyPressed(KeyEvent key){
        gsm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key){
        gsm.keyReleased(key.getKeyCode());
    }



}

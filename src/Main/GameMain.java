package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameMain extends JPanel {
    //constants for the game
    static final String TITLE = "GAME TEMPLATE";
    static final int CANVAS_WIDTH = 320;
    static final int CANVAS_HEIGHT = 240;

    //game objects

    //custom drawing panel
    private GameCanvas canvas;

    static final int UPDATES_PER_SECOND = 4;

    //nano seconds
    static final long UPDATE_PERIOD_NANO_SECONDS = 100000000L/ UPDATES_PER_SECOND;


    //game state
    boolean gameOver = false;
    boolean gamePaused = false;

    //menu bar
    // public static JMenuBar menuBar;

    public GameMain() {
        //initialize game objects
        gameInit();

        //UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);
        //scorecard, button, etc
        //setup menu bar

    }

    private void gameInit() {

    }


    private void gameStart() {

        Thread gameThread = new Thread(){
            @Override
            public void run(){
                gameLoop();
            }
        };
        gameThread.start();
    }

    public void gameLoop() {
        long beginTime, timeTaken, timeLeft;

        while (!gameOver) {
            beginTime = System.nanoTime();

            if (!gamePaused) {
                //update object positions
                gameUpdate();
            }

            //refresh display
            repaint();

            //delay timer
            timeTaken = System.nanoTime() - beginTime;
            //miliseconds
            timeLeft = (UPDATE_PERIOD_NANO_SECONDS - timeTaken)/ 1000000;

            if(timeLeft < 10){
                timeLeft = 10;
            }

            try{
                //adds a delay and another thread can work in that time
                Thread.sleep(timeLeft);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    private void gameStop() {

    }

    private void gameUpdate() {

    }

    //can also use Graphics g
    public void gameDraw(Graphics2D g2d) {

    }

    //process keypressed events
    public void gameKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_LEFT:
                break;
        }

    }

    public class GameCanvas extends JPanel implements KeyListener {

        public GameCanvas() {
            setFocusable(true);
            requestFocus();
            addKeyListener(this);
        }

        //how graphics works??
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            super.paintComponent(g2d);
            setBackground(Color.BLACK);

            //draw ??
            gameDraw(g2d);
        }


        @Override
        public void keyTyped(KeyEvent e) {
            gameKeyPressed(e.getKeyCode());
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }


    }

    public static void main(String[] args) {
        //UI thread safety
        //let all other events be processed
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //construct a new frame
                JFrame frame = new JFrame(TITLE);
                frame.setContentPane(new GameMain());
//                frame.setJMenuBar(menuBar);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //responsive
                frame.pack();

                //center
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });

    }

}

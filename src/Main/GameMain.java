package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameMain extends JPanel {
    //constants for the game
    static final String TITLE = "GAME TEMPLATE";
    static final int CANVAS_WIDTH = 320;
    static final int CANVAS_HEIGHT = 240;

    //game objects

    //custom drawing panel
    private GameCanvas canvas;

    //menu bar
    // public static JMenuBar menuBar;

    public GameMain(){
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

    private void gameStart(){

    }

    private void gameStop(){

    }

    private void gameUpdate(){

    }

    //can also use Graphics g
    public void GameDraw(Graphics2D g2d){

    }

    //process keypressed events

    public void gameKeyPressed(int keyCode){
        switch (keyCode){
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

}

package Main;

import javax.swing.*;

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


        
    }

    private void gameInit() {
    }
}

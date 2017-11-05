package GameState;

import java.util.ArrayList;

public class GameStateManager {
    private ArrayList<GameStateManager> gameStates;
    private int currentState;
    public  static final int MENUSTATE = 0;
    public  static final int LEVEL1STATE = 1;

    public GameStateManager(){
        gameStates = new ArrayList<GameStateManager>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
    }

    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
}
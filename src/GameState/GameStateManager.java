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
}

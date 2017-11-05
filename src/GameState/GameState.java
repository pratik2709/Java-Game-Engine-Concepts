package GameState;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gsm;
    public abstract void init();
    public abstract void update(Graphics2D g);
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyReleased(int k);
    public abstract void keyPressed(int k);
}

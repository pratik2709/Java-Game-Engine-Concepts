package Entity;

import TileMap.TileMap;

public abstract class Enemy extends MapObject {

    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;

    protected boolean flinching;
    protected boolean flinchTimer;

    public Enemy(TileMap tm) {
        super(tm);
    }

    
}

package GameState;

import Entity.*;
import Entity.Enemies.Slugger;
import Main.GamePanel;
import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;
    private ArrayList<Enemy> enemies;
    private HUD hud;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0,0);
//        tileMap.setTween(0.07);
        bg = new Background("/Backgrounds/grassbg1.gif", (int) 0.1);

        player = new Player(tileMap);
        player.setPosition(100,100);

        //enemies
        enemies = new ArrayList<Enemy>();
        Slugger s = new Slugger(tileMap);
        s.setPosition(100,100);
        enemies.add(s);

        hud = new HUD(player);

    }

    @Override
    public void update() {
        player.update();
        //??
        tileMap.setPosition(
                GamePanel.WIDTH/2 - player.getx(),
                GamePanel.HEIGHT/2 - player.gety()

        );

        //set background
        bg.setPosition((int)tileMap.getx(), (int)tileMap.gety());

        //attack enemies
        player.checkAttack(enemies);

        //update enemies
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
            if(enemies.get(i).isDead()) {
                enemies.remove(i);
                i--;
            }
        }

    }

    @Override
    public void draw(Graphics2D g) {
        //draw background
        bg.draw(g);
        //draw tilemap
        tileMap.draw(g);
        //draw player
        player.draw(g);

        //enemies
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }

        //draw hud
        hud.draw(g);
    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        if(k == KeyEvent.VK_W) player.setJumping(false);
        if(k == KeyEvent.VK_E) player.setGliding(false);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_W) player.setJumping(true);
        if(k == KeyEvent.VK_E) player.setGliding(true);
        if(k == KeyEvent.VK_R) player.setScratching();
        if(k == KeyEvent.VK_F) player.setFiring();
    }
}

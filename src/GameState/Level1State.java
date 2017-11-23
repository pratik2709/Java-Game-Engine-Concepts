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
    private ArrayList<Explosion> explosions;

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

        populateEnemies();

        explosions = new ArrayList<Explosion>();

        hud = new HUD(player);

    }

    private void populateEnemies() {
        enemies = new ArrayList<Enemy>();
        Slugger s;
          s = new Slugger(tileMap);

        Point[] points = new Point[]{
            new Point(100, 100),
            new Point(860, 200),
            new Point(1525, 200),
            new Point(1680, 200),
            new Point(1800, 200),
        };
        for(int i = 0; i < points.length; i++){
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }

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
            Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()) {
                enemies.remove(i);
                i--;
                explosions.add(
                        new Explosion(e.getx(), e.gety())
                );
            }
        }
        //update explosions
        for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()){
                explosions.remove(i);
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

        //draw explosions
        for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).draw(g);
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
//        System.out.println("keypressed!");
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_W) {
//            System.out.println("pressed jumping");
            player.setJumping(true);
        }
        if(k == KeyEvent.VK_E) player.setGliding(true);
        if(k == KeyEvent.VK_R) player.setScratching();
        if(k == KeyEvent.VK_F) player.setFiring();
    }
}

package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private String[] choice = {
            "Start",
            "Help",
            "Quit",
    };

    private int currentChoice = 0;
    private Font titleFont;
    private Color titleColor;
    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/background/menu.gif", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN,28);
            font = new Font("Arial", Font.PLAIN,12);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void update(Graphics2D g) {
        bg.update();

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Dragon tale", 80, 70);

        //draw menu options
        g.setFont(font);
        for(int i = 0; i < choice.length; i++){
            if(i == currentChoice){
                g.setColor(Color.BLACK);
            }
            else{
                g.setColor(Color.RED);
            }
            g.drawString(choice[i], 145, 140 + i*15 );
        }
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice == choice.length){
                currentChoice = choice.length - 1;
            }
        }

        if(k == KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = 0;
            }
        }

        if(k == KeyEvent.VK_ENTER){
            select();
        }
    }

    private void select() {
        if(currentChoice == 0){
            //start
        }
        else if (currentChoice == 1){
            //help
        }
        else if(currentChoice == 2){
            //exit
        }
    }
}

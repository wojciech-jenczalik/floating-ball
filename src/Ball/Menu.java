package Ball;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static Ball.Game.*;

/**
 * Klasa zawierajaca obsluge zdarzen generowanych przez mysz,
 * opisuje tez uklad okienek i tekstu w menu gry
 */
public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Configurator configurator = Handler.getConfiguratorInstance();
    private Scores scores = Game.getScoresInstance();
    private KeyInput keyInput = Game.getKeyInputInstance();
    int SIZE;

    public Menu(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        if (gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 1, WIDTH / 3, HEIGHT / 8)) {//start
             /*   game.gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD(); */
                gameState = Game.STATE.Chooser;
            }
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 2, WIDTH / 3, HEIGHT / 8)) { //hiscores
                gameState = Game.STATE.Hiscores;
            }
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 3, WIDTH / 3, HEIGHT / 8)) { //help
                gameState = Game.STATE.Help;
            }
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8)) { //exit
                System.exit(0);
            }
        }else if (gameState == Game.STATE.Help || gameState == Game.STATE.Hiscores){
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8)) { //return
                gameState = Game.STATE.Menu;
            }
        }else if (gameState == STATE.Chooser){
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8)) { //return
                gameState = Game.STATE.Menu;
            }
            if (mouseOver(mx, my, Game.WIDTH / 2 - Game.WIDTH / 26 - Game.WIDTH / 11, Game.HEIGHT / 2 - Game.HEIGHT / 12, SIZE / 11, SIZE / 11)) {
                configurator.setBallType(0);
                gameState = STATE.DifficultyChooser;
           /*     game.gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD(); */
            }
            if (mouseOver(mx, my, Game.WIDTH / 2 - Game.WIDTH / 26, Game.HEIGHT / 2 - Game.HEIGHT / 12, SIZE / 11, SIZE / 11)) {
                configurator.setBallType(1);
                gameState = STATE.DifficultyChooser;
              /*  game.gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD(); */
            }
            if (mouseOver(mx, my, Game.WIDTH / 2 - Game.WIDTH / 26 + Game.WIDTH / 11, Game.HEIGHT / 2 - Game.HEIGHT / 12, SIZE / 11, SIZE / 11)) {
                configurator.setBallType(2);
                gameState = STATE.DifficultyChooser;
            /*    game.gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD(); */
            }
        }else if (gameState == STATE.DifficultyChooser){
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 2, WIDTH / 3, HEIGHT / 8)) { //ez
                gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD();
                configurator.setGameDifficulty(0.66f);
                configurator.writeProperties(7);
            }
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 3, WIDTH / 3, HEIGHT / 8)) { //med
                gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD();
                configurator.setGameDifficulty(1f);
                configurator.writeProperties(7);
            }
            if (mouseOver(mx, my, WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8)) { //hard
                gameState = Game.STATE.Game;
                handler.LoadImageLevel(handler.level1);
                handler.LoadHUD();
                configurator.setGameDifficulty(1.5f);
                configurator.writeProperties(7);
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            return my > y && my < y + height;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g) {
        if(HEIGHT>WIDTH){
            SIZE=WIDTH;
        }else {
            SIZE=HEIGHT;
        }

        Font menuFont = new Font("Comic Sans MS", Font.BOLD, SIZE / 14);
        Font startFont = new Font("Comic Sans MS", Font.PLAIN, SIZE / 14);
        Font helpFont = new Font("Comic Sans MS", Font.PLAIN, SIZE / 14);
        Font textFont = new Font("Comic Sans MS", Font.PLAIN, SIZE / 28);
        g.setFont(menuFont);
        g.setColor(Color.CYAN);
        g.drawString("FLOATING BALL", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9);

        if (gameState == Game.STATE.Menu) {
            g.setFont(menuFont);
            g.setColor(Color.CYAN);
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 1, WIDTH / 3, HEIGHT / 8); //start
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 2, WIDTH / 3, HEIGHT / 8); //hiscores
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 3, WIDTH / 3, HEIGHT / 8); //help
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8); //exit
            g.setFont(startFont);
            g.setColor(Color.white);
            g.drawString("START", WIDTH / 2 - WIDTH / 11, HEIGHT * 5 / 100 + HEIGHT / 4 * 1);
            g.drawString("HISCORES", WIDTH / 2 - WIDTH / 7, HEIGHT * 5 / 100 + HEIGHT / 6 * 1 + HEIGHT / 4);
            g.drawString("HELP", WIDTH / 2 - WIDTH / 14, HEIGHT * 5 / 100 + HEIGHT / 6 * 2 + HEIGHT / 4);
            g.drawString("EXIT", WIDTH / 2 - WIDTH / 14, HEIGHT * 5 / 100 + HEIGHT / 6 * 3 + HEIGHT / 4);
        } else if (gameState == Game.STATE.Help) {
            g.setFont(helpFont);
            g.setColor(Color.CYAN);
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8); //back
            g.setColor(Color.white);
            g.drawString("BACK", WIDTH / 2 - WIDTH / 14, HEIGHT * 5 / 100 + HEIGHT / 6 * 3 + HEIGHT / 4);
            g.setFont(textFont);
            g.drawString("Use WASD or arrow keys to handle the ball.", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 1 + HEIGHT / 12);
            g.drawString("Get the ball to the target.", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 2 + HEIGHT / 12);
            g.drawString("Avoid crashing into obstacles.", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 3 + HEIGHT / 12);
            g.drawString("Gather as much points as possible.", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 4 + HEIGHT / 12);
            g.drawString("Scores: Easy x 0.66, Normal x 1, Hard x 1.5", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 5 + HEIGHT / 12);
        } else if (gameState == Game.STATE.Hiscores) {
            g.setFont(helpFont);
            g.setColor(Color.CYAN);
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8); //back
            g.setColor(Color.white);
            g.drawString("BACK", WIDTH / 2 - WIDTH / 14, HEIGHT * 5 / 100 + HEIGHT / 6 * 3 + HEIGHT / 4);
            g.setFont(textFont);
            try {
                scores.readScores();
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawString(Integer.toString(scores.getIndexes()[0])+".  "
                            +Integer.toString(scores.getScores()[0])+"  "
                            +(scores.getNicknames()[0]), WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 1 + HEIGHT / 18);
            g.drawString(Integer.toString(scores.getIndexes()[1])+".  "
                    +Integer.toString(scores.getScores()[1])+"  "
                    +(scores.getNicknames()[1]), WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 2 + HEIGHT / 18);
            g.drawString(Integer.toString(scores.getIndexes()[2])+".  "
                    +Integer.toString(scores.getScores()[2])+"  "
                    +(scores.getNicknames()[2]), WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 3 + HEIGHT / 18);
            g.drawString(Integer.toString(scores.getIndexes()[3])+".  "
                    +Integer.toString(scores.getScores()[3])+"  "
                    +(scores.getNicknames()[3]), WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 4 + HEIGHT / 18);
            g.drawString(Integer.toString(scores.getIndexes()[4])+".  "
                    +Integer.toString(scores.getScores()[4])+"  "
                    +(scores.getNicknames()[4]), WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 9 * 5 + HEIGHT / 18);
        } else if (gameState == Game.STATE.Chooser) {
            g.setFont(menuFont);
            g.setColor(Color.white);
            g.drawImage(tex.player[0], Game.WIDTH / 2 - Game.WIDTH / 26 - Game.WIDTH / 11, Game.HEIGHT / 2 - Game.HEIGHT / 12, null);
            g.drawImage(tex.player[1], Game.WIDTH / 2 - Game.WIDTH / 26, Game.HEIGHT / 2 - Game.HEIGHT / 12, null);
            g.drawImage(tex.player[2], Game.WIDTH / 2 - Game.WIDTH / 26 + Game.WIDTH / 11, Game.HEIGHT / 2 - Game.HEIGHT / 12, null);
            g.drawString("Choose your ball!", WIDTH / 2 - WIDTH / 5, HEIGHT * 5 / 100 + HEIGHT / 4 * 1);
            g.setColor(Color.CYAN);
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8);
            g.setFont(helpFont);
            g.setColor(Color.white);
            g.drawString("BACK", WIDTH / 2 - WIDTH / 14, HEIGHT * 5 / 100 + HEIGHT / 6 * 3 + HEIGHT / 4);
        }else if (gameState == STATE.DifficultyChooser) {
            g.setFont(menuFont);
            g.setColor(Color.CYAN);
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 2, WIDTH / 3, HEIGHT / 8); //easy
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 3, WIDTH / 3, HEIGHT / 8); //med
            g.fillRect(WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 6 * 4, WIDTH / 3, HEIGHT / 8); //hard
            g.setFont(startFont);
            g.setColor(Color.white);
            g.drawString("Select difficulty:", WIDTH / 2 - WIDTH / 6, HEIGHT * 5 / 100 + HEIGHT / 4);
            g.drawString("Easy", WIDTH / 2 - WIDTH / 8, HEIGHT * 5 / 100 + HEIGHT / 6 * 1 + HEIGHT / 4);
            g.drawString("Medium", WIDTH / 2 - WIDTH / 8, HEIGHT * 5 / 100 + HEIGHT / 6 * 2 + HEIGHT / 4);
            g.drawString("Hard", WIDTH / 2 - WIDTH / 8, HEIGHT * 5 / 100 + HEIGHT / 6 * 3 + HEIGHT / 4);
        }
    }
}

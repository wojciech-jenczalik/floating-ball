package Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Glowna klasa gry. Zawiera:
 * konstruktor inicjalizujacy okno głowne i pierwsze grafiki,
 * funkcje startująca i zatrzymująca watek,
 * petle dzialania gry,
 * renderer grafki.
 */
public class Game extends Canvas implements Runnable {

    public static int WIDTH=960, HEIGHT = WIDTH / 12 * 9;
    public static int LEVEL = 1;
    public int frames = 0;
    public static int FROZENFPS = 0;
    private int frozenScore = 0;
    private int score = 0;

    public static BufferedImage background;

    private Thread thread;
    private boolean running = false;
    private static Handler handler;
    static Texture tex;
    private Configurator configurator;
    private Menu menu;
    private static Scores scores;
    private static KeyInput keyInput;


    public enum STATE{
        Menu,
        Game,
        Help,
        Chooser,
        Hiscores,
        DifficultyChooser
    }

    public static STATE gameState = STATE.Menu;
    public Game() {

        //System.out.println(System.getProperty("user.dir"));
        handler = new Handler();
        tex = new Texture();
        configurator = new Configurator();
        scores = new Scores();
        menu = new Menu(this, handler);
        keyInput = new KeyInput(handler, configurator);
        BufferedImageLoader loader = new BufferedImageLoader();
       // level1 = loader.loadImage("/level1.png"); //mapa
        background = loader.loadImage("/bg4.jpg"); //tło
        this.addKeyListener(keyInput);
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Floating ball", this);


       /* handler.addObject(new Player(100, 100, ID.Player, 32, 32, handler));
        handler.addObject(new Obstacle(640,0, ID.Obstacle, 32, 240));
        handler.addObject(new Obstacle(640, 450, ID.Obstacle, 32, 240));
        handler.addObject(new Obstacle(320, 0 ,ID.Obstacle, 32, 440));
        handler.addObject(new Obstacle(0, HEIGHT / 2, ID.Obstacle, 120, 32));
        handler.addObject(new Goal(WIDTH - (WIDTH / 8), HEIGHT - (HEIGHT / 8), ID.Goal, 32, 32)); */

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
                if(handler.isPaused()==false) {
                    score = configurator.getScoreAmount();
                    if (score > 0) {
                        score--;
                    }
                      configurator.setScoreAmount(score);
                }
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                FROZENFPS = frames;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        if (gameState == STATE.Game) {
            handler.tick();
        } else if (gameState == STATE.Menu){
            menu.tick();
        }
        if (configurator.getLivesAmount() == 0 || LEVEL == 8) {
            handler.clearLevel();
            String playerName = JOptionPane.showInputDialog("Enter your nickname");
            frozenScore = (int)(score*configurator.getGameDifficulty());
        //    System.out.println(frozenScore+playerName);
            scores.setScoreAndNickname(frozenScore, playerName);
            scores.writeScores();
            gameState = STATE.Hiscores;
            LEVEL = 1;
            configurator.setLivesAmount(20);
            configurator.setScoreAmount(0);
        }


        //if(handler.isPaused()==true){ running=false; }
        //if(handler.isPaused()==false){running=true; }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, 0, this);

        if (gameState == STATE.Game) {
            handler.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Hiscores || gameState == STATE.Chooser || gameState ==STATE.DifficultyChooser){
           menu.render(g);
        }


        g.dispose();
        bs.show();
    }


    public static Texture getInstance(){
        return tex;
    }
    public static Handler getHandlerInstance() {return handler;}
    public static Scores getScoresInstance() {return scores;}
    public static KeyInput getKeyInputInstance() {return keyInput;}
    public static void main(String[] args){
        new Game();
    }
}

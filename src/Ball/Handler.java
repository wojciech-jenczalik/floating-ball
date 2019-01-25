package Ball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

/**
 * Klasa na biezaco obslugujaca obiekty interaktywne(ich wyswietlanie oraz w przyszlosci: zachowanie)
 * HUD
 * oraz tworzenie i wyswietlanie poziomow.
 *
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<>();
    private LinkedList<HUD> hud = new LinkedList<>();
    private static Configurator configurator;
    public BufferedImage level1;
    private BufferedImage level2, level3, level4, level5,level6, level7;
    private Random generator;
    private boolean paused;
    public static int tempHeight = Game.HEIGHT;
    public static int tempWidth = Game.WIDTH;
    private Texture tex = Game.getInstance();
    private int SIZE = Game.HEIGHT;
    private boolean blocked = true;

    public Handler() {
        BufferedImageLoader loader = new BufferedImageLoader();
        level1 = loader.loadImage("/level1.png");
        level2 = loader.loadImage("/level2.png");
        level3 = loader.loadImage("/level3.png");
        level4 = loader.loadImage("/level4.png");
        level5 = loader.loadImage("/level5.png");
        level6 = loader.loadImage("/level6.png");
        level7 = loader.loadImage("/level3.png");
        configurator = new Configurator(0, "0", 0, 0, 0, 0, 0);
        //configurator.writeProperties();
        configurator.loadProperties();
        generator = new Random();
        paused = false;
    }


    public static Configurator getConfiguratorInstance() {return configurator;}

    public boolean isPaused(){
        return paused;
    }

    public void setPauseStatus(boolean p){
        paused=p;
    }
    public void tick(){
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if((tempHeight!=Game.HEIGHT || tempWidth!=Game.WIDTH) && (!blocked)) {
                if(Game.WIDTH>Game.HEIGHT){
                    SIZE=Game.HEIGHT;
                }else{
                    SIZE=Game.WIDTH;
                }
                for(int j = 0; j < object.size(); j++){
                    GameObject tempObject2 = object.get(j);
                    tempObject2.setX(tempObject2.getX() * Game.WIDTH / tempWidth);
                    tempObject2.setY(tempObject2.getY() * Game.HEIGHT / tempHeight);
                /*    tempObject2.setWidth(tempObject2.getWidth() * Game.WIDTH / tempWidth);
                    tempObject2.setHeight(tempObject2.getHeight() * Game.HEIGHT / tempHeight); */
                    if(tempObject2.getId().equals(ID.Player) || tempObject2.getId().equals(ID.Goal)) {
                        tempObject2.setWidth(SIZE / 11);
                        tempObject2.setHeight(SIZE / 11);
                    }else if(tempObject2.getId().equals(ID.Obstacle) || tempObject2.getId().equals(ID.LifeBonus)){
                        tempObject2.setWidth(SIZE / 22);
                        tempObject2.setHeight(SIZE / 22);
                    }
                }
            }
            tempHeight = Game.HEIGHT;
            tempWidth = Game.WIDTH;
            tempObject.tick();
            blocked=false;
        }
    }


    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject =  object.get(i);
            tempObject.render(g);
        }
        for(int i = 0; i < hud.size(); i++){
            HUD tempHud = hud.get(i);
            tempHud.render(g);
        }
    }

    public void LoadHUD(){
        addHudElement(new LivesBelt(0, 0, ID.LivesBelt, configurator));
        addHudElement(new ScoreBelt(0, 0, ID.ScoreBelt, configurator));
        addHudElement(new LevelBelt(0, 0, ID.LevelBelt));
        addHudElement(new FpsBelt(0, 0, ID.FpsBelt));
        addHudElement(new PauseSign(0, 0, ID.PauseSign, this));
    }

    public void LoadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        int roll = generator.nextInt(3);


        for (int xx = 0; xx < h; xx++){
            for (int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 && blue == 255){
                    if(roll == 0){
                        addObject(new Obstacle(xx*(Game.WIDTH / 30 - 1), yy*(Game.HEIGHT / 22 - 1), 0, ID.Obstacle, Game.WIDTH / 30, (Game.WIDTH / 30)));
                        roll = generator.nextInt(3);
                    }
                    if(roll == 1){
                        addObject(new Obstacle(xx*(Game.WIDTH / 30 - 1), yy*(Game.HEIGHT / 22 - 1), 1, ID.Obstacle, Game.WIDTH / 30, (Game.WIDTH / 30)));
                        roll = generator.nextInt(3);
                    }
                    if(roll == 2){
                        addObject(new Obstacle(xx*(Game.WIDTH / 30 - 1), yy*(Game.HEIGHT / 22 - 1), 2, ID.Obstacle, Game.WIDTH / 30, (Game.WIDTH / 30)));
                        roll = generator.nextInt(3);
                    }
                }
                if (red == 255 && green == 0 && blue == 0) addObject(new Player(xx*(Game.WIDTH / 30 - 1), yy*(Game.HEIGHT / 22 - 1), configurator.getLivesAmount(), configurator.getBallType(), ID.Player, Game.WIDTH / 15, Game.WIDTH / 15, this, configurator));
                if (red == 0 && green == 38 && blue == 255) addObject(new LifeBonus(xx*(Game.WIDTH / 30 - 1), yy*(Game.HEIGHT / 22 - 1), 0, ID.LifeBonus, Game.WIDTH / 30, Game.WIDTH / 30));
                if (red == 255 && green == 216 && blue == 0) addObject(new Goal(xx*(Game.WIDTH / 30 - 1), yy*(Game.HEIGHT / 22 - 1), 0, ID.Goal, Game.WIDTH / 15, Game.WIDTH / 15, this));
                //if (red == 0 && green == 255 && blue == 255) addObject(new Portal(xx*31, yy*31, 0, ID.Portal, 32, 64));
            }
        }
    }




    public void addObject(GameObject object){ this.object.add(object); }
    public void addHudElement(HUD hud){this.hud.add(hud);}
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void switchLevel(){
        clearLevel();
        switch(Game.LEVEL){
            case 1:
                Game.LEVEL++;
                LoadImageLevel(level2);
                Game.LEVEL--;
                break;
            case 2:
                LoadImageLevel(level3);
                break;
            case 3:
                LoadImageLevel(level4);
                break;
            case 4:
                LoadImageLevel(level5);
                break;
            case 5:
                LoadImageLevel(level6);
                break;
            case 6:
                LoadImageLevel(level7);
                break;
                }
                Game.LEVEL++;
    }

    public void clearLevel(){
        object.clear();
    }

}

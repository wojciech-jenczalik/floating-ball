package Ball;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasa sluzaca do przypisywania tekstur graficznych obiektom
 */
public class Texture {
    private int SIZE = Game.HEIGHT;
    private SpriteSheet os, os2, os3, ps, ps2, ps3, gs, lbs, pts;
    private BufferedImage obstacle_sheet = null;
    private BufferedImage obstacle_sheet2 = null;
    private BufferedImage obstacle_sheet3 = null;
    private BufferedImage player_sheet = null;
    private BufferedImage player_sheet2 = null;
    private BufferedImage player_sheet3 = null;
    private BufferedImage goal_sheet = null;
    private BufferedImage lifeBonus_sheet = null;
    private BufferedImage portal_sheet = null;

    public Image[] player = new java.awt.Image[3];
    public Image[] obstacle = new java.awt.Image[3]; //w [] liczba rodzajów przeszkód
    public Image[] goal = new java.awt.Image[1];
    public Image[] lifeBonus = new java.awt.Image[1];
    public Image[] portal = new java.awt.Image[1];

    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            obstacle_sheet = loader.loadImage("/rock1.png");
            obstacle_sheet2 = loader.loadImage("/rock2.png");
            obstacle_sheet3 = loader.loadImage("/rock3.png");
            player_sheet = loader.loadImage("/mars.png");
            player_sheet2 = loader.loadImage("/fioletowa.png");
            player_sheet3 = loader.loadImage("/zielona.png");
            goal_sheet = loader.loadImage("/galaxy.png");
            lifeBonus_sheet = loader.loadImage("/bonuslife.png");
            portal_sheet = loader.loadImage("/portal.gif");

        }catch (Exception e){
            e.printStackTrace();
        }
        os = new SpriteSheet(obstacle_sheet);
        os2 = new SpriteSheet(obstacle_sheet2);
        os3 = new SpriteSheet(obstacle_sheet3);
        ps = new SpriteSheet(player_sheet);
        ps2 = new SpriteSheet(player_sheet2);
        ps3 = new SpriteSheet(player_sheet3);
        gs = new SpriteSheet(goal_sheet);
        lbs = new SpriteSheet(lifeBonus_sheet);
        pts = new SpriteSheet(portal_sheet);

        getTextures();
    }

    public void getTextures(){
        if(Game.WIDTH>Game.HEIGHT){
            SIZE=Game.HEIGHT;
        }else{
            SIZE=Game.WIDTH;
        }
        obstacle[0] = os.resizeImage(SIZE / 22, SIZE / 22);
        obstacle[1] = os2.resizeImage(SIZE / 22, SIZE / 22);
        obstacle[2] = os3.resizeImage(SIZE / 22, SIZE / 22);
        player[0] = ps.resizeImage(SIZE / 11,SIZE / 11);
        player[1] = ps2.resizeImage(SIZE/ 11, SIZE / 11);
        player[2] = ps3.resizeImage(SIZE/ 11, SIZE / 11);
        goal[0] = gs.resizeImage(SIZE / 11, SIZE / 11);
        lifeBonus[0] = lbs.resizeImage(SIZE / 22, SIZE / 22);
        portal[0] = pts.resizeImage(32, 64);

        /*obstacle[0] = os.resizeImage(Game.WIDTH / 30, Game.HEIGHT / 30);
        obstacle[1] = os2.resizeImage(Game.WIDTH / 30, Game.HEIGHT / 30);
        obstacle[2] = os3.resizeImage(Game.WIDTH / 30, Game.HEIGHT / 30);
        player[0] = ps.resizeImage(Game.WIDTH / 15,Game.HEIGHT / 15);
        player[1] = ps2.resizeImage(Game.WIDTH / 15, Game.HEIGHT / 30);
        goal[0] = gs.resizeImage(Game.WIDTH / 15, Game.HEIGHT / 30);
        lifeBonus[0] = lbs.resizeImage(Game.WIDTH / 30, Game.HEIGHT / 30);
        portal[0] = pts.resizeImage(Game.WIDTH / 30, Game.HEIGHT / 15); */
    }

    public int getSIZE() {
        return SIZE;
    }
}

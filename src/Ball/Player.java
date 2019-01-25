package Ball;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Klasa dziedziczaca po GameObject,
 * pozwala na wyswietlanie i sterowanie kulki gracza
 * okresla tez system kolizji z innymi obiektami interaktywnymi
 */
public class Player extends GameObject {


    Handler handler;
    Configurator configurator;
    Texture tex;
    AffineTransform at;
    Graphics2D g2g;
    private int type;
    private static int livesAmount;

    public Player(int x, int y, int livesAmount, int type, ID id, int width, int height, Handler handler, Configurator configurator) {
        super(x, y, id, width, height);
        this.type = type;
        this.livesAmount = livesAmount;
        this.handler = handler;
        this.configurator = configurator;
        this.tex  = Game.getInstance();
        accY = configurator.getGravityConstant()*configurator.getGameDifficulty();
        if(Game.LEVEL==1) {
            accY = 0;
        }else{
            accY = configurator.getGravityConstant()*configurator.getGameDifficulty();
        }
    }

    public Rectangle getBounds(){
      return new Rectangle(x, y, width, height);
    }
    public Ellipse2D.Float getEllipse(){ return new Ellipse2D.Float(x, y, width, height); }


    public void tick() {
        if(handler.isPaused()==false) {
            x += velX;
            y += velY;
            velX += accX;
            velY += accY;
        }
        if(configurator.getLivesAmount()==0){
            velX = 0;
            velY = 0;
            accX = 0;
            accY = 0;
        }
        collision();
        at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(x/2), width / 2, height / 2);
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Obstacle){

                if(getEllipse().intersects(tempObject.getBounds())){
                    x = 100;
                    y = 100;
                    velX = 0;
                    velY = 0;
                    if (livesAmount > 0) livesAmount -= 1;
                    configurator.setLivesAmount(livesAmount);
                    int score = configurator.getScoreAmount();
                    if (score > 100) configurator.setScoreAmount(score - 100);
                    if (score < 100 && score > 0) configurator.setScoreAmount(0);
                }
            }
            else if (tempObject.getId() == ID.Goal) {
                //nowy poziom
                if (getEllipse().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                    configurator.setLivesAmount(livesAmount);
                    configurator.setScoreAmount(configurator.getScoreAmount() + 2000);
                    /*x = 100;
                    y = 100;
                    velX = 0;
                    velY = 0;*/
                }
            }
            else if (tempObject.getId() == ID.LifeBonus) {
                if (getEllipse().intersects(tempObject.getBounds())) {
                    livesAmount += 1;
                    configurator.setLivesAmount(livesAmount);
                    configurator.setScoreAmount(configurator.getScoreAmount() + 100);
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    public void render(Graphics g) {
        /*g.setColor(Color.white);
        g.fillOval(x, y, width, height); */ //na wszelki
        g2g =(Graphics2D)g;
        if (type == 0) //mars
           // g.drawImage(tex.player[0], x, y, null);
            g2g.drawImage(tex.player[0], at, null);
        if (type == 1) //fioletwoa
            g2g.drawImage(tex.player[1], at, null);
        if (type == 2) //zielona
            g2g.drawImage(tex.player[2], at, null);

    }
}

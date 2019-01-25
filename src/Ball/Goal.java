package Ball;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Klasa okreslajaca cel, do ktorego nalezy doprowadzic kulke
 */
public class Goal extends GameObject{
    Handler handler;
    Texture tex = Game.getInstance();
    private int type;
    public static AffineTransform at;
    Graphics2D g2g;
    public static long count;
    public static int goalX;
    public static int goalY;


    public Goal(int x, int y, int type, ID id, int width, int height, Handler handler) {
        super(x, y, id, width, height);
        this.type = type;
        at = AffineTransform.getTranslateInstance(x, y);
        this.handler=handler;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
    public Ellipse2D.Float getEllipse(){ return new Ellipse2D.Float(x, y, width, height); }

    public void tick() {
            count++;
            if (count == 2) count = 1;
            if(handler.isPaused()==false) at.rotate(Math.toRadians(count), width / 2, height / 2);
        goalX=x;
        goalY=y;

        }



    public void render(Graphics g) {
        if (type == 0)

           // g.drawImage(tex.goal[0], x, y, null);
        g2g =(Graphics2D)g;
        g2g.drawImage(tex.goal[0], at, null);
    }
}

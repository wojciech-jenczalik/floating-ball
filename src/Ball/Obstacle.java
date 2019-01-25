package Ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Klasa dziedziczaca po klasie GameObject,
 * opisuje przeszkody w grze,
 * wyswietla grafike przypisana do konkretnej przeszkody
 */
public class Obstacle extends GameObject {

    Texture tex = Game.getInstance();
    private int type;

    public Obstacle(int x, int y, int type, ID id, int width, int height) {
        super(x, y, id, width, height);
        this.type = type;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
    public Ellipse2D.Float getEllipse(){ return new Ellipse2D.Float(x, y, width, height); }

    public void tick() {

    }

    public void render(Graphics g) {

        //g.setColor(Color.black);
       // g.fillRect(x, y, width, height);  //na wszelki wypadek XD
        if (type == 0) //yellow plain
            g.drawImage(tex.obstacle[0], x, y, null);
        if (type == 1) //nozna
            g.drawImage(tex.obstacle[1], x, y, null);
        if (type == 2)
            g.drawImage(tex.obstacle[2], x, y, null);
    }
}

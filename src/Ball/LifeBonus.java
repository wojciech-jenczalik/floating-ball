package Ball;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Klasa okreslajaca obiekt interaktywny, ktory w momencie zderzenia sie z graczem
 * dodaje mu jedno zycie i 100 punktow
 */
public class LifeBonus extends GameObject{

    Texture tex = Game.getInstance();
    private int type;

    public LifeBonus(int x, int y, int type, ID id, int width, int height) {
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
        if (type == 0)
            g.drawImage(tex.lifeBonus[0], x, y, null);
    }
}

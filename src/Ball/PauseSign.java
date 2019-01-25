package Ball;

import java.awt.*;

import static Ball.Game.HEIGHT;
import static Ball.Game.WIDTH;

/**
 * Klasa dziedziczaca po klasie HUD,
 * pozwala wyswietlic informacje o pauzie
 */
public class PauseSign extends HUD{

    Handler handler;

    public PauseSign(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler=handler;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        Font pauseFont = new Font("SansSerif", Font.BOLD, WIDTH / 20);
        g.setFont(pauseFont);
        if(handler.isPaused()==true) {
            g.drawString("GAME PAUSED", WIDTH / 2 - WIDTH /5, HEIGHT / 3);
        }
    }
}

package Ball;

import java.awt.*;

import static Ball.Game.HEIGHT;
import static Ball.Game.WIDTH;

/**
 * Klasa dziedziczaca po klasie HUD,
 * pozwala wyswietlic liczbe zyc
 * w formie tekstu
 */
public class LivesBelt extends HUD{

    Configurator configurator;

    public LivesBelt(int x, int y, ID id, Configurator configurator) {
        super(x, y, id);
        this.configurator = configurator;
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        Font livesFont = new Font("SansSerif", Font.BOLD, 16);
        g.setFont(livesFont);
        g.drawString("Lives: " + configurator.getLivesAmount(), WIDTH - WIDTH / 5, HEIGHT / 10);
    }
}

package Ball;

import java.awt.*;

import static Ball.Game.HEIGHT;
import static Ball.Game.WIDTH;

/**
 * Klasa dziedziczaca po klasie HUD,
 * pozwala wyswietlic aktualna liczbe punktow
 * w formie tekstu
 */
public class ScoreBelt extends HUD{

    Configurator configurator;

    public ScoreBelt(int x, int y, ID id, Configurator configurator) {
        super(x, y, id);
        this.configurator = configurator;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        Font livesFont = new Font("SansSerif", Font.BOLD, 16);
        g.setFont(livesFont);
        g.drawString("Points: " + configurator.getScoreAmount(), WIDTH - WIDTH / 5, HEIGHT / 8);
    }
}

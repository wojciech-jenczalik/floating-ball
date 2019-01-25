package Ball;

import java.awt.*;

import static Ball.Game.HEIGHT;
import static Ball.Game.LEVEL;
import static Ball.Game.WIDTH;

/**
 * Klasa dziedziczaca po klasie HUD,
 * pozwala wyswietlic aktualny poziom na ktorym znajduje sie uzytkownik
 * w formie tekstu
 */
public class LevelBelt extends HUD{

    public LevelBelt(int x, int y, ID id) {
        super(x, y, id);
    }


    public void render(Graphics g) {
        g.setColor(Color.PINK);
        Font livesFont = new Font("SansSerif", Font.BOLD, Game.HEIGHT/40);
        g.setFont(livesFont);
        g.drawString("Level: " + LEVEL, WIDTH - WIDTH / 5, HEIGHT / 16);
    }
}

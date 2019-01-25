package Ball;

import java.awt.*;

import static Ball.Game.*;

/**
 * Klasa dziedziczaca po klasie HUD,
 * pozwala wyswietlic liczbe klatek na sekunde
 * w formie tekstu
 */
public class FpsBelt extends HUD{

    public FpsBelt(int x, int y, ID id) {
        super(x, y, id);
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        Font livesFont = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(livesFont);
        g.drawString("FPS: " + FROZENFPS, WIDTH / 32, HEIGHT / 16);
    }

}

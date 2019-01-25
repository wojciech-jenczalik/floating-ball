package Ball;

import java.awt.*;

/**
 * Klasa abstrakcyjna okreslajaca graficzny interfejs z informacjami dla uzytkownika
 *
 */
public abstract class HUD {

    protected int x, y;
    protected ID id;

    public HUD(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void render(Graphics g);

    public ID getId(){
        return id;
    }

}

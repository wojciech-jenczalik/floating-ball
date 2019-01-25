package Ball;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa zawierajaca obsluge zdarzen generowanych przez klawiature
 */
public class KeyInput extends KeyAdapter {

    Handler handler;
    Configurator configurator;
    private static float gravityConstant;
    private static float gameDifficulty;

    public KeyInput(Handler handler, Configurator configurator){
        this.handler = handler;
        this.configurator = configurator;
        this.gravityConstant = configurator.getGravityConstant();
        this.gameDifficulty = configurator.getGameDifficulty();
    }


    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                if(handler.isPaused()==false) {
                    if (key == KeyEvent.VK_ESCAPE) System.exit(0);
                    if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) tempObject.setAccY(-gravityConstant*configurator.getGameDifficulty());
                    if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) tempObject.setAccY(2 * (gravityConstant*configurator.getGameDifficulty()));
                    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setAccX(-gravityConstant*configurator.getGameDifficulty());
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { tempObject.setAccX(gravityConstant*configurator.getGameDifficulty());
                    }
                    // if(key == KeyEvent.VK_A) tempObject.setVelX(tempObject.getVelX() - 2);
                }
            }
        }
        if(key == KeyEvent.VK_P){
            if(handler.isPaused()==true) {handler.setPauseStatus(false); return;}
            if(handler.isPaused()==false) {handler.setPauseStatus(true); return;}
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){

                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) tempObject.setAccY(gravityConstant*configurator.getGameDifficulty());
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) tempObject.setAccY(gravityConstant*configurator.getGameDifficulty());
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setAccX(0);
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setAccX(0);
            }
        }
    }
}

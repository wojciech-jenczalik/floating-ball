package Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

/**
 * Klasa z konstruktorem tworzacym okno
 */
public class Window extends JFrame {

    public static JFrame frame;

    Texture tex = Game.getInstance();
    Handler handler = Game.getHandlerInstance();

    public Window(int width, int height, String title, Game game){

        frame = new JFrame(title);
      //  frame.setPreferredSize(new Dimension(width, height));
     //   frame.setMaximumSize(new Dimension(width, height));
      //  frame.setMinimumSize(new Dimension(width, height));
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e){
               // Component c = (Component)e.getSource();
                Game.WIDTH = frame.getWidth();
                Game.HEIGHT = frame.getHeight();
                Goal.at = AffineTransform.getTranslateInstance(Goal.goalX * Game.WIDTH / handler.tempWidth, Goal.goalY * Game.HEIGHT / handler.tempHeight);

        //        frame.setSize(new Dimension(Game.WIDTH, Game.HEIGHT));
                tex.getTextures();
            }
        });
        game.start();
    }

}

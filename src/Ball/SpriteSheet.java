package Ball;

import java.awt.*;
// import java.awt.image.BufferedImage;

/**
 * Mylaca nazwa, bo klasa ta sluzyla do przetwarzania kilkunastu obrazkow
 * znajdujacych sie w 1 pliku graficznym.
 * Teraz słuzy do skalowania obrazkow do pozadanej rozdzielczosci
 */
public class SpriteSheet {

    private Image image;

    public SpriteSheet(Image image){
        this.image = image;
    }

    /*public BufferedImage getImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    } */

    public Image resizeImage (int width, int height){
        Image resImg = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return resImg;
    }
}

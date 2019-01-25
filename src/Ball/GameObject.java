package Ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Klasa abstrakcyjna okreslajaca obiekty interaktywne w grze,
 * dziedzicza po niej: Player, Obstacle i Goal
 */
public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int width, height;
    protected float velX, velY;
    protected static float accX, accY;

    public GameObject(int x, int y, ID id, int width, int height){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract Ellipse2D.Float getEllipse();

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) {this.height = height; }
    public int getWidth() { return width;}
    public int getHeight() { return height;}
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public void setAccX(float accX){
        this.accX = accX;
    }
    public void setAccY(float accY){
        this.accY = accY;
    }
    public float getAccX(){
        return accX;
    }
    public float getAccY(){
        return accY;
    }
}

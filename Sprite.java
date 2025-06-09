import java.awt.Graphics;

/**
 * The Sprite class is a class that will be used to define the player and objects it can interact with people
 * 
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

abstract class Sprite {
    // sprite position variables
    private int x;
    private int y;
    // primary image variable (will be used as a parent picture to enact getSubImage() Method on)
    private String picName;
    // game object
    //------------------------------------------------------------------------------    
    Sprite(Game game, int x, int y, String picName) {
        this.x = x;
        this.y = y;
        this.picName = picName;
    }
    //------------------------------------------------------------------------------
    // getters
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public String getPicName() {
        return this.picName;
    }
    // setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }    
    //------------------------------------------------------------------------------
    // drawing frames
    abstract void draw(Graphics g);
}
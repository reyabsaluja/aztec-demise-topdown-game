import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Tile class is a tile image that is used to build the TileMapLayers 
 * 
 * @see Game
 * @see TileMapLayer
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Tile {
    private int x;
    private int y;
    private BufferedImage picture;
    //------------------------------------------------------------------------------
    Tile(int row, int col, BufferedImage img) {
        this.x = col * Const.TILE_SIZE;
        this.y = row * Const.TILE_SIZE;
        this.picture = img;
    }
    //------------------------------------------------------------------------------
    // getters
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public BufferedImage getImg() {
        return this.picture;
    }
    // setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    //------------------------------------------------------------------------------    
    public void draw(Graphics g) {
        g.drawImage(this.picture, this.getX(), this.getY(), null);
        g.setColor(Color.RED);
    }
}
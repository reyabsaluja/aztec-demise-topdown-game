import java.awt.Graphics;

/**
 * The Chest class is an interactive part of the game that can be opened.
 * Inherits from InteractiveGameObject
 * 
 * @see Sprite
 * @see InteractiveGameObject
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Chest extends InteractiveGameObject {
    String item;
    boolean opened = false;
    //------------------------------------------------------------------------------   
    public Chest(Game game, int x, int y, int imageStartingX, int imageStartingY, boolean lockStatus, String item, String key, String picName) {
        super(game, x, y, imageStartingX, imageStartingY, lockStatus, key, picName);
        this.item = item;
    }
    //------------------------------------------------------------------------------
    // getters
    public String getItem() {
        return this.item;
    }
    // setters
    public void setItem(String item) {
        this.item = item;
    }
    //------------------------------------------------------------------------------   
    @Override
    // Method to define opening door
    public void openObject() {
        this.setColumns((this.getColumns() + 1) % 4);
        this.setColumns((this.getColumns() + 1) % 4);
        this.setColumns((this.getColumns() + 1) % 4);
        this.opened = true;
    }
    //------------------------------------------------------------------------------   
    /**
     * Method to get individual chest pictures from tileset
     * 
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(loadParentPicture(this.getPicName()), this.getX(), this.getY(), null);
    }
}
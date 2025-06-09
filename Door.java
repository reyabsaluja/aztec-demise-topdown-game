import java.awt.Graphics;

/**
 * The Door class is an interactive part of the game that can be opened.
 * Inherits from InteractiveGameObject
 * 
 * @see Sprite
 * @see InteractiveGameObject
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Door extends InteractiveGameObject{
    String map;
    int spawnLocationX;
    int spawnLocationY;
    //------------------------------------------------------------------------------   
    public Door(Game game, int x, int y, int imageStartingX, int imageStartingY, boolean lockStatus, String key, String map, int spawnLocationX, int spawnLocationY, String picName) {
        super(game, x, y, imageStartingX, imageStartingY, lockStatus, key, picName);
        this.map = map;
        this.spawnLocationX = spawnLocationX;
        this.spawnLocationY = spawnLocationY;
    }
    //------------------------------------------------------------------------------
    // getters
    public String getMap() {
        return this.map;
    }
    public int getSpawnLocationX() {
        return this.spawnLocationX;
    }
    public int getSpawnLocationY() {
        return this.spawnLocationY;
    }
    // setters
    public void setMap(String map) {
        this.map = map;
    }
    public void setSpawnLocationX(int newSpawnLocationX) {
        this.spawnLocationX = newSpawnLocationX;
    }
    public void setSpawnLocationY(int newSpawnLocationY) {
        this.spawnLocationY = newSpawnLocationY;
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to get individiual door pictures from tileset
     * 
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(loadParentPicture(this.getPicName()), this.getX(), this.getY(), null);
    }
}
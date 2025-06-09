import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

/**
 * The InteractiveGameObject class is a game object that can be interacted with.
 * 
 * @see Sprite
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

abstract class InteractiveGameObject extends Sprite {
    // starting position of images variables
    private int imageStartingX;
    private int imageStartingY;
    // status of image 
    private int imageStatus = 0; // 0 = closed, 1 = open
    private int columns;
    private BufferedImage currentAnimation;
    // object image
    private BufferedImage image;
    // status of lock
    private boolean lockStatus;
    private boolean opened = false;
    private String key;
    //------------------------------------------------------------------------------   
    public InteractiveGameObject(Game game, int x, int y, int imageStartingX, int imageStartingY, boolean lockStatus, String key, String picName) {
        super(game, x, y, picName);
        this.imageStartingX = imageStartingX;
        this.imageStartingY = imageStartingY;
        this.lockStatus = lockStatus;
        this.key = key;
    }
    //------------------------------------------------------------------------------
    // getters
    public int getImageStartingX() {
        return this.imageStartingX;
    }
    public int getImageStartingY() {
        return this.imageStartingY;
    }
    public int getImageStatus() {
        return this.imageStatus;
    }
    public int getColumns() {
        return this.columns;
    }
    public BufferedImage getImage() {
        return this.image;
    }
    public BufferedImage getCurrentAnimation() {
        return this.currentAnimation;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public boolean getLockStatus() {
        return this.lockStatus;
    }
    public boolean getOpened() {
        return this.opened;
    }
    public String getKey() {
        return this.key;
    }
    // setters
    public void setImageStartingX(int imageStartingX) {
        this.imageStartingX = imageStartingX;
    }
    public void setImageStartingY(int imageStartingY) {
        this.imageStartingY = imageStartingY;
    }
    public void setImageStatus(int imageStatus) {
        this.imageStatus = imageStatus;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public void setLockStatus(boolean lockStatus) {
        this.lockStatus = lockStatus;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }
    public void setKey(String key) {
        this.key = key;
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to load parent picture
     * 
     * @param picName
     * @return BufferedImage
     */
    public BufferedImage loadParentPicture(String picName) {
        try {
            BufferedImage tileSet = ImageIO.read(new File(picName));
            this.currentAnimation = tileSet.getSubimage(this.imageStartingX + this.columns * Const.TILE_SIZE, this.imageStartingY, Const.TILE_SIZE, Const.TILE_SIZE);
            return currentAnimation;
        } catch (IOException ex) {System.out.println(ex);}
        return null;
    }
    // Method to define opening object
    public void openObject() {
        this.columns = (this.columns + 1) % 4; // shifting animation to match current movement
        this.columns = (this.columns + 1) % 4; // shifting animation to match current movement
    }
    //------------------------------------------------------------------------------
    @Override
    abstract void draw(Graphics g);
}
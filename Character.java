import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/**
 * The Character class is an animated version of the sprite
 * Inherits from Sprite
 * 
 * @see Sprite
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Character extends Sprite{
    // direction variables
    private int characterDirection = 0; // 0 = down, 1 = left, 2 = right, 3 = up
    // movement variables
    private boolean isMoving;
    private int characterVelocityX;
    private int characterVelocityY;
    // collision variables
    private Rectangle collisionBox;
    // animation variables
    private int buffer = 15;
    private int frame = 0;
    // game variable
    private Game game;
    //------------------------------------------------------------------------------
    public Character(Game game, int x, int y, String spriteSheet) {
        super(game, x, y, spriteSheet);
        this.characterDirection = 0;
        this.collisionBox = new Rectangle(this.getX() + 10, this.getY() + 10, Const.TILE_SIZE, Const.TILE_SIZE);
        this.game = game;
    }
    //------------------------------------------------------------------------------
    // getters
    public int getCharacterDirection() {
        return this.characterDirection;
    }
    public boolean playerIsMoving() {
        return this.isMoving;
    }
    public int getCharacterVelocityX() {
        return this.characterVelocityX;
    }
    public int getCharacterVelocityY() {
        return this.characterVelocityY;
    }
    public Rectangle getCollisionBox() {
        return this.collisionBox;
    }
    // setters
    public void setCharacterDirection(int characterDirection) {
        this.characterDirection = characterDirection;
    }
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    public void setCharacterVelocityX(int characterVelocityX) {
        this.characterVelocityX = characterVelocityX;
    }
    public void setCharacterVelocityY(int characterVelocityY) {
        this.characterVelocityY = characterVelocityY;
    }
    public void setCollisionBox() {
        this.collisionBox.setLocation(this.getX() + 10, this.getY() + 10);
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to get player pictures
     * 
     * @param spriteSheet
     * @return BufferedImage
     */
     public BufferedImage loadSpriteSheet(String spriteSheet) {
        try {
            BufferedImage img = ImageIO.read(new File(spriteSheet));
            this.frame += 1;
            if (this.frame == 8)
                this.frame = 0;
            BufferedImage currentAnimation = img.getSubimage(this.frame * 80 + this.buffer, this.characterDirection * 80 + this.buffer, 80 - this.buffer, 80 - this.buffer);
            return currentAnimation;
        } catch (IOException ex) {System.out.println(ex);}
        return null;
    }
    //------------------------------------------------------------------------------
    // walk functions
    public void walkDown() {
        moveDown();
        this.setCharacterVelocityY(3);
    }
    public void walkUp() {
        moveUp();
        this.setCharacterVelocityY(-3);
    }
    public void walkLeft() {
        moveLeft();
        this.setCharacterVelocityX(-3);
    }
    public void walkRight() {
        moveRight();
        this.setCharacterVelocityX(3);
    }
    // run functions
    public void runDown() {
        moveDown();
        this.setCharacterVelocityY(7);
    }
    public void runUp() {
        moveUp();
        this.setCharacterVelocityY(-7);
    }
    public void runLeft() {
        moveLeft();
        this.setCharacterVelocityX(-7);
    }
    public void runRight() {
        moveRight();
        this.setCharacterVelocityX(7);
    }
    // move functions
    public void moveDown() {
        this.characterDirection = 1; // setting player direction to down
        this.isMoving = true;
    }
    public void moveUp() {
        this.characterDirection = 0; // setting player direction to up
        this.isMoving = true;
    }
    public void moveLeft() {
        this.characterDirection = 3; // setting player direction to left
        this.isMoving = true;
    }
    public void moveRight() {
        this.characterDirection = 2; // setting player direction to right
        this.isMoving = true;
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to draw the player
     * 
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(loadSpriteSheet(this.getPicName()), (this.getX() - game.getGameCamera().getXOffset()), (this.getY() - game.getGameCamera().getYOffset()), null);
    }
}

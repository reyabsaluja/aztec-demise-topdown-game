import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

/**
 * The Player class is an animated version of the sprite
 * Inherits from Character
 * 
 * @see Sprite
 * @see Character
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Player extends Character {
    // held item variables
    private ArrayList < String > heldItems = new ArrayList < String > ();
    // pop-up variables
    private String chestPopup = "";
    private String doorPopup = "";
    // timer variable
    Timer time;
    //------------------------------------------------------------------------------
    public Player(Game game, int x, int y, String spriteSheet) {
        super(game, x, y, spriteSheet);
        this.setCharacterDirection(0);
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to update player movement, animations, and interactions
     * 
     * @param game
     */
    public void update(Game game) {
        // initializing classes
        MyKeyListener keyListener = game.getKeyListener();
        // player movement controls
        if (this.getCharacterDirection() == 0)
            this.setCharacterDirection(4);
        if (this.getCharacterDirection() == 1)
            this.setCharacterDirection(5);
        if (this.getCharacterDirection() == 2)
            this.setCharacterDirection(6);
        if (this.getCharacterDirection() == 3)
            this.setCharacterDirection(7);
        if (game.getGameState() == "GAME") {
            // player walk controls
            if (keyListener.down() && !collides(game, 0, 3) && !keyListener.run()) { // checking to see if collision or any other keys are pressed
                this.walkDown();
            } else if (keyListener.up() && !collides(game, 0, -3) && !keyListener.run()) { // checking to see if collision or any other keys are pressed
                this.walkUp();
            } else if (keyListener.left() && !collides(game, -3, 0) && !keyListener.run()) { // checking to see if collision or any other keys are pressed
                this.walkLeft();
            } else if (keyListener.right() && !collides(game, 3, 0) && !keyListener.run()) { // checking to see if collision or any other keys are pressed
                this.walkRight();
            }
            //player run controls
            if (keyListener.down() && keyListener.run() && !collides(game, 0, 7)) { // checking to see if collision or any other keys are pressed
                this.runDown();
            } else if (keyListener.up() && keyListener.run() && !collides(game, 0, -7)) { // checking to see if collision or any other keys are pressed
                this.runUp();
            } else if (keyListener.left() && keyListener.run() && !collides(game, -7, 0)) { // checking to see if collision or any other keys are pressed
                this.runLeft();
            } else if (keyListener.right() && keyListener.run() && !collides(game, 7, 0)) { // checking to see if collision or any other keys are pressed
                this.runRight();
            }
            if (this.playerIsMoving() && game.getGameState() == "GAME") {
                this.setX(this.getX() + this.getCharacterVelocityX());
                this.setY(this.getY() + this.getCharacterVelocityY());
                this.setCollisionBox();
            }
            // stopping player movement after certain movement
            if (this.getX() == this.getX() + this.getCharacterVelocityX() || this.getY() == this.getY() + this.getCharacterVelocityY()) {
                this.setIsMoving(false);
                this.setCharacterVelocityX(0);
                this.setCharacterVelocityY(0);
            }
            // setting game camera to center on player
            game.getGameCamera().centerOnPlayer(this);

            if (keyListener.interact()) { // checking for player interaction with objects
                TileMapLayer[] tileMapLayers = game.getTileMapLayers();
                Chest chests[][] = tileMapLayers[5].getChests();
                Door doors[][] = tileMapLayers[5].getDoors();
                for (int row = 0; row < chests.length; row++) { // looping through all chest objects
                    for (int column = 0; column < chests[row].length; column++) {
                        if (chests[row][column] != null) {
                            if (chests[row][column].getKey() != null) {
                                if (chests[row][column].getLockStatus() && this.hasHeldItem(chests[row][column].getKey()))
                                    chests[row][column].setLockStatus(false);
                            }
                            // opening chests
                            if (isNear(chests[row][column].getX(), chests[row][column].getY()) && !chests[row][column].getLockStatus() && !chests[row][column].opened && chests[row][column].getItem() != null) { // checking if player is near chest object, if the chest are unlocked or locked, and if the chest have something in them
                                chests[row][column].openObject();
                                this.addHeldItem(chests[row][column].getItem()); // adding chests item to the player's inventory
                                for (int numItems = 0; numItems < heldItems.size(); numItems++) {
                                    chestPopup = "OBTAINED " + this.heldItems.get(numItems); // creating pop-up 
                                }
                            } else if (isNear(chests[row][column].getX(), chests[row][column].getY()) && !chests[row][column].getLockStatus() && !chests[row][column].opened && chests[row][column].getItem() == null) { // checking to see if chest has nothing in it
                                chestPopup = "CHEST IS EMPTY!"; // creating pop-up
                            }
                            // checking if chest is locked
                            else if (isNear(chests[row][column].getX(), chests[row][column].getY()) && chests[row][column].getLockStatus()) {
                                chestPopup = "CHEST IS LOCKED!"; // creating pop-up
                            }
                        }
                    }
                }
                for (int row = 0; row < doors.length; row++) { // looping through all door objects
                    for (int column = 0; column < doors[row].length; column++) {
                        if (doors[row][column] != null) {
                            if (doors[row][column].getKey() != null) {
                                if (doors[row][column].getLockStatus() && this.hasHeldItem(doors[row][column].getKey()))
                                    doors[row][column].setLockStatus(false);
                            }
                            // opening doors
                            if (isNear(doors[row][column].getX(), doors[row][column].getY()) && !doors[row][column].getLockStatus() || isNear(doors[row][column].getX(), doors[row][column].getY()) && !doors[row][column].getLockStatus() && doors[row][column].getKey() == null) { // checking if player is near chest objects
                                doors[row][column].openObject();
                                this.setCoordinates(doors[row][column].getSpawnLocationX(), doors[row][column].getSpawnLocationY());
                                this.setCollisionBox();
                                game.setMapState(doors[row][column].getMap());
                                game.updateMapState();
                            }
                            // checking if door is locked
                            else if (isNear(doors[row][column].getX(), doors[row][column].getY()) && doors[row][column].getLockStatus()) {
                                doorPopup = "DOOR IS LOCKED!";
                            }
                        }
                    }
                }
            }
        }

        if (this.hasHeldItem(Const.KEY1) && this.hasHeldItem(Const.KEY2) && this.hasHeldItem(Const.KEY3))
            game.setGameState("VICTORY_DIALOGUE");
    }

    
    /** 
     * Method to check if the player collision box intersects with a tile collision box
     * 
     * @param game
     * @param playerVelocityX
     * @param playerVelocityY
     * @return boolean
     */
    public boolean collides(Game game, int playerVelocityX, int playerVelocityY) {
        TileMapLayer[] tileMapLayers = game.getTileMapLayers();
        Rectangle[][] collisionBoxes = tileMapLayers[1].getCollisionBoxes();
        Rectangle intersectionBox = new Rectangle(this.getCollisionBox().x + playerVelocityX + 4, this.getCollisionBox().y + playerVelocityY + 1, 24, 30);
        for (int row = 0; row < collisionBoxes.length; row++) {
            for (int column = 0; column < collisionBoxes[row].length; column++) {
                if (collisionBoxes[row][column] != null && intersectionBox.intersects(collisionBoxes[row][column])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to declare that the player is moving
    public void isMoving() {
        this.setIsMoving(true);
    }
    // Method ot declare that the player is not moving
    public void isNotMoving() {
        this.setIsMoving(false);
    }
    
    /** 
     * Method to check if player is near another object
     * 
     * @param otherX
     * @param otherY
     * @return boolean
     */
    public boolean isNear(int otherX, int otherY) {
        if (Math.abs(this.getX() - otherX) < 40 && Math.abs(this.getY() - otherY) < 40)
            return true;
        return false;
    }
    
    /** 
     * Method to declare what held items player has
     * 
     * @param item
     */
    public void addHeldItem(String item) {
        this.heldItems.add(item);
    }
    
    /** 
     * Method to check if player has certain held item
     * 
     * @param item
     * @return boolean
     */
    public boolean hasHeldItem(String item) {
        for (int numItems = 0; numItems < this.heldItems.size(); numItems++) {
            if (this.heldItems.get(numItems).equalsIgnoreCase(item))
                return true;
        }
        return false;
    }
    
    /** 
     * Method to draw pop-up text boxes
     * 
     * @param g
     */
    public void drawPopUpBox(Graphics g) {
        if (!chestPopup.equals("")) {
            g.setColor(Const.WHITE_COLOR);
            g.setFont(Const.SMALL_START_FONT);
            g.drawString(chestPopup, Const.SCREEN_WIDTH/2 - 100, Const.SCREEN_HEIGHT/2);
            time = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chestPopup = "";
                }
            });
            time.setRepeats(false);
            time.start();      
        }
        if (!doorPopup.equals("")) {
            g.setColor(Const.WHITE_COLOR);
            g.setFont(Const.SMALL_START_FONT);
            g.drawString(doorPopup, Const.SCREEN_WIDTH/2 - 100, Const.SCREEN_HEIGHT/2);
            time = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doorPopup = "";
                }
            });
            time.setRepeats(false);
            time.start();   
        }
    }
}
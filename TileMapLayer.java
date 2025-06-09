import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/**
 * The TileMapLayer class is a class that creates a 2D array of tiles  
 * 
 * @see Game
 * @see Tile
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class TileMapLayer {
    private int rows;
    private int columns;
    Tile[][] tiles;
    Rectangle[][] collisionBoxes;
    Chest[][] chests;
    Door[][] doors;
    private Game game;
    //------------------------------------------------------------------------------       
    TileMapLayer(Game game, String layerName, String tileSet) {
        this.game = game;
        getMapDimensions(layerName); // read the map file to find out the map dimensions
        renderMap(layerName, tileSet); // read and process the tileset file, adding tiles to the tile map
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to read the map file to find out the map dimensions
     * 
     * @param layerName
     */
    public void getMapDimensions(String layerName) {
        try {
            Scanner input = new Scanner(new File(layerName));
            while (input.hasNext()) {
                String line = input.nextLine();
                this.rows++;
                if (line.length() > this.columns) {
                    this.columns = line.length();
                }
            }
            input.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    /** 
     * Method to read and process the tileset file, adding tiles to the tile map
     * 
     * @param layerName
     * @param tileSet
     */
    public void renderMap(String layerName, String tileSet) {
        this.tiles = new Tile[rows][columns];
        this.collisionBoxes = new Rectangle[rows][columns];
        this.chests = new Chest[rows][columns];
        this.doors = new Door[rows][columns];
        try {
            BufferedImage tileSetPicture = ImageIO.read(new File(tileSet));
            int tileSetPictureColumns = tileSetPicture.getWidth() / Const.TILE_SIZE;
            Scanner input = new Scanner(new File(layerName));

            for (int row = 0; row < this.rows; row++) {
                String[] line = input.nextLine().split(",");
                for (int column = 0; column < line.length; column++) {
                    int tileIndex = Integer.parseInt(line[column]);

                    if (layerName == Const.MAP1DOORSANDCHESTS || layerName == Const.HOUSE1DOORSANDCHESTS || layerName == Const.DUNGEON_DOORS_AND_CHESTS || layerName == Const.MAZE_DOORS_AND_CHESTS) {
                        if (tileIndex == 1) { // bronze chest unlocked
                            Chest newChest = new Chest(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.BRONZE_CHEST_POS, false, Const.KEY1, null, Const.DECORATIONS_TILESET);
                            this.addChest(newChest, row, column);
                        } else if (tileIndex == 2) {
                            Door newDoor = new Door(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.BLUE_DOOR_POS, true, Const.KEY1, "MAP1", 480 - 8, 288, Const.DOOR_TILESET);
                            this.addDoor(newDoor, row, column);
                        } else if (tileIndex == 3) {
                            Door newDoor = new Door(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.BLUE_DOOR_POS, false, null, "HOUSE1", 544 - 8, 372 + 41, Const.DOOR_TILESET);
                            this.addDoor(newDoor, row, column);
                        } else if (tileIndex == 4) {
                            Door newDoor = new Door(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.RED_DOOR_POS, true, Const.KEY1, "DUNGEON", Const.WORLD_WIDTH/2 - 8, Const.WORLD_HEIGHT - 200, Const.DOOR_TILESET);
                            this.addDoor(newDoor, row, column);
                        } else if (tileIndex == 5) {
                            Door newDoor = new Door(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.RED_DOOR_POS, false, null, "MAP1", Const.WORLD_WIDTH/2 + 248, Const.WORLD_HEIGHT/2 - 64, Const.DOOR_TILESET);
                            this.addDoor(newDoor, row, column);
                        } else if (tileIndex == 6) {
                            Door newDoor = new Door(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.BRONZE_DOOR_POS, false, null, "MAZE", Const.WORLD_WIDTH/2 + 56, Const.WORLD_HEIGHT - 256, Const.DOOR_TILESET);
                            this.addDoor(newDoor, row, column);
                        } else if (tileIndex == 7) {
                            Door newDoor = new Door(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.BRONZE_DOOR_POS, false, null, "MAP1", Const.WORLD_WIDTH/2 - 100, Const.WORLD_HEIGHT/2, Const.DOOR_TILESET);
                            this.addDoor(newDoor, row, column);
                        } else if (tileIndex == 8) {
                            Chest newChest = new Chest(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.RED_CHEST_POS, false, null, null, Const.DECORATIONS_TILESET);
                            this.addChest(newChest, row, column);
                        } else if (tileIndex == 9) {
                            Chest newChest = new Chest(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.BLUE_CHEST_POS, false, Const.KEY2, null, Const.DECORATIONS_TILESET);
                            this.addChest(newChest, row, column);
                        } else if (tileIndex == 10) {
                            Chest newChest = new Chest(game, column * Const.TILE_SIZE, row * Const.TILE_SIZE, 0, Const.RED_CHEST_POS, false, Const.KEY3, null, Const.DECORATIONS_TILESET);
                            this.addChest(newChest, row, column);
                        }
                    }

                    if (layerName == Const.MAP1SOLIDLAYER || layerName == Const.HOUSE1SOLIDLAYER || layerName == Const.MAP1SOLIDLAYER2 || layerName == Const.MAZESOLIDLAYER || layerName == Const.DUNGEON_SOLID_LAYER_1 || layerName == Const.DUNGEON_SOLID_LAYER_2) {
                        if (tileIndex >= 1) {
                            tileIndex = Math.abs(tileIndex) - 1;
                            Tile newTile = new Tile(row, column, tileSetPicture.getSubimage(tileIndex % tileSetPictureColumns * Const.TILE_SIZE, tileIndex / tileSetPictureColumns * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE));
                            this.addTile(newTile, row, column);
                            Rectangle newRect = new Rectangle(column * Const.TILE_SIZE, row * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE);
                            this.addCollisionBox(newRect, row, column);
                        }
                    } else if (tileIndex > 0 && layerName != Const.MAP1DOORSANDCHESTS && layerName != Const.HOUSE1DOORSANDCHESTS) {
                        tileIndex = tileIndex - 1;
                        Tile newTile = new Tile(row, column, tileSetPicture.getSubimage(tileIndex % tileSetPictureColumns * Const.TILE_SIZE, tileIndex / tileSetPictureColumns * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE));
                        this.addTile(newTile, row, column);
                    }
                }
            }

            input.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    /** 
     * Method to add tiles
     * 
     * @param tile
     * @param row
     * @param column
     */
    public void addTile(Tile tile, int row, int column) {
        this.tiles[row][column] = tile;
    }
    
    /** 
     * Method to add collisionBoxes
     * 
     * @param newRect
     * @param row
     * @param column
     */
    public void addCollisionBox(Rectangle newRect, int row, int column) {
        this.collisionBoxes[row][column] = newRect;
    }
    
    /** 
     * @param newChest
     * @param row
     * @param column
     */
    // Method to add chests
    public void addChest(Chest newChest, int row, int column) {
        this.chests[row][column] = newChest;
    }
    
    /** 
     * @param newDoor
     * @param row
     * @param column
     */
    // Method to add doors
    public void addDoor(Door newDoor, int row, int column) {
        this.doors[row][column] = newDoor;
    }

     // Method to get chests
     public Chest[][] getChests() {
        return this.chests;
    }
    // Method to get collisionBoxes
    public Rectangle[][] getCollisionBoxes() {
        return collisionBoxes;
    }
    // Method to get doors
    public Door[][] getDoors() {
        return this.doors;
    }
    //------------------------------------------------------------------------------
    /** 
     * Method to draw tiles
     * 
     * @param g
     */
    public void draw(Graphics g) {
        for (int row = 0; row < this.tiles.length; row++) {
            for (int column = 0; column < this.tiles[row].length; column++) {
                if (this.tiles[row][column] != null) {
                    g.drawImage(this.tiles[row][column].getImg(), (column * Const.TILE_SIZE - game.getGameCamera().getXOffset()), (row * Const.TILE_SIZE - game.getGameCamera().getYOffset()), null);
                    // this.tiles[row][column].draw(g);
                }
            }
        }
    }
    
    /** 
     * Method to draw collision boxes
     * 
     * @param g
     */
    public void drawCollisionBoxes(Graphics g) {
        for (int row = 0; row < this.collisionBoxes.length; row++) {
            for (int column = 0; column < this.collisionBoxes[row].length; column++) {
                if (this.collisionBoxes[row][column] != null) {
                    g.setColor(Color.RED);
                    g.drawRect((column * Const.TILE_SIZE - game.getGameCamera().getXOffset()), (row * Const.TILE_SIZE - game.getGameCamera().getYOffset()), Const.TILE_SIZE, Const.TILE_SIZE);
                }
            }
        }
    }
    
    /** 
     * Method to draw chests
     * 
     * @param g
     */
    public void drawChests(Graphics g) {
        for (int row = 0; row < this.chests.length; row++) {
            for (int column = 0; column < this.chests[row].length; column++) {
                if (this.chests[row][column] != null) {
                    g.drawImage(this.chests[row][column].loadParentPicture(Const.DECORATIONS_TILESET), (column * Const.TILE_SIZE - game.getGameCamera().getXOffset()), (row * Const.TILE_SIZE - game.getGameCamera().getYOffset()), null);
                }
            }
        }
    }
    
    /** 
     * Method to draw doors
     * 
     * @param g
     */
    public void drawDoors(Graphics g) {
        for (int row = 0; row < this.doors.length; row++) {
            for (int column = 0; column < this.doors[row].length; column++) {
                if (this.doors[row][column] != null) {
                    g.drawImage(this.doors[row][column].loadParentPicture(Const.DOOR_TILESET), (column * Const.TILE_SIZE - game.getGameCamera().getXOffset()), (row * Const.TILE_SIZE - game.getGameCamera().getYOffset()), null);
                }
            }
        }
    }
}
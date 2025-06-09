import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The Menu class is a collection of different menu items that act as a GUI.
 * 
 * @see Game
 * @see MenuItem
 * @see Rext
 * @see Text
 * @see Image
 * @see Button
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Menu {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color bgColor;

    private MenuItem[] menuItems;
    //------------------------------------------------------------------------------     
    Menu() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.bgColor = Const.BLACK_COLOR;
    }
    Menu(int x, int y, int width, int height, Color bgColor, MenuItem[] menuItems) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.menuItems = menuItems;
    }
    //------------------------------------------------------------------------------ 
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public MenuItem getMenuItem(int index) {
        return this.menuItems[index];
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    //------------------------------------------------------------------------------   
    /** 
     * Draws the menu onto a Graphics panel.
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(this.bgColor);
        g.fillRect(this.x, this.y, this.width, this.height);

        for (int menuItemIndex = 0; menuItemIndex < menuItems.length; menuItemIndex++) {
            menuItems[menuItemIndex].draw(g);
        }
    }
    //------------------------------------------------------------------------------   
    /**
     * Resets the color to the original of all the buttons inside the menu.
     */
    public void resetButtons() {
        for (int menuItemIndex = 0; menuItemIndex < menuItems.length; menuItemIndex++) {
            if (menuItems[menuItemIndex] instanceof Button) {
                ((Button) menuItems[menuItemIndex]).resetColor();
            }
        }
    }
    //------------------------------------------------------------------------------   
    /** 
     * Find all the buttons inside the menu that collided with the passed coordinate.
     * @param x
     * @param y
     * @return ArrayList<Integer>
     */
    public ArrayList < Integer > findCollidedButtons(int x, int y) {
        ArrayList < Integer > collidedButtons = new ArrayList < Integer > ();

        for (int menuItemIndex = 0; menuItemIndex < menuItems.length; menuItemIndex++) {
            if (menuItems[menuItemIndex] instanceof Button) {
                Button menuButton = (Button) menuItems[menuItemIndex];

                if (menuButton.inside(x, y)) {
                    collidedButtons.add(menuItemIndex);
                }
            }
        }
        return collidedButtons;
    }
}
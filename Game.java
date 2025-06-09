import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The Game class is a class that is based on an original game by Reyab Saluja, Aztec: Demise. 
 * 
 * @see Player
 * @see Tile
 * @see Menu
 * @see MenuItem
 * @see TileSetLayer
 * @see Sound
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class Game {
    JFrame gameWindow;
    JPanel gamePanel;
    MenuMouseListener menuMouseListener;
    MenuMotionListener menuMotionListener;
    private MyKeyListener keyListener = new MyKeyListener();
    // map variables
    private String mapState = "HOUSE1";
    // game variables
    private String gameState = "STARTMENU";
    // game objects
    private TileMapLayer[] tileMapLayers = new TileMapLayer[6];
    // player
    private Player player = new Player(this, Const.WORLD_WIDTH / 2 + 100, Const.WORLD_HEIGHT / 2 - 70, Const.PLAYER_SPRITE_SHEET);
    // game camera
    private GameCamera gameCamera;
    // dialogue
    String[] dialogue = {
        "\n\nWe have long been expecting your arrival...",
        "\n\nYou may not know it yet, but you are the \nchosen one...\n\nYou are the one who will rid this world of\nthe evils that consume it..",
        "\n\nYou will face many challenges and opposition along your journey...\n\nSearch through each house in the village.\nFind the 3 chests of gold and collect all\nthe items within. Please hero, save us!"
    };
    String[] victoryDialogue = {
        "\n\nThank you so much brave hero! You have saved us all!"
    };
    int dialogueState = 0;
    private JTextArea dialogueTextArea;
    //-------------------------------------------------------------
    MenuItem[] startMenuItems = {
        new Image(0, 0, Const.TITLE_SCREEN),
        new Button(new Rect(0, 0, Const.RED_COLOR, 100, 50), new Text(0, 0, "START", Const.MEDIUM_START_FONT, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "DIALOGUE", Const.BLUE_COLOR, true, true),
        new Button(new Rect(0, 0, Const.BLUE_COLOR, 100, 50), new Text(0, 0, "CREDITS", Const.SMALL_START_FONT, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "CREDITS", Const.RED_COLOR, true, true),
        new Button(new Rect(0, 0, Const.BLUE_COLOR, 100, 50), new Text(0, 0, "CONTROLS", Const.SMALL_START_FONT, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "CONTROLS", Const.RED_COLOR, true, true)
    };
    Menu startMenu = new Menu(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, Color.BLACK, startMenuItems);

    MenuItem[] creditMenuItems = {
        new Text(0, 0, "Lead Programmer: Reyab Saluja", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Text(0, 0, "Lead Developer: Reyab Saluja", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Text(0, 0, "Lead Designer: Reyab Saluja", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Button(new Rect(0, 0, Color.BLACK, 100, 50), new Text(0, 0, "BACK", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "STARTMENU", Const.RED_COLOR, true, true)
    };
    Menu creditsMenu = new Menu(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, Color.BLACK, creditMenuItems);

    MenuItem[] controlsMenuItems = {
        new Image(100, 50, Const.ARROW_DOWN),
        new Image(100, 100, Const.ARROW_LEFT),
        new Image(100, 150, Const.ARROW_RIGHT),
        new Image(100, 200, Const.ARROW_UP),
        new Image(100, 250, Const.SPACE),
        new Text(0, 0, "MOVE DOWN", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Text(0, 0, "MOVE LEFT", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Text(0, 0, "MOVE RIGHT", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Text(0, 0, "MOVE UP", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Text(0, 0, "INTERACT", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Button(new Rect(0, 0, Color.BLACK, 100, 50), new Text(0, 0, "BACK", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "STARTMENU", Const.RED_COLOR, true, true)
    };
    Menu controlsMenu = new Menu(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, Color.BLACK, controlsMenuItems);

    MenuItem[] dialogueMenuItems = {
        new Text(0, 0, dialogue[dialogueState], Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, false),
        new Button(new Rect(0, 0, Color.BLACK, 100, 50), new Text(0, 0, "CONTINUE", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "NEXT_DIALOGUE", Const.RED_COLOR, true, true)
    };
    Menu dialogueMenu = new Menu(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, Color.BLACK, dialogueMenuItems);

    MenuItem[] victoryDialogueMenuItems = {
        new Text(0, 0, victoryDialogue[0], Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true),
        new Button(new Rect(0, 0, Color.BLACK, 100, 50), new Text(0, 0, "CONTINUE", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "VICTORY_SCREEN", Const.RED_COLOR, true, true)
    };
    Menu victoryDialogueMenu = new Menu(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, Color.BLACK, victoryDialogueMenuItems);

    MenuItem[] victoryScreenMenuItems = {
        new Image(0, 0, Const.WIN_SCREEN),
        new Button(new Rect(0, 0, Color.BLACK, 100, 50), new Text(0, 0, "QUIT GAME", Const.VSMALL_FONT_MENU, Const.WHITE_COLOR, true), MouseEvent.MOUSE_CLICKED, "END_GAME", Const.RED_COLOR, true, true)
    };
    Menu victoryScreenMenu = new Menu(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, Color.BLACK, victoryScreenMenuItems);
    //-------------------------------------------------------------
    Game() {
        updateMapState();
        Const.MUSIC.setFramePosition(0);
        Const.MUSIC.start();
        gameCamera = new GameCamera(this, 0, 0);
        gameWindow = new JFrame("Aztec: Demise");
        gameWindow.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(true);
        // initializing game panel
        gamePanel = new GraphicsPanel();
        gamePanel.setBackground(Const.BLACK_COLOR);
        gameWindow.add(gamePanel);
        // intializing key listener
        gamePanel.addKeyListener(keyListener);
        // Creating Mouse Listener
        menuMouseListener = new MenuMouseListener();
        gamePanel.addMouseListener(menuMouseListener);
        // Creating Mouse Motion Listener
        menuMotionListener = new MenuMotionListener();
        gamePanel.addMouseMotionListener(menuMotionListener);
        // setting window visible
        gameWindow.setVisible(true);

        // positioning start menu items
        ((Button) startMenuItems[1]).centerButton(Const.SCREEN_WIDTH / 2 - 12, Const.SCREEN_HEIGHT / 2 + 145);
        ((Button) startMenuItems[2]).centerButton(Const.SCREEN_WIDTH / 2 - 105, Const.SCREEN_HEIGHT / 2 + 220);
        ((Button) startMenuItems[3]).centerButton(Const.SCREEN_WIDTH / 2 + 90, Const.SCREEN_HEIGHT / 2 + 235);

        // poistioning credits menu items
        ((Text) creditMenuItems[0]).centerText(Const.SCREEN_WIDTH/2, Const.SCREEN_HEIGHT / 6);
        ((Text) creditMenuItems[1]).centerText(Const.SCREEN_WIDTH/2, Const.SCREEN_HEIGHT / 4);
        ((Text) creditMenuItems[2]).centerText(Const.SCREEN_WIDTH/2, Const.SCREEN_HEIGHT / 3);
        ((Button) creditMenuItems[3]).centerButton(Const.SCREEN_WIDTH / 2 - 10, Const.SCREEN_HEIGHT / 2 + 140);

        // positioning controls menu items
        ((Text) controlsMenuItems[5]).centerText(175, 57);
        ((Text) controlsMenuItems[6]).centerText(175, 107);
        ((Text) controlsMenuItems[7]).centerText(183, 157);
        ((Text) controlsMenuItems[8]).centerText(170, 207);
        ((Text) controlsMenuItems[9]).centerText(250, 257);
        ((Button) controlsMenuItems[10]).centerButton(Const.SCREEN_WIDTH / 2 - 10, Const.SCREEN_HEIGHT / 2 + 140);

        // positioning dialogue menu items
        ((Button) dialogueMenuItems[1]).centerButton(Const.SCREEN_WIDTH / 2 - 10, Const.SCREEN_HEIGHT / 2 + 140);

        // positioning victory dialogue menu items
        ((Text) victoryDialogueMenuItems[0]).centerText(Const.SCREEN_WIDTH/2 - 10, Const.SCREEN_HEIGHT / 4);
        ((Button) victoryDialogueMenuItems[1]).centerButton(Const.SCREEN_WIDTH / 2 - 10, Const.SCREEN_HEIGHT / 2 + 140);

        // positioning victory screen menu items
        ((Button) victoryScreenMenuItems[1]).centerButton(50, Const.SCREEN_HEIGHT - 15);
    }
    //-------------------------------------------------------------
    // main game loop
    public void run() {
        while (true) {
            if (gameState.equalsIgnoreCase("GAME"))
                player.update(this);
            try {
                gameWindow.repaint();
                Thread.sleep(Const.FRAME_PERIOD);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    //------------------------------------------------------------- 
    public class GraphicsPanel extends JPanel {
        GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // required
            if (gameState.equalsIgnoreCase("STARTMENU")) {
                startMenu.draw(g);
            } else if (gameState.equalsIgnoreCase("CREDITS")) {
                creditsMenu.draw(g);
            } else if (gameState.equalsIgnoreCase("CONTROLS")) {
                controlsMenu.draw(g);
            } else if (gameState.equalsIgnoreCase("DIALOGUE")) {
                if (Const.MUSIC.isRunning()) {
                    Const.MUSIC.stop();
                    Const.MUSIC.flush();
                }
                dialogueMenu.draw(g);
                player.setCoordinates(Const.SCREEN_WIDTH / 2 - 40, Const.SCREEN_HEIGHT / 2 - 100);
                player.setCharacterDirection(1); // direction down
                player.draw(g); // drawing player
            } else if (gameState.equalsIgnoreCase("GAME")) {
                // drawing map layers
                for (int layerIndex = 0; layerIndex < tileMapLayers.length; layerIndex++)
                    tileMapLayers[layerIndex].draw(g);
                // drawing chests
                for (int chestIndex = 0; chestIndex < tileMapLayers.length; chestIndex++) {
                    tileMapLayers[chestIndex].drawChests(g);
                }
                // drawing doors
                for (int doorIndex = 0; doorIndex < tileMapLayers.length; doorIndex++) {
                    tileMapLayers[doorIndex].drawDoors(g);
                }
                player.draw(g); // drawing player
                player.drawPopUpBox(g);
            } else if (gameState.equalsIgnoreCase("VICTORY_DIALOGUE")) {
                victoryDialogueMenu.draw(g);
            } else if (gameState.equalsIgnoreCase("VICTORY_SCREEN")) {
                victoryScreenMenu.draw(g);
            }
        }
    }
    //-------------------------------------------------------------
    public class MenuMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) { // listens to clicks
            int mouseX = e.getX();
            int mouseY = e.getY();
            // Click sound implementation
            Const.CLICK_SOUND.setFramePosition(0);
            Const.CLICK_SOUND.start();

            callRanButtons(mouseX, mouseY, MouseEvent.MOUSE_CLICKED);
        }
        public void mousePressed(MouseEvent e) { // MUST be implemented even if not used!
            int mouseX = e.getX();
            int mouseY = e.getY();

            callRanButtons(mouseX, mouseY, MouseEvent.MOUSE_PRESSED);
        }
        public void mouseReleased(MouseEvent e) { // MUST be implemented even if not used!
            int mouseX = e.getX();
            int mouseY = e.getY();

            callRanButtons(mouseX, mouseY, MouseEvent.MOUSE_RELEASED);
        }
        public void mouseEntered(MouseEvent e) { // MUST be implemented even if not used!
            int mouseX = e.getX();
            int mouseY = e.getY();

            callRanButtons(mouseX, mouseY, MouseEvent.MOUSE_ENTERED);
        }
        public void mouseExited(MouseEvent e) { // MUST be implemented even if not used!
            int mouseX = e.getX();
            int mouseY = e.getY();

            callRanButtons(mouseX, mouseY, MouseEvent.MOUSE_EXITED);
        }
    }
    //-------------------------------------------------------------
    public class MenuMotionListener implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) { // MUST be implemented even if not used!
        }
        public void mouseMoved(MouseEvent e) { // MUST be implemented even if not used!
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (!(gameState.equalsIgnoreCase("GAME"))) {
                hoverButtons(mouseX, mouseY);
            }
        }
    }
    //-------------------------------------------------------------
    /** 
    * Check if any button in the current game state menu is colliding with the mouse.
    * If so, change the button to its hover color, otherwise, reset the button to the original color.

    * @param mouseX
    * @param mouseY
    */
    public void hoverButtons(int mouseX, int mouseY) {
        if (gameState.equalsIgnoreCase("STARTMENU")) {
            startMenu.resetButtons();

            ArrayList < Integer > hoveredButtons = startMenu.findCollidedButtons(mouseX, mouseY);

            for (int hoveredButtonIndex: hoveredButtons) {
                Button hoveredButton = ((Button) startMenu.getMenuItem(hoveredButtonIndex));

                hoveredButton.hoveredColor();
            }
        } else if (gameState.equalsIgnoreCase("CREDITS")) {
            creditsMenu.resetButtons();

            ArrayList < Integer > hoveredButtons = creditsMenu.findCollidedButtons(mouseX, mouseY);

            for (int hoveredButtonIndex: hoveredButtons) {
                Button hoveredButton = ((Button) creditsMenu.getMenuItem(hoveredButtonIndex));

                hoveredButton.hoveredColor();
            }
        } else if (gameState.equalsIgnoreCase("CONTROLS")) {
            controlsMenu.resetButtons();

            ArrayList < Integer > hoveredButtons = controlsMenu.findCollidedButtons(mouseX, mouseY);

            for (int hoveredButtonIndex: hoveredButtons) {
                Button hoveredButton = ((Button) controlsMenu.getMenuItem(hoveredButtonIndex));

                hoveredButton.hoveredColor();
            }
        } else if (gameState.equalsIgnoreCase("DIALOGUE")) {
            dialogueMenu.resetButtons();

            ArrayList < Integer > hoveredButtons = dialogueMenu.findCollidedButtons(mouseX, mouseY);

            for (int hoveredButtonIndex: hoveredButtons) {
                Button hoveredButton = ((Button) dialogueMenu.getMenuItem(hoveredButtonIndex));

                hoveredButton.hoveredColor();
            }
        } else if (gameState.equalsIgnoreCase("VICTORY_DIALOGUE")) {
            victoryDialogueMenu.resetButtons();

            ArrayList < Integer > hoveredButtons = victoryDialogueMenu.findCollidedButtons(mouseX, mouseY);

            for (int hoveredButtonIndex: hoveredButtons) {
                Button hoveredButton = ((Button) victoryDialogueMenu.getMenuItem(hoveredButtonIndex));

                hoveredButton.hoveredColor();
            }
        } else if (gameState.equalsIgnoreCase("VICTORY_SCREEN")) {
            victoryScreenMenu.resetButtons();

            ArrayList < Integer > hoveredButtons = victoryScreenMenu.findCollidedButtons(mouseX, mouseY);

            for (int hoveredButtonIndex: hoveredButtons) {
                Button hoveredButton = ((Button) victoryScreenMenu.getMenuItem(hoveredButtonIndex));

                hoveredButton.hoveredColor();
            }
        }
    }
    //-------------------------------------------------------------
    /** 
     * Check if any of the button's in the current game state has been collided with and activated.
     * If so, run their custom button function. 
     * 
     * @param mouseX
     * @param mouseY
     * @param mouseEvent
     */
    public void callRanButtons(int mouseX, int mouseY, int mouseEvent) {
        if (gameState.equalsIgnoreCase("STARTMENU")) {
            ArrayList < Integer > ranButtons = startMenu.findCollidedButtons(mouseX, mouseY);

            for (int ranButtonIndex: ranButtons) {
                Button ranButton = ((Button) startMenu.getMenuItem(ranButtonIndex));
                if (ranButton.checkButtonType(mouseEvent)) {
                    runButtonFunction(ranButton.getButtonFunction());
                }
            }
        } else if (gameState.equalsIgnoreCase("CREDITS")) {
            ArrayList < Integer > ranButtons = creditsMenu.findCollidedButtons(mouseX, mouseY);

            for (int ranButtonIndex: ranButtons) {
                Button ranButton = ((Button) creditsMenu.getMenuItem(ranButtonIndex));
                if (ranButton.checkButtonType(mouseEvent)) {
                    runButtonFunction(ranButton.getButtonFunction());
                }
            }
        } else if (gameState.equalsIgnoreCase("CONTROLS")) {
            ArrayList < Integer > ranButtons = controlsMenu.findCollidedButtons(mouseX, mouseY);

            for (int ranButtonIndex: ranButtons) {
                Button ranButton = ((Button) controlsMenu.getMenuItem(ranButtonIndex));
                if (ranButton.checkButtonType(mouseEvent)) {
                    runButtonFunction(ranButton.getButtonFunction());
                }
            }
        } else if (gameState.equalsIgnoreCase("DIALOGUE")) {
            ArrayList < Integer > ranButtons = dialogueMenu.findCollidedButtons(mouseX, mouseY);

            for (int ranButtonIndex: ranButtons) {
                Button ranButton = ((Button) dialogueMenu.getMenuItem(ranButtonIndex));
                if (ranButton.checkButtonType(mouseEvent)) {
                    runButtonFunction(ranButton.getButtonFunction());
                }
            }
        } else if (gameState.equalsIgnoreCase("VICTORY_DIALOGUE")) {
            ArrayList < Integer > ranButtons = victoryDialogueMenu.findCollidedButtons(mouseX, mouseY);

            for (int ranButtonIndex: ranButtons) {
                Button ranButton = ((Button) victoryDialogueMenu.getMenuItem(ranButtonIndex));
                if (ranButton.checkButtonType(mouseEvent)) {
                    runButtonFunction(ranButton.getButtonFunction());
                }
            }
        } else if (gameState.equalsIgnoreCase("VICTORY_SCREEN")) {
            ArrayList < Integer > ranButtons = victoryScreenMenu.findCollidedButtons(mouseX, mouseY);

            for (int ranButtonIndex: ranButtons) {
                Button ranButton = ((Button) victoryScreenMenu.getMenuItem(ranButtonIndex));
                if (ranButton.checkButtonType(mouseEvent)) {
                    runButtonFunction(ranButton.getButtonFunction());
                }
            }
        } 
    }
    //-------------------------------------------------------------
    /** 
     * Contains the functionality of every custom button function.
     * 
     * @param buttonFunction
     */
    public void runButtonFunction(String buttonFunction) {
        if (buttonFunction.equalsIgnoreCase("GAME")) {
            updateMapState();
            gameState = "GAME";
            addTextArea(gamePanel, dialogue);
        } else if (buttonFunction.equalsIgnoreCase("CREDITS")) {
            Const.MUSIC.setFramePosition(0);
            Const.MUSIC.start();
            gameState = "CREDITS";
        } else if (buttonFunction.equalsIgnoreCase("CONTROLS")) {
            Const.MUSIC.setFramePosition(0);
            Const.MUSIC.start();
            gameState = "CONTROLS";
        } else if (buttonFunction.equalsIgnoreCase("DIALOGUE")) {
            gameState = "DIALOGUE";
            addTextArea(gamePanel, dialogue);
            System.out.println(dialogueState);
        } else if (buttonFunction.equalsIgnoreCase("NEXT_DIALOGUE")) {
            if (dialogueState < dialogue.length - 1) {
                dialogueState += 1;
                replaceTextArea(gamePanel, (dialogue[dialogueState]));
                System.out.println(dialogueState);
            } else if (dialogueState == dialogue.length - 1) {
                replaceTextArea(gamePanel, "");
                player.setCoordinates(Const.SCREEN_WIDTH / 2 - 10, Const.SCREEN_HEIGHT / 2 -100);
                gameState = "GAME";
            }
        } else if (buttonFunction.equalsIgnoreCase("STARTMENU")) {
            gameState = "STARTMENU";
            
        } else if (buttonFunction.equalsIgnoreCase("VICTORY_SCREEN")) {
            gameState = "VICTORY_SCREEN";
        } else if (buttonFunction.equalsIgnoreCase("END_GAME")) {
            gameWindow.dispose();
        }
    }
    //-------------------------------------------------------------
    // loading chest objects
    public void loadObjectsMap1() {
        // map layers
        tileMapLayers[0] = new TileMapLayer(this, Const.MAP1LAYER1, Const.MAIN_TILESET); // layer 1
        tileMapLayers[1] = new TileMapLayer(this, Const.MAP1SOLIDLAYER, Const.MAIN_TILESET); // solid layer
        tileMapLayers[2] = new TileMapLayer(this, Const.MAP1LAYER2, Const.MAIN_TILESET); // layer 2
        tileMapLayers[3] = new TileMapLayer(this, Const.MAP1LAYER3, Const.MAIN_TILESET); // layer 3
        tileMapLayers[4] = new TileMapLayer(this, Const.MAP1SOLIDLAYER2, Const.MAIN_TILESET);
        tileMapLayers[5] = new TileMapLayer(this, Const.MAP1DOORSANDCHESTS, Const.DECORATIONS_TILESET);
    }
    public void loadObjectsHouse1() {
        // map layers
        tileMapLayers[0] = new TileMapLayer(this, Const.HOUSE1LAYER1, Const.INDOOR_TILESET);
        tileMapLayers[1] = new TileMapLayer(this, Const.HOUSE1SOLIDLAYER, Const.INDOOR_TILESET); // solid layer
        tileMapLayers[2] = new TileMapLayer(this, Const.HOUSE1LAYER1, Const.INDOOR_TILESET); // layer 2
        tileMapLayers[3] = new TileMapLayer(this, Const.HOUSE1LAYER1, Const.INDOOR_TILESET); // layer 3
        tileMapLayers[4] = new TileMapLayer(this, Const.HOUSE1LAYER1, Const.INDOOR_TILESET); // layer 3
        tileMapLayers[5] = new TileMapLayer(this, Const.HOUSE1DOORSANDCHESTS, Const.DECORATIONS_TILESET);
    }
    public void loadObjectsMaze() {
        tileMapLayers[0] = new TileMapLayer(this, Const.MAZELAYER1, Const.DUNGEON_TILESET);
        tileMapLayers[1] = new TileMapLayer(this, Const.MAZESOLIDLAYER, Const.DUNGEON_TILESET); // solid layer
        tileMapLayers[2] = new TileMapLayer(this, Const.MAZESOLIDLAYER, Const.DUNGEON_TILESET);
        tileMapLayers[3] = new TileMapLayer(this, Const.MAZESOLIDLAYER, Const.DUNGEON_TILESET);
        tileMapLayers[4] = new TileMapLayer(this, Const.MAZESOLIDLAYER, Const.DUNGEON_TILESET);
        tileMapLayers[5] = new TileMapLayer(this, Const.MAZE_DOORS_AND_CHESTS, Const.DECORATIONS_TILESET);
    }
    public void loadObjectsDungeon() {
        tileMapLayers[0] = new TileMapLayer(this, Const.DUNGEON_LAYER_1, Const.DUNGEON_TILESET);
        tileMapLayers[1] = new TileMapLayer(this, Const.DUNGEON_SOLID_LAYER_1, Const.DUNGEON_TILESET);
        tileMapLayers[2] = new TileMapLayer(this, Const.DUNGEON_SOLID_LAYER_2, Const.DUNGEON_TILESET);
        tileMapLayers[3] = new TileMapLayer(this, Const.DUNGEON_SOLID_LAYER_2, Const.DUNGEON_TILESET);
        tileMapLayers[4] = new TileMapLayer(this, Const.DUNGEON_SOLID_LAYER_2, Const.DUNGEON_TILESET);
        tileMapLayers[5] = new TileMapLayer(this, Const.DUNGEON_DOORS_AND_CHESTS, Const.DECORATIONS_TILESET);
    }

    public void updateMapState() {
        // loading all objects
        if (mapState.equalsIgnoreCase("MAP1"))
            loadObjectsMap1();
        else if (mapState.equalsIgnoreCase("HOUSE1"))
            loadObjectsHouse1();
        else if (mapState.equalsIgnoreCase("MAZE"))
            loadObjectsMaze();
        else if (mapState.equalsIgnoreCase("DUNGEON"))
            loadObjectsDungeon();
    }

    
    /** 
     * Method to add a JTextArea to the panel to display the dialogue
     * 
     * @param panel
     * @param d
     */
    public void addTextArea(JPanel panel, String[] d) {
        if (gameState == "DIALOGUE" || gameState == "NEXT_DIALOGUE" || gameState == "VICTORY_DIALOGUE") {
            dialogueTextArea = new JTextArea();
            dialogueTextArea.setBounds(0, 0, Const.SCREEN_WIDTH - 100, 100);
            dialogueTextArea.setLineWrap(true);
            dialogueTextArea.setEditable(false);
            dialogueTextArea.setOpaque(false);
            dialogueTextArea.setFont(Const.VSMALL_FONT_MENU);
            dialogueTextArea.setText(d[0]);
            dialogueTextArea.setForeground(Color.WHITE);
            gamePanel.add(dialogueTextArea);
        }
    }

    
    /** 
     * Method to delete and replace the text in the text arae
     * 
     * @param panel
     * @param newText
     */
    public void replaceTextArea(JPanel panel, String newText) {
        dialogueTextArea.selectAll();
        dialogueTextArea.replaceSelection(newText);
    }

    // getters
    public MyKeyListener getKeyListener() {
        return keyListener;
    }
    public GameCamera getGameCamera() {
        return gameCamera;
    }
    public String getGameState() {
        return gameState;
    }
    public TileMapLayer[] getTileMapLayers() {
        return tileMapLayers;
    }
    public JPanel getGamePanel() {
        return gamePanel;
    }
    // setters
    public void setMapState(String newMapState) {
        mapState = newMapState;
    }
    public void setGameState(String newGameState) {
        gameState = newGameState;
    }
    //------------------------------------------------------------- 
    public static void main(String[] args) {
        Game demo = new Game();
        demo.run();
    }
}
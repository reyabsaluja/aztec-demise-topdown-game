import java.awt.Font;
import java.awt.Color;

public class Const {
    // world settings
    public static final int TILE_SIZE = 32; // 32x32 tile
    public static final int COLUMNS = 30; // number of columns of tiles
    public static final int ROWS = 30; // number of rows of tiles
    public static final int WORLD_WIDTH = COLUMNS * TILE_SIZE;
    public static final int WORLD_HEIGHT = ROWS * TILE_SIZE;

    // screen settings
    public static final int CURRENT_ROWS = 16;
    public static final int CURRENT_COLUMNS = 15;
    public static final int SCREEN_WIDTH = (CURRENT_COLUMNS * TILE_SIZE) + 17;
    public static final int SCREEN_HEIGHT = (CURRENT_ROWS * TILE_SIZE) + 10;

    // player settings
    public static final int FPS = 30;
    public static final int FRAME_PERIOD = 1000 / 20;

    // tilesets
    public static final String MAIN_TILESET = "Images/tilesets/tileset.png";
    public static final String DECORATIONS_TILESET = "Images/tilesets/decorations.png";
    public static final String DOOR_TILESET = "Images/tilesets/doors.png";
    public static final String INDOOR_TILESET = "Images/tilesets/indoortileset.png";
    public static final String DUNGEON_TILESET = "Images/tilesets/dungeontileset.png";
    public static final String ARROW = "Images/tilesets/arrow.png";

    // spritesheets
    public static final String PLAYER_SPRITE_SHEET = "Images/spritesheets/hero1.png";

    // other images
    public static final String MAP_IMAGE = "Images/other/MapTabImage.png";
    public static final String TITLE_SCREEN = "Images/other/title_screen.png";
    public static final String WIN_SCREEN = "Images/other/winscreen.png";
    // keys
    public static final String ARROW_DOWN = "Images/other/ARROWDOWN.png";
    public static final String ARROW_LEFT = "Images/other/ARROWLEFT.png";
    public static final String ARROW_RIGHT = "Images/other/ARROWRIGHT.png";
    public static final String ARROW_UP = "Images/other/ARROWUP.png";
    public static final String SPACE = "Images/other/SPACE.png";

    // map1 layers
    public static String MAP1LAYER1 = "MapLayers/MAP1/MAP1Layer1.txt";
    public static String MAP1LAYER2 = "MapLayers/MAP1/MAP1Layer2.txt";
    public static String MAP1LAYER3 = "MapLayers/MAP1/MAP1Layer3.txt";
    public static String MAP1SOLIDLAYER = "MapLayers/MAP1/MAP1SolidLayerTiles.txt";
    public static String MAP1DOORSANDCHESTS = "MapLayers/MAP1/MAP1DOORSANDCHESTS.txt";
    public static String MAP1SOLIDLAYER2 = "MapLayers/MAP1/MAP1SOLIDLAYER2.txt";

    // HOUSE1 layers
    public static String HOUSE1LAYER1 = "MapLayers/HOUSE1/HOUSE1LAYER1.txt";
    public static String HOUSE1SOLIDLAYER = "MapLayers/HOUSE1/HOUSE1SOLIDLAYER.txt";
    public static String HOUSE1DOORSANDCHESTS = "MapLayers/HOUSE1/HOUSE1DOORSANDCHESTS.txt";

    // maze layers
    public static String MAZELAYER1 = "MapLayers/MAZE/MAZELAYER1.txt";
    public static String MAZESOLIDLAYER = "MapLayers/MAZE/MAZESOLIDLAYER.txt";
    public static String MAZE_DOORS_AND_CHESTS = "MapLayers/MAZE/MAZEDOORSANDCHESTS.txt";

    // dungeon layers
    public static String DUNGEON_LAYER_1 = "MapLayers/DUNGEON/DUNGEONLAYER1.txt";
    public static String DUNGEON_SOLID_LAYER_1 = "MapLayers/DUNGEON/DUNGEONSOLIDLAYER1.txt";
    public static String DUNGEON_SOLID_LAYER_2 = "MapLayers/DUNGEON/DUNGEONSOLIDLAYER2.txt";
    public static String DUNGEON_DOORS_AND_CHESTS = "MapLayers/DUNGEON/DUNGEONDOORSANDCHESTS.txt";

    // texture coordinate settings

    // chests
    public static final int BRONZE_CHEST_POS = 128;
    public static final int RED_CHEST_POS = 96;
    public static final int BLUE_CHEST_POS = 64;
    // doors
    public static final int BRONZE_DOOR_POS = 0;
    public static final int BLUE_DOOR_POS = 32;
    public static final int RED_DOOR_POS = 64;
    public static final int PINK_DOOR_POS = 96;

    // items
    public static final String KEY1 = "KEY1";
    public static final String KEY2 = "KEY2";
    public static final String KEY3 = "KEY3";
    public static final String KEY4 = "KEY4";
    public static final String KEY5 = "KEY5";

    // places

    // houses
    public static final String HOUSE1 = "HOUSE1";
    public static final String HOUSE2 = "HOUSE2";

    // fonts
    private static final Font MENU_FONT = CustomFont.loadFont("Fonts/FutureForcesCondensed-4AMl.otf", 10);
    private static final Font START_MENU_FONT = CustomFont.loadFont("Fonts/Azonix.otf", 10);

    public static final Font MEDIUM_START_FONT = START_MENU_FONT.deriveFont(32f);
    public static final Font SMALL_START_FONT = START_MENU_FONT.deriveFont(27f);

    public static final Font SMALL_FONT_MENU = MENU_FONT.deriveFont(60f);
    public static final Font VSMALL_FONT_MENU = MENU_FONT.deriveFont(20f);

    // colours
    public static final Color BLACK_COLOR = Color.BLACK;
    public static final Color WHITE_COLOR = Color.WHITE;
    public static final Color RED_COLOR = new Color(203, 33, 39);
    public static final Color BLUE_COLOR = new Color(15, 82, 127);

    // sounds
    public static final Sound CLICK_SOUND = new Sound("Audio/Click.wav");
    public static final Sound MUSIC = new Sound("Audio/Music.wav");
}
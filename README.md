# Aztec: Demise

_A 2D top-down adventure game by Reyab Saluja_

<img width="906" alt="Screenshot 2025-06-08 at 3 28 26 PM" src="https://github.com/user-attachments/assets/3a2a749e-5d82-4e64-bc51-8f3ba9014324" />


## Overview

**Aztec: Demise** is a Java-based, single-player adventure game where you play as the chosen hero destined to save a village from evil. Explore houses, dungeons, and mazes, solve puzzles, collect keys, unlock chests, and interact with the world to uncover the story and achieve victory.

## Features

- **Classic Top-Down Gameplay:** Move, run, and interact with the world using intuitive keyboard controls.
- **Multiple Levels:** Explore a village, houses, a maze, and a dungeon, each with unique layouts and challenges.
- **Puzzle Elements:** Find keys, unlock doors and chests, and collect items to progress.
- **Narrative Dialogue:** Experience a story-driven introduction and victory sequence.
- **Custom Graphics & Audio:** Includes original pixel art, custom fonts, and background music.
- **Menus:** Start menu, credits, controls, and victory screens.

## Controls

| Action      | Key(s)                |
|-------------|-----------------------|
| Move Up     | W or Up Arrow         |
| Move Down   | S or Down Arrow       |
| Move Left   | A or Left Arrow       |
| Move Right  | D or Right Arrow      |
| Run         | R                     |
| Interact    | Spacebar              |

## How to Play

1. **Start the Game:** Launch the game and select "START" from the menu.
2. **Explore:** Use the movement keys to navigate the world.
3. **Interact:** Stand near chests or doors and press `Spacebar` to interact.
4. **Collect Items:** Find and collect keys and items from chests to unlock new areas.
5. **Progress:** Solve puzzles and explore all areas to collect the three main keys.
6. **Win:** Once all keys are collected, complete the final challenge to save the village!

## Project Structure

- `Game.java` - Main game logic and loop
- `Player.java` - Player character and controls
- `Menu.java`, `MenuItem.java`, `Button.java`, `Text.java`, `Image.java` - UI components
- `TileMapLayer.java`, `Tile.java` - Map and level structure
- `Audio/` - Sound and music files
- `Images/` - Sprites, tilesets, and UI images
- `Fonts/` - Custom fonts
- `Dialogue/` - Dialogue text files
- `MapLayers/` - Level data for each area

## Requirements

- Java 8 or higher
- A system capable of running Java Swing applications

## Running the Game

1. Clone or download this repository.
2. Ensure you have Java installed (`java -version`).
3. Compile the game:
   ```sh
   javac Game.java
   ```
4. Run the game:
   ```sh
   java Game
   ```

## Credits

- **Lead Programmer/Developer/Designer:** Reyab Saluja

## License

This project is for educational purposes. Please contact the author for other uses.

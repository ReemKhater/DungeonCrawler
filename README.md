# Dungeon Crawler Game

This project aims to implement the basic foundations of a small dungeon crawler game, focusing on basic physics, sprite movement, and collision management. The game includes a dungeon layout and sprites that can move within the dungeon, interacting with obstacles and traps.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Gameplay Mechanics](#gameplay-mechanics)
- [Code Structure](#code-structure)
- [Acknowledgments](#acknowledgments) 

---

## Project Overview

The goal of this project is to simulate a dungeon where a hero sprite can move around and interact with various objects, such as traps and solid obstacles. The game incorporates basic physics, including collision detection between the hero sprite and other objects in the dungeon. The physics engine manages sprite movements and interactions, while the render engine handles the graphical output of the game.

### Development Challenges

This game was built with the following objectives:

1. **Static Dungeon Display**: The dungeon is displayed statically, and the sprites move within the dungeon.
2. **Sprite Movement**: The player-controlled hero sprite can move within the dungeon and interact with the environment.
3. **Basic Physics**: The game engine models basic physics, including constant speed and collision detection with objects like traps and solid sprites.

---

## Features

- **Hero Sprite Movement**: The player controls the hero sprite, which moves around the dungeon.
- **Collision Detection**: The physics engine detects and handles collisions between dynamic sprites (e.g., the hero) and solid objects (e.g., traps).
- **Trap Mechanics**: Traps cause health loss when the hero collides with them.
- **Health Indicator**: A health bar tracks the hero's remaining health and triggers a game-over when health reaches zero. The player can press start to restart the game. 
- **Static Dungeon**: The dungeon layout is pre-defined and displayed.

---

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/DungeonCrawler.git
   ```
2. **Navigate into the project directory**:
```bash
cd dungeon-crawler
```

3. **Compile the project (assuming you're using a standard Java setup)**:
```bash
javac *.java
```

4. **Run the game**:
```bash
java Main
```

---

## Usage 

1. **Starting the Game**: After running the game, the hero sprite will be visible on the screen and can be moved with the arrow keys.
2. **Interacting with Traps**: The hero sprite will lose health when colliding with traps, which are represented as solid objects in the game.
3. **Health Indicator**: The health indicator will decrease as the hero collides with traps. If the health reaches zero, the game will end.

--- 

## Gameplay Mechanics

1. **Movement**: The hero sprite can move in four directions: North, South, East, and West. The movement is handled by the DynamicSprite class, which manages the hero’s position and direction.
2. **Collision Detection**: The PhysicEngine is responsible for handling all collision detections. It checks if the hero sprite collides with any traps or solid objects, and it triggers appropriate actions (such as reducing health on trap collision).
3. **Health**: The HealthIndicator class manages the player's health. If health reaches zero, the game triggers a "Game Over" message.

---

## Code Structure 

This project follows a modular structure with different engines and sprite classes:

- **Engines**:
  - **RenderEngine**: Handles all the graphical rendering of static and animated sprites.
  - **PhysicEngine**: Manages physics, including sprite movement and collision detection.
  - **GameEngine**: Handles user input and game interactions.

- **Sprites**:
  - **Sprite**: Represents decorative elements without physical properties.
  - **SolidSprite**: Represents objects that interact physically with the hero, like traps or walls.
  - **DynamicSprite**: Represents animated elements that can move and interact, such as the hero sprite.

- **Additional Classes**:
  - **HealthIndicator**: Tracks and updates the hero's health.
  - **Direction**: Enumerates the four cardinal directions used for sprite movement.
  - **Main**: The entry point for starting the game.
 
---

## Acknowledgments

This project is based on a class exercise and partially uses code provided by my professor, Antoine Tauvel, as part of the course material. The original code and additional resources can be found in the [official repository](https://github.com/antoineTauvelENSEA/FISE_2024_2025_Dungeon_Crawler).

I would like to thank Mr Tauvel for the guidance and the tutorial, which served as a foundation for the development of this project.


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;

    // Instances
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    GameOver gameOver;
    HealthIndicator healthIndicator;
    DynamicSprite hero; // Manage hero directly in Main

    // Game state
    boolean isGameOver = false;

    // Timers
    Timer renderTimer;
    Timer gameTimer;
    Timer physicTimer;

    // Constructor
    public Main() throws Exception {
        // Initialize JFrame
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(1920, 1080);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize game components
        hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        healthIndicator = new HealthIndicator(100, 10, 10, 200, 20); // Max health: 100
        renderEngine = new RenderEngine(displayZoneFrame, healthIndicator);
        physicEngine = new PhysicEngine(healthIndicator);
        gameEngine = new GameEngine(hero, healthIndicator, displayZoneFrame.getWidth(), displayZoneFrame.getHeight());
        gameOver = new GameOver(400, 600, this::resetGame); // Pass resetGame as callback

        // Initialize timers
        initializeTimers();

        // Start timers
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        // Set up JFrame and components
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        // Load the level
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    // Initialize timers
    private void initializeTimers() {
        renderTimer = new Timer(50, (time) -> {
            if (isGameOver) {
                gameOver.repaint(); // Render the Game Over screen
            } else {
                renderEngine.update(); // Continue normal rendering if the game is not over
            }
        });

        gameTimer = new Timer(50, (time) -> {
            if (!isGameOver) {
                gameEngine.update();
            }
        });

        physicTimer = new Timer(50, (time) -> {
            if (!isGameOver) {
                physicEngine.update();
                System.out.println("Current Health: " + healthIndicator.getCurrentHealth());
                if (healthIndicator.isDead()) {
                    System.out.println("Health is 0. Triggering Game Over...");
                    triggerGameOver(); // Transition to Game Over
                }
            }
        });
    }

    // Trigger Game Over state
    private void triggerGameOver() {
        if (!isGameOver) { // Ensure it runs only once
            isGameOver = true; // Update game state
            stopTimers(); // Stop all timers
            displayZoneFrame.removeKeyListener(gameEngine); // Disable game controls

            // Switch to Game Over panel
            displayZoneFrame.getContentPane().removeAll(); // Remove current content
            displayZoneFrame.getContentPane().add(gameOver); // Add Game Over panel
            displayZoneFrame.revalidate(); // Refresh the frame
            displayZoneFrame.repaint(); // Trigger re-render
            displayZoneFrame.addKeyListener(gameOver); // Add Game Over controls
            System.out.println("Game Over triggered.");
        }
    }

    // Reset the game
    private void resetGame() {
        try {
            isGameOver = false; // Reset game state

            // Restart health, engines, and levels
            healthIndicator = new HealthIndicator(100, 10, 10, 200, 20);
            renderEngine = new RenderEngine(displayZoneFrame, healthIndicator);
            physicEngine = new PhysicEngine(healthIndicator);
            hero = new DynamicSprite(200, 300,
                    ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
            gameEngine = new GameEngine(hero, healthIndicator, displayZoneFrame.getWidth(), displayZoneFrame.getHeight());

            // Reload the level
            Playground level = new Playground("./data/level1.txt");
            renderEngine.addToRenderList(level.getSpriteList());
            renderEngine.addToRenderList(hero);
            physicEngine.addToMovingSpriteList(hero);
            physicEngine.setEnvironment(level.getSolidSpriteList());

            // Reset event listeners
            displayZoneFrame.getContentPane().removeAll(); // Clear all components
            displayZoneFrame.getContentPane().add(renderEngine); // Add the main game panel
            displayZoneFrame.removeKeyListener(gameOver);
            displayZoneFrame.addKeyListener(gameEngine);

            // Restart timers
            initializeTimers();
            renderTimer.start();
            gameTimer.start();
            physicTimer.start();

            displayZoneFrame.revalidate(); // Refresh the frame
            displayZoneFrame.repaint(); // Redraw the frame
            System.out.println("Game has been reset!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Stop all timers
    private void stopTimers() {
        if (renderTimer != null) renderTimer.stop();
        if (gameTimer != null) gameTimer.stop();
        if (physicTimer != null) physicTimer.stop();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

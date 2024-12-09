import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    //instances
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    GameOver gameOver;
    HealthIndicator healthIndicator = new HealthIndicator(100, 10, 10, 200, 20); // Max health: 100, at top-left corner


    //game state
    boolean isGameOver=false;

    //constructor
    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        //Initialization of renderEngine, physicEngine and gameEngine
        renderEngine = new RenderEngine(displayZoneFrame,healthIndicator);
        physicEngine = new PhysicEngine(healthIndicator);
        gameEngine = new GameEngine(hero,healthIndicator);
        gameOver = new GameOver();

        //Initialization of the timers
        Timer renderTimer = new Timer(50,(time)->{
            if (isGameOver){
                gameOver.draw(renderEngine.getGraphics()); //draw the Game Over screen
            }
            else {
                renderEngine.update(); //continue normal rendering if the game is not over
            }
        });
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
	// write your code here
        Main main = new Main();
    }
}

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;

    //instance
    private HealthIndicator healthIndicator;

    public GameEngine(DynamicSprite hero, HealthIndicator healthIndicator) {
        this.hero = hero;
        this.healthIndicator = healthIndicator;
    }

    @Override
    public void update() {
        if (healthIndicator.isDead()) {
            //Trigger Game Over logic
            triggerGameOver();
        }
    }

    private void triggerGameOver() {
        System.out.println("Game Over");
        GameOver gameOver = new GameOver();
        gameOver.displayGameOver();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

import javax.swing.*;
import java.awt.*;

public class GameOver {
    private int width;
    private int height;

    //constructor
    public GameOver() {
        this.width = width;
        this.height = height;
    }

    public void displayGameOver(){
        JFrame gameOverFrame = new JFrame("Game Over");
        JLabel label = new JLabel("You died. Game Over.", SwingConstants.CENTER );
        label.setFont(label.getFont().deriveFont(32.0f));
        gameOverFrame.add(label);
        gameOverFrame.setSize(400, 200);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setVisible(true);
    }

    public void draw(Graphics g) {
        // Clear the screen and set black background color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // Display "Game Over" text in white
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("GAME OVER", width / 2 - 150, height / 2);
    }
}

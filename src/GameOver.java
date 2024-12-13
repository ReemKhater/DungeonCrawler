import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOver extends JPanel implements KeyListener {
    private int width;
    private int height;
    private Runnable resetCallback;

    public GameOver(int width, int height, Runnable resetCallback) {
        this.width = width;
        this.height = height;
        this.resetCallback = resetCallback;

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over", width / 2 - 100, height / 2 - 50);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Press Enter to Restart", width / 2 - 100, height / 2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (resetCallback != null) {
                resetCallback.run(); // Trigger the reset logic
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

import java.awt.*;

public class HealthIndicator {
    private int maxHealth;
    private int currentHealth;
    private int x, y, width, height; // Position and dimensions of the health bar

    // Constructor
    public HealthIndicator(int maxHealth, int x, int y, int width, int height) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Method to decrease health
    public void decreaseHealth(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            currentHealth = 0; //ensure health doesn't go below zero
        }
    }

    // Method to draw the health bar
    public void draw(Graphics g) {
        // Draw the background bar (gray)
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        // Draw the current health (green)
        g.setColor(Color.RED);
        int currentWidth = (int) ((double) currentHealth / maxHealth * width);
        g.fillRect(x, y, currentWidth, height);

        // Optional: Add a border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    // Getters and Setters (if needed)
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public int getHealth(){
        return currentHealth;
    }
}

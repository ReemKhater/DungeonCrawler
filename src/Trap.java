import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Trap extends Sprite {
    private final int damage; // Health reduction value

    public Trap(double x, double y, Image image, double width, double height, int damage) {
        super(x, y, image, width, height); //call the sprite constructor
        this.damage = damage;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public int getDamage() {
        return damage;
    }
}


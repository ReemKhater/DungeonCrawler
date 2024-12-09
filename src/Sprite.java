import java.awt.*;
import java.awt.geom.*;

public class Sprite implements Displayable{
    protected double x;
    protected double y;
    protected final Image image;
    protected final double width;
    protected final double height;

    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    //Method to get the sprite's hitbox
    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double((int) x, (int) y, (int) width, (int) height);
    }

    //method to check if the sprite collides with another sprite
    public boolean collidesWith(Sprite other) {
        return this.getHitBox().intersects(other.getHitBox());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x,(int)y,null);
    }
}

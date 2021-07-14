import java.awt.*;

public class Crate extends GameObject {
    public Crate(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}

import java.awt.*;

public class Tutorial extends GameObject{
    private final Game game;

    public Tutorial(int x, int y, ID id, SpriteSheet ss, Game game) {
        super(x, y, id, ss);
        this.game = game;
    }

    public void tick() {

    }


    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Draw shadow
        float alpha = 0.3f;
        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        Font h = new Font("TimesRoman", Font.PLAIN, 15);
        g.setColor(Color.white);
        g.setFont(h);
        g.drawImage(game.wasd, x+100, y+100, 200, 200, null);
        g.drawImage(game.fv, x+700, y+130, 160, 160, null);
        g.drawImage(game.mouse, x+100, y+400, 160, 160, null);
        g.drawImage(game.helm, x+700, y+400, 160, 160, null);


        alpha = 1f;
         alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        g.drawString("Used WASD to move the character", x+100, y+310);
        g.drawString("Press F to use an HP pot", x+700, y+310);
        g.drawString("Press V to use an MP pot", x+700, y+330);
        g.drawString("Right or Left Click to shoot", x+100, y+590);
        g.drawString("Press Space to activate helm ability", x+680, y+580);

    }

    public Rectangle getBounds() {
        return null;
    }
}

import java.awt.*;
import java.awt.image.BufferedImage;

public class RedKnight extends GameObject {
    private final Game game;
    private final Handler handler;
    private final Sound sound;
    private int bounds = 96;
    private int ticks = 0;
    private boolean regen = true;
    private int sprnum = 0;
    private BufferedImage cSprite;
    public RedKnight(int x, int y, ID id, SpriteSheet ss, Game game, Handler handler, Sound sound) {
        super(x, y, id, ss);
        this.game = game;
        this.handler = handler;
        this.sound = sound;
    }
    private void MoveToMiddle(int ticks){
        if(ticks < 90) {
            velY = 0;
            velX = 3;
        } else {
            velX = 0;
        }
    }
    private void firstPhase(int ticks){
        int b2 = bounds/2-10;

        if(ticks % 60 == 0 ){
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+0+b2, y+100+b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+100+b2, y+0+b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+-100+b2, y+0+b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+0+b2, y+-100+b2, ss, game, sound, 10));
        } else if (ticks % 30 == 0){
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+50+b2, y+50+b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+-50+b2, y+50+b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+50+b2, y+-50+b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+-50+b2, y+-50+b2, ss, game, sound, 10));
        }
    }
    private void extraDamageFirstPhasae(int ticks){
        int b2 = bounds/2-10;

        if(ticks % 60 == 0 ){
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+0+b2, y+100+b2, ss, game, sound, 13));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+100+b2, y+0+b2, ss, game, sound, 13));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+-100+b2, y+0+b2, ss, game, sound, 13));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+0+b2, y+-100+b2, ss, game, sound, 13));
        } else if (ticks % 30 == 0){
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+50+b2, y+50+b2, ss, game, sound, 13));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+-50+b2, y+50+b2, ss, game, sound, 13));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+50+b2, y+-50+b2, ss, game, sound, 13));
            handler.addObject(new MiniShots(x+b2, y+b2, ID.MINISHOT, handler, x+-50+b2, y+-50+b2, ss, game, sound, 13));
        }
    }
    private void chase(){
        velX = Math.signum(game.getPx() -x )*3;
        velY = Math.signum(game.getPy() -y)*3;
    }
    private void regenHP(){
        if(regen){
            game.setRedHP(game.getRedHP()+25);
        }
        if(game.getRedHP() >= 750){
            regen = false;
        }
    }
private void aura(int ticks){
    int b2 = bounds/2-10;

    if(ticks % 20 == 0 ){
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+0+b2, y+100+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+100+b2, y+0+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+-100+b2, y+0+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+0+b2, y+-100+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+50+b2, y+50+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+-50+b2, y+50+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+50+b2, y+-50+b2, ss, game, 30, 0));
        handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+-50+b2, y+-50+b2, ss, game, 30, 0));
    }
}

    public void tick() {
        ticks++;
        MoveToMiddle(ticks);
        if(game.isPurpleAlive()) {
            if (game.getRedHP() >= 500) {
                firstPhase(ticks);
            } else if (game.getRedHP() <= 500) {
                chase();
                aura(ticks);
            }
        } else {
            if(bounds < 112){
                bounds++;
            }
            regenHP();
            aura(ticks);
            chase();
        }
        x+= velX;
        y+= velY;
        if(game.getRedHP() <= 0){
            sound.playSound("Rocks_death.wav");
            game.setRedAlive(false);
            handler.removeObject(this);
        }
        if(Math.abs(velX) > 0 || Math.abs(velY) > 0){
            if(ticks % 20 == 0) {
                sprnum++;
            }
            if(sprnum > 2){
                sprnum = 0;
            }
            cSprite = game.RedM[sprnum];
        } else {
            if(ticks % 10 == 0) {
                sprnum++;
            }
            if(sprnum > 3){
                sprnum = 0;
            }
            cSprite = game.RedSpin[sprnum];
        }



    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Draw shadow
        float alpha = 0.3f;
        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        g2d.drawImage(game.shadow, x, y+10, bounds, bounds, null);
        alpha = 1f;
        alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        g.drawImage(cSprite, x, y+10, bounds, bounds, null);
        //Health Bar
        if(game.isPurpleAlive()) {
            g.setColor(Color.red);
            g.fillRect(x, y + bounds, 100, 5);
            g.setColor(Color.green);
            g.fillRect(x, y + bounds, game.getRedHP() / 10, 5);
        } else {
            g.setColor(Color.red);
            g.fillRect(x+25, y + bounds, 75, 5);
            g.setColor(Color.green);
            g.fillRect(x+25, y + bounds, game.getRedHP() / 10, 5);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, bounds, bounds);

    }
}

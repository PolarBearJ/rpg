import java.awt.*;
import java.awt.image.BufferedImage;

public class PurpleKnight extends GameObject {
    private final Game game;
    private final Handler handler;
    private final Sound sound;
    private int bounds = 96;
    private int ticks = 0;
    private int side = 0; //0 = right 1 = down 2 = left 3 = up
    private boolean regen = true;
   private int dirx = 100000;
    private int diry = 100000;
    private int sprnum = 0;
    private BufferedImage cSprite;
    private boolean moving = false;


    public PurpleKnight(int x, int y, ID id, SpriteSheet ss, Game game, Handler handler, Sound sound) {
        super(x, y, id, ss);
        this.game = game;
        this.handler = handler;
        this.sound = sound;
    }

    private void MoveToMiddle(int ticks) {
        if (ticks < 90) {
            velY = 0;
            velX = -3;
        } else {
            velX = 0;
        }
    }

    private void firstPhase(int ticks) {
        int b2 = bounds / 2 - 10;

        if (ticks % 60 == 0) {
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 50 + b2, y + 50 + b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + -50 + b2, y + 50 + b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 50 + b2, y + -50 + b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + -50 + b2, y + -50 + b2, ss, game, sound, 10));
        } else if (ticks % 30 == 0) {
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 0 + b2, y + 100 + b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 100 + b2, y + 0 + b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + -100 + b2, y + 0 + b2, ss, game, sound, 10));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 0 + b2, y + -100 + b2, ss, game, sound, 10));
        }
    }

    private void speedshots(int ticks) {
        int b2 = bounds / 2 - 10;

        if (ticks % 20 == 0) {
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 50 + b2, y + 50 + b2, ss, game, sound, 5));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + -50 + b2, y + 50 + b2, ss, game, sound, 5));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 50 + b2, y + -50 + b2, ss, game, sound, 5));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + -50 + b2, y + -50 + b2, ss, game, sound, 5));
        } else if (ticks % 10 == 0) {
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 0 + b2, y + 100 + b2, ss, game, sound, 5));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 100 + b2, y + 0 + b2, ss, game, sound, 5));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + -100 + b2, y + 0 + b2, ss, game, sound, 5));
            handler.addObject(new MiniShots(x + b2, y + b2, ID.MINISHOT, handler, x + 0 + b2, y + -100 + b2, ss, game, sound, 5));
        }
    }
    private void aura(int ticks){
        int b2 = bounds/2-10;

        if(ticks % 20 == 0 ){
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+0+b2, y+100+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+100+b2, y+0+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+-100+b2, y+0+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+0+b2, y+-100+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+50+b2, y+50+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+-50+b2, y+50+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+50+b2, y+-50+b2, ss, game, 15, 0));
            handler.addObject(new AuraShot(x+b2, y+b2, ID.AURASHOT, handler, x+-50+b2, y+-50+b2, ss, game, 15, 0));
        }
    }

    private void move(int dir){

        moving = true;
            if (dir == 0) {
                dirx = 1250;
                diry = 1000;
            } else if (dir == 1) {
                dirx = 1000;
                diry = 1250;
            } else if (dir == 2) {
                dirx = 750;
                diry = 1000;
            } else if (dir == 3) {
                dirx = 1000;
                diry = 750;
            }

            velX = Math.signum(dirx - x) * 5;
            velY = Math.signum(diry - y) * 5;
            if(Math.abs(velX) < 3) velX = 0;
            if(Math.abs(velY) < 3) velY = 0;
        int deltax = Math.abs(dirx-x);
        int deltay = Math.abs(diry-y);
          if( deltax < 10 && deltax > 0  && deltay < 10&& deltay > 0) {
              moving = false;
            velX = 0;
            velY = 0;
        } else if (deltax < 10 && deltax > 0){
              velX = 0;
          } else if (deltay < 10 && deltay > 0){
              velY = 0;
          }

    }

    private void moveToQuads(int ticks){

        if (ticks % 180 == 0) {
        side = (int) (Math.random()*4);
        }
      move(side);

    }

    private void regenHP(){
        if(regen){
            game.setPurpHP(game.getPurpHP()+25);
        }
        if(game.getPurpHP() >= 1000){
            regen = false;
        }
    }



    public void tick() {
        ticks++;
        if(game.isRedAlive()) {
            MoveToMiddle(ticks);
            if (game.getPurpHP() >= 500) {
                firstPhase(ticks);
            } else if (game.getPurpHP() <= 500) {
                speedshots(ticks);
            }
        } else {
            if(bounds < 112){
                bounds++;
            }
            regenHP();
            moveToQuads(ticks);
            if(!moving) {
                speedshots(ticks);
            } else {
                aura(ticks);
            }
        }

        x+= velX;
        y+= velY;

        if(game.getPurpHP() <= 0){
            sound.playSound("Rocks_death.wav");
            game.setPurpleAlive(false);
            handler.removeObject(this);
        }
        if(Math.abs(velX) > 0 || Math.abs(velY) > 0){
            if(ticks % 15 == 0) {
                sprnum++;
            }
            if(sprnum > 2){
                sprnum = 0;
            }
            cSprite = game.PurpleM[sprnum];
        } else {
            if(ticks % 5 == 0) {
                sprnum++;
            }
            if(sprnum > 3){
                sprnum = 0;
            }
            cSprite = game.PurpleSpin[sprnum];
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
        g.setColor(Color.red);
        g.fillRect(x , y+bounds, 100, 5);
        g.setColor(Color.green);
        g.fillRect(x , y+bounds, game.getPurpHP()/10, 5);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, bounds, bounds);
    }
}

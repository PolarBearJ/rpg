import java.awt.*;
import java.awt.image.BufferedImage;

public class Warrior extends GameObject {
    // FIXME make all the other attributes of GameObject subclasses private, and as many as possible final
    private final Handler handler;
    private final Game game;
    private final Sound sound;
    private int Speed = 5;
    private final int HitBox = 48;
    public double warAng;
    private BufferedImage CurrentSprite = null;
    private int counter = 0;
    private int LastPressed = 1; //1 = right 2 = up 3 = left 4 = down
    private Gun gun;
    private int timeC = 0;
    private int HPT = 0;
    private float diagspeed = 4;
    public Warrior(int x, int y, ID id, Handler handler, Game game, Sound sound, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        this.sound = sound;
        this.gun = new StarterGun(this, handler, game, ss, sound);
        handler.addObject(this.gun);
    }
    public void movement() {
        if(game.isSPEEDY()){
            Speed = 7;
            diagspeed = 5;
        }
        if (!handler.isRight() && !handler.isLeft() && !handler.isDown() && !handler.isUp()) {
            velX = 0;
            velY = 0;
        }
        if (handler.isUp() && handler.isRight()) {
            velX =  diagspeed;
            velY = -diagspeed;
            if (counter > 20) counter = 0;
            CurrentSprite = game.warrCharRight[(int) counter / 10];
            counter++;
            LastPressed = 1;
            if (counter == 20) {
                counter = 0;
            }
        } else if (handler.isUp() && handler.isLeft()) {
            velX = -diagspeed;
            velY =  -diagspeed;
            CurrentSprite = game.warrCharUp[(int) counter / 10 + 1];
            counter++;
            LastPressed = 2;
            if (counter == 20) {
                counter = 0;
            }
        } else if (handler.isUp()) {
            velX = 0;
            velY = -Speed;
            CurrentSprite = game.warrCharUp[(int) counter / 10 + 1];
            counter++;
            LastPressed = 2;
            if (counter == 20) {
                counter = 0;
            }
        }
        if (handler.isDown() && handler.isRight()) {
            velX =  diagspeed;
            velY =  diagspeed;
            CurrentSprite = game.warrCharDown[(int) counter / 10 + 1];
            counter++;
            LastPressed = 4;
            if (counter == 20) {
                counter = 0;
            }

        } else if (handler.isDown() && handler.isLeft()) {
            velX =  -diagspeed;
            velY =  diagspeed;
            if (counter > 20) counter = 0;
            CurrentSprite = game.warrCharLeft[(int) counter / 10];
            counter++;
            LastPressed = 3;
            if (counter == 20) {
                counter = 0;
            }
        } else if (handler.isDown()) {
            velX = 0;
            velY = Speed;
            CurrentSprite = game.warrCharDown[(int) counter / 10 + 1];
            counter++;
            LastPressed = 4;
            if (counter == 20) {
                counter = 0;
            }
        }

        if (handler.isRight() && !handler.isUp() && !handler.isDown()) {
            velX = Speed;
            velY = 0;
            if (counter > 20) counter = 0;
            CurrentSprite = game.warrCharRight[(int) counter / 10];
            counter++;
            LastPressed = 1;
            if (counter == 20) {
                counter = 0;
            }
        }

        if (handler.isLeft() && !handler.isUp() && !handler.isDown()) {
            velX = -Speed;
            velY = 0;
            LastPressed = 3;
            if (counter > 20) counter = 0;
            CurrentSprite = game.warrCharLeft[(int) counter / 10];
            counter++;
            LastPressed = 3;
            if (counter == 20) {
                counter = 0;
            }
        }

        if (!handler.isDown() && !handler.isUp() && !handler.isLeft() && !handler.isRight()) {
            if (LastPressed == 1) {
                CurrentSprite = game.warrCharRight[0];
            }
            if (LastPressed == 2) {
                CurrentSprite = game.warrCharUp[0];
            }
            if (LastPressed == 3) {
                CurrentSprite = game.warrCharLeft[0];
            }
            if (LastPressed == 4) {
                CurrentSprite = game.warrCharDown[0];
            }
        }

    }
    private void alive(){
        if(game.getPhealth()<= 0){
            sound.playSound("Death-screen.wav");
            game.setCharDead(true);
            game.getMusic().stopSong();
            game.setLosescreen(true);
            handler.removeObject(this);
        }
    }

private void regenHPMP(int HPT){
        if(game.getPMana() < 65){
            game.setEnoughMana(false);
        } else {
            game.setEnoughMana(true);
        }
        if(HPT%120 == 0 && game.getPhealth() < 100){
            game.setPhealth(game.getPhealth()+5);
        }
        if(HPT%120 == 0 && game.getPMana() < 100){
        game.setPMana(game.getPMana()+10);
    }
        if(game.getPhealth() > 100){
            game.setPhealth(100);
        }
    if(game.getPMana() > 100){
        game.setPMana(100);
    }
}

    public void tick() {

        HPT++;
        regenHPMP(HPT);
        if(game.isSPEEDY()) {
            timeC++;
        }
        if(timeC % 300 == 0){
            game.setSPEEDY(false);
            game.setBEZERK(false);
            game.setOnCooldown(false);
            Speed = 5;
            diagspeed = 4;
        }
        x += velX;
        y += velY;
        game.setPx(x);
        game.setPy(y);
        game.setPvx((int) velX);
        game.setPvy((int) velY);
        collision(); // first check collisions
        movement(); //then do movement
//        checkBounds(); //then cap off the speeds
        alive();//Check if alive if not then kill it
    }

    private void collision() {
        for (int i = 0; i < handler.gameObjects.size() - 1; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.BLOCK) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }

//            if (tempObject.getId() == ID.CRATE) {
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    game.setPhealth(game.getPhealth()+150);
//                    handler.removeObject(tempObject);
//                }
//            }

        }
    }

    public void render(Graphics g) { //draw images related to character
         warAng = -game.CamA;
        Graphics2D g2d = (Graphics2D) g;
        //Draw shadow
        float alpha = 0.3f;
        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        if(LastPressed == 4 || LastPressed == 1){
            g2d.drawImage(game.shadow, x-4, y+10, HitBox, HitBox, null);
        } else {
            g2d.drawImage(game.shadow, x + 4, y + 10, HitBox, HitBox, null);
        }

        if(game.isSpaceParticle()){
            g.drawImage(game.helm, x-75, y-100, 200, 200, null);
        }

        alpha = 1f;
         alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        if(game.isShooting()){
            if(gun.getSprDirShoot() == 1){
                LastPressed = 1;
            CurrentSprite = game.warrCharShootRight[gun.SprSw];
            } else if(gun.getSprDirShoot() == 2){
                LastPressed = 2;
                CurrentSprite = game.warrCharShootUp[gun.SprSw];
            }else if(gun.getSprDirShoot() == 3){
                LastPressed = 3;
                CurrentSprite = game.warrCharShootLeft[gun.SprSw];
            }else if(gun.getSprDirShoot() == 4){
                LastPressed = 4;
                CurrentSprite = game.warrCharShootDown[gun.SprSw];
            }
        }

        g.setColor(Color.green);
//        g.drawRect(x, y, HitBox, HitBox);
        g2d.rotate(warAng, x + HitBox / 2, y + HitBox / 2);
        if(CurrentSprite == game.warrCharShootRight[1] || CurrentSprite == game.warrCharShootLeft[1]){
            if(CurrentSprite == game.warrCharShootLeft[1]){
                g2d.drawImage(CurrentSprite, x-24, y, HitBox * 2, HitBox, null); //Draw player shoot left
            }else {
                g2d.drawImage(CurrentSprite, x, y, HitBox * 2, HitBox, null); //Draw player shoot right
            }
        } else {
            g2d.drawImage(CurrentSprite, x, y, HitBox, HitBox, null); // else draw player
        }
        if(game.isBEZERK()){
            g.drawImage(game.bezrk, x+15, y-20, 16, 16, null);
        }






//        g.drawLine((int) game.getCamera().getX(), (int) game.getCamera().getY()-20,(int) (game.getCamera().getX()+820), (int) game.getCamera().getY()+600) ;
//        g.drawLine((int)game.getCamera().getX()+820, (int)game.getCamera().getY()-20, (int)game.getCamera().getX(), (int)game.getCamera().getY()+600);


    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, HitBox, HitBox);
    }
}

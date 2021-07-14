import java.awt.*;

public class TrapDoor extends GameObject {
    private final Game game;
    private final Handler handler;
    private final Sound sound;
    public TrapDoor(int x, int y, ID id, SpriteSheet ss, Game game, Handler handler, Sound sound) {
        super(x, y, id, ss);
        this.game = game;
        this.handler = handler;
        this.sound = sound;
    }

    private void removeALL(){
        for(int i = 0; i<handler.gameObjects.size()-1; i++){
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId() != ID.GUN && tempObject.getId() != ID.PLAYER){
                handler.removeObject(tempObject);
            }
        }
    }


    private void Collide(){
        for (int i = 0; i < handler.gameObjects.size() - 1; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.PLAYER) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    sound.playSound("Enter_realm.wav");
                    removeALL();
                   newLevel();
                    game.getMusic().changeSong();
                }
            }
        }
    }
    private void newLevel(){


        if(game.getLevelNumber()== 1){
            game.Level1();
            game.setLevelNumber(2);
            return;
        } else if (game.getLevelNumber() == 2){
            game.setLvl3(true);
            game.Level2();
        }
    }


    public void tick() {
        Collide();


    }

    public void render(Graphics g) {
       g.drawImage(game.trapDoor, x, y, 64, 64, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}

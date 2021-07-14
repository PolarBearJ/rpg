public class StarterGun extends Gun {


    public StarterGun(Warrior warrior, Handler handler, Game game, SpriteSheet ss, Sound sound) {
        super(warrior, handler, game, ss, sound);

    }


    int getShotsPerSecond() {
        return 3;
    }
}

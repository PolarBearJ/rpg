public class Music implements  Runnable{



    private int songNumber = 0;
    private Game game;
    private Sound sound;

    public Music(Game game) {
        this.game = game;
    }

    public int getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }
    public void stopSong(){
        sound.stop();
    }

    public void changeSong() {
        songNumber++;
        loopSong();
    }

    @Override
    public void run() {
        sound = new Sound(game);
        loopSong();
    }

    private void loopSong() {
        sound.stop();
        if(songNumber == 0){
            sound.loopSound("tutorialSong.wav");
        } else if (songNumber == 1){
            sound.loopSound("Rhinoceros.wav");
        } else if (songNumber >= 2){
            sound.loopSound("finalBoss.wav");
        }
    }

}

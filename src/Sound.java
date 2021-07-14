import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;

public class Sound {
    private final Game game;
    private Clip clip;

    public Sound(Game game) {
        this.game = game;
    }

    public void playSound(String filepath) {
        try {
            InputStream music;
            // create AudioInputStream object
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

            // create clip reference
            Clip clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN) ;
            gainControl.setValue(-20);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loopSound(String filepath) {
        try {

            InputStream music;
            // create AudioInputStream object
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN) ;
            gainControl.setValue(-20);
            clip.start();
            //loop song
            clip.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}



import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlaySound{
    String path = "src/sounds/intro.wav";
    String path2 = "src/sounds/eat.wav";
    public void playSound(String soundFileName){
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if (soundFileName.equals(path) || soundFileName.equals(path2)){
                clip.loop(0);
            }else {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
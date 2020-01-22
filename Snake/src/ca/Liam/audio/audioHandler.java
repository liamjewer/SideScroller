package ca.Liam.audio;

import javax.sound.sampled.*;
import java.io.File;


public class audioHandler {
    public static void playSound(String filepath){
        File file = new File(filepath);
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loopSound(String filepath){
        File file = new File(filepath);
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

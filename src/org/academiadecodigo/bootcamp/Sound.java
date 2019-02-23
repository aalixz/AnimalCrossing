package org.academiadecodigo.bootcamp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public  class Sound extends JFrame {


    public void generalSound()throws IOException {

        try {
            URL soundURL = this.getClass().getClassLoader().getResource("yoga.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void rabbitSound() {

        try {
            URL soundURL = this.getClass().getClassLoader().getResource("yoga.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}








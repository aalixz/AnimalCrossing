package org.academiadecodigo.bootcamp;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Sound sound = new Sound();
        Game slot1 = new Game();

        try {
            sound.generalSound();
        } catch (IOException e) {
            e.printStackTrace();
        }


        slot1.start();




    }
}

package org.academiadecodigo.bootcamp;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Sound sound;
        Game slot1 = new Game();
        try {
            sound = new Sound();

        } catch (IOException e) {
            e.printStackTrace();
        }


        slot1.start();

    }
}

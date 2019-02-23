package org.academiadecodigo.bootcamp;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Sound sound = new Sound();
        Game slot1 = new Game();

        sound.generalSound();



        slot1.start();

    }
}

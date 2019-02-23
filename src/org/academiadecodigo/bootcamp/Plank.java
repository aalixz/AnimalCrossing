package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Plank extends GameObject implements Rideable {

    public Plank(int col, int row, GameObject.Direction dir) {
        this.setSpeed(1);
        this.setDir(dir);

            // TODO: array de sprites
            this.setSprite(new Picture(col,row,"GameObjects/PlankLeft.png"));
            this.getSprite().draw();

    }

}

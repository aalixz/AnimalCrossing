package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Plank extends GameObject implements Rideable {

    public Plank(int col, int row, GameObject.Direction dir) {
        this.setSpeed(1);
        this.setDir(dir);

        if (dir == GameObject.Direction.LEFT) {
            this.setSprite(new Picture(col,row,"Bunny/BunnyJumpLeft.png"));
            this.getSprite().draw();

        } else {
            this.setSprite(new Picture(col,row,"Bunny/BunnyJumpRight.png"));
            this.getSprite().draw();
        }
    }

}

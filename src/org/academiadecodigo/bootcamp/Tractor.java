package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Tractor extends GameObject implements Collidable {

    public Tractor(int col, int row, GameObject.Direction dir) {
        this.setSpeed(1);
        this.setDir(dir);

        if (dir == GameObject.Direction.LEFT) {
            this.setSprite(new Picture(col,row,"GameObjects/TractorLeft.png"));
            this.getSprite().draw();

        } else {
            this.setSprite(new Picture(col,row,"GameObjects/TractorRight.png"));
            this.getSprite().draw();
        }
    }
}

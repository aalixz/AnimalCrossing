package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameObject {

    // region Properties
    private Picture sprite;
    private int speed;
    // endregion

    enum Type {
        COLLIDABLE,
        RIDEABLE
    }

    enum Direction {
        LEFT,
        RIGHT
    }

    // region Getters
    public Picture getSprite() {
        return sprite;
    }

    public int getSpeed() {
        return speed;
    }
    // endregion

    // region Setters
    public void setSprite(Picture sprite) {
        this.sprite = sprite;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    // endregion

}

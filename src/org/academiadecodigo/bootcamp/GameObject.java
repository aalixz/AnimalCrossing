package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

abstract class GameObject {

    // region Properties
    private Picture sprite;
    private int speed;
    private GameObject.Direction dir;
    // endregion

    enum Type {
        COLLIDABLE,
        RIDEABLE
    }

    enum Direction {
        LEFT,
        RIGHT
    }

    public void move(GameObject.Direction dir, int cellSize) {
        if (dir == Direction.LEFT) {
            this.sprite.translate(-cellSize * speed, 0);
        } else {
            this.sprite.translate(cellSize * speed, 0);
        }
    }

    // region Getters
    public Picture getSprite() {
        return sprite;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDir() {
        return dir;
    }
    // endregion

    // region Setters
    public void setSprite(Picture sprite) {
        this.sprite = sprite;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    // endregion

}

package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private Picture playerSprite;

    public Player(Grid grid, int x, int y) {
        this.playerSprite = new Picture(x, y, "Bunny/BunnyUp.png");
    }

    public void show() {
        playerSprite.draw();
    }

    public void moveRight(int cellSize) {
        playerSprite.translate(cellSize, 0);
    }

    public void moveLeft(int cellSize) {
        playerSprite.translate(-cellSize, 0);
    }

    public void moveUp(int cellSize) {
        playerSprite.translate(0, -cellSize);
    }

    public void moveDown(int cellSize) {
        playerSprite.translate(0, cellSize);
    }

    public Picture getPlayerSprite() {
        return playerSprite;
    }
}
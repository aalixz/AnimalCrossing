package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private Picture playerSprite;
    private int x;
    private int y;

    public Player(Grid grid, int x, int y) {
        this.playerSprite = new Picture(x, y, "Bunny/BunnyUp.png");
        this.x = x;
        this.y = y;
    }

    public void show() {
        playerSprite.draw();
    }

    public void moveRight(int cellSize) {
            playerSprite.load("Bunny/BunnyRight.png");
            playerSprite.translate(cellSize, 0);
            //playerSprite.load("Bunny/BunnyJumpRight.png");
        
    }

    public void moveLeft(int cellSize) {
        playerSprite.load("Bunny/BunnyLeft.png");
        playerSprite.translate(-cellSize, 0);
    }

    public void moveUp(int cellSize) {
        playerSprite.load("Bunny/BunnyUp.png");
        playerSprite.translate(0, -cellSize);
    }

    public void moveDown(int cellSize) {
        playerSprite.load("Bunny/BunnyDown.png");
        playerSprite.translate(0, cellSize);
    }

    public Picture getPlayerSprite() {
        return playerSprite;
    }
}
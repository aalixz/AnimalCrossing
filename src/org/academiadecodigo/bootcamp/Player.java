package org.academiadecodigo.bootcamp;

import com.sun.corba.se.impl.interceptors.PICurrent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private Picture playerSprite;
    private int x;
    private int y;
    private Picture current;

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
        playerSprite.draw();
        playerSprite.translate(cellSize, 0);


    }

    public void moveLeft(int cellSize) {
        // playerSprite.load("Bunny/BunnyJumpRight.png");
        //playerSprite.draw();
        playerSprite.translate(-cellSize, 0);
    }

    public void moveUp(int cellSize) {
        playerSprite.load("Bunny/BunnyUp.png");
        playerSprite.draw();
        playerSprite.translate(0, -cellSize);
    }

    public void moveDown(int cellSize) {
        playerSprite.load("Bunny/BunnyDown.png");
        playerSprite.draw();
        playerSprite.translate(0, cellSize);
    }

    public Picture getPlayerSprite() {
        return playerSprite;
    }
}
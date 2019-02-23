package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;

public class Player {

    private Picture playerSprite;
    private int x;
    private int y;
    private Sound rabbit = new Sound();

    public Player(Grid grid, int x, int y) {
        this.playerSprite = new Picture(x, y, "Bunny/BunnyUp.png");
        this.x = x;
        this.y = y;
    }

    public void show() {
        playerSprite.draw();
    }

    public void moveRight(int cellSize){
            playerSprite.load("Bunny/BunnyRight.png");
            playerSprite.translate(cellSize, 0);
        try {
            rabbit.generalSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //playerSprite.load("Bunny/BunnyJumpRight.png");
        
    }

    public void moveLeft(int cellSize) {
        playerSprite.load("Bunny/BunnyLeft.png");
        playerSprite.translate(-cellSize, 0);
        try {
            rabbit.generalSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveUp(int cellSize) {
        playerSprite.load("Bunny/BunnyUp.png");
        playerSprite.translate(0, -cellSize);
        try {
            rabbit.generalSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveDown(int cellSize) {
        playerSprite.load("Bunny/BunnyDown.png");
        playerSprite.translate(0, cellSize);
        try {
            rabbit.generalSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Picture getPlayerSprite() {
        return playerSprite;
    }
}
package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;

public class Player extends GameObject {

    private Picture playerSprite;
    private Sound rabbit = new Sound();

    public Player(int x, int y) {
        this.playerSprite = new Picture(x, y, "Bunny/BunnyUp.png");
    }

    public void show() {
        playerSprite.draw();
    }

    public void hide() {
        playerSprite.delete();
    }

    public void moveRight(){
            playerSprite.load("Bunny/BunnyRight.png");
            playerSprite.translate(Grid.CELL_SIZE, 0);
        try {
            rabbit.rabbitSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //playerSprite.load("Bunny/BunnyJumpRight.png");
        
    }

    public void moveLeft() {
        playerSprite.load("Bunny/BunnyLeft.png");
        playerSprite.translate(-Grid.CELL_SIZE, 0);
        try {
            rabbit.rabbitSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveUp() {
        playerSprite.load("Bunny/BunnyUp.png");
        playerSprite.translate(0, -Grid.CELL_SIZE);
        try {
            rabbit.rabbitSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveDown() {
        playerSprite.load("Bunny/BunnyDown.png");
        playerSprite.translate(0, Grid.CELL_SIZE);
        try {
            rabbit.rabbitSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Picture getPlayerSprite() {
        return playerSprite;
    }

}
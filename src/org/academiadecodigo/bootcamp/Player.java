package org.academiadecodigo.bootcamp;


import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler {

    private Grid grid;
    private Keyboard kb;
    private Picture playerSprite;


    public Player(Grid grid, int x, int y) {
        this.grid = grid;
        this.kb = new Keyboard(this);
        this.playerSprite = new Picture(x,y, "Bunny/BunnyUp.png");
    }


    public void init() throws Exception{
        KeyboardEvent rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(rightPressed);

        KeyboardEvent leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(leftPressed);

        KeyboardEvent upPressed = new KeyboardEvent();
        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(upPressed);

        KeyboardEvent downPressed = new KeyboardEvent();
        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(downPressed);

        playerSprite.draw();
    }

    public void moveRight() {
        if (playerSprite.getMaxX() < grid.getWidth()) {
            playerSprite.translate(grid.CELL_SIZE, 0);
        }
    }

    public void moveLeft() {
        if (playerSprite.getX() > grid.PADDING) {
            playerSprite.translate(-grid.CELL_SIZE, 0);
        }
    }

    public void moveUp() {
        if (playerSprite.getY() > grid.PADDING) {
            playerSprite.translate(0, -grid.CELL_SIZE);
        }
    }

    public void moveDown() {
        if (playerSprite.getMaxY() < grid.getHeight()) {
            playerSprite.translate(0, grid.CELL_SIZE);
        }
    }


    @Override
    public void keyPressed(KeyboardEvent event) {
        switch (event.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                moveRight();
                break;
            case KeyboardEvent.KEY_LEFT:
                moveLeft();
                break;
            case KeyboardEvent.KEY_UP:
                moveUp();
                break;
            case KeyboardEvent.KEY_DOWN:
                moveDown();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public Picture getPlayerSprite() {
        return playerSprite;
    }
}
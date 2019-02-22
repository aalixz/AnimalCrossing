package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements KeyboardHandler {

    private Grid grid;
    private Lane[] lanes;
    private Player bunny;
    private Keyboard kb;

    public Game() {
        this.grid = new Grid();
        this.bunny = new Player(grid, grid.columnToX(grid.getCols() / 2), grid.rowToY(grid.getRows() - 1));
        this.kb = new Keyboard(this);
    }

    public void start() {

        // region Key Events
        addKeyEvent(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED);
        addKeyEvent(KeyboardEvent.KEY_Q, KeyboardEventType.KEY_PRESSED);
        // endregion

        grid.draw();

        lanes = new Lane[grid.getRows()];

        lanes[0] = new Lane(0);
        lanes[0].generateSafeLane(grid);

        lanes[5] = new Lane(5);
        lanes[5].generateSafeLane(grid);

        lanes[9] = new Lane(9);
        lanes[9].generateCollidableLane(grid, GameObject.Direction.LEFT,2,2);

        lanes[10] = new Lane(10);
        lanes[10].generateSafeLane(grid);

        bunny.show();

        Picture moveText = new Picture(grid.PADDING, grid.PADDING, "Move.png");
        Picture quitText = new Picture(grid.getWidth() - 70, grid.PADDING, "Quit.png");

        moveText.draw();
        quitText.draw();

    }

    private void addKeyEvent(int key, KeyboardEventType type) {
        KeyboardEvent keyEvent = new KeyboardEvent();
        keyEvent.setKey(key);
        keyEvent.setKeyboardEventType(type);
        kb.addEventListener(keyEvent);
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        switch (event.getKey()) {

            case KeyboardEvent.KEY_UP:
                if (bunny.getPlayerSprite().getY() > grid.PADDING) {
                    bunny.moveUp(grid.CELL_SIZE);
                }
                break;

            case KeyboardEvent.KEY_DOWN:
                if (bunny.getPlayerSprite().getMaxY() < grid.getHeight()) {
                    bunny.moveDown(grid.CELL_SIZE);
                }
                break;

            case KeyboardEvent.KEY_LEFT:
                if (bunny.getPlayerSprite().getX() > grid.PADDING) {
                    bunny.moveLeft(grid.CELL_SIZE);
                }
                break;

            case KeyboardEvent.KEY_RIGHT:
                if (bunny.getPlayerSprite().getMaxX() < grid.getWidth()) {
                    bunny.moveRight(grid.CELL_SIZE);
                }
                break;

            case KeyboardEvent.KEY_Q:
                System.exit(0);
                break;

            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}

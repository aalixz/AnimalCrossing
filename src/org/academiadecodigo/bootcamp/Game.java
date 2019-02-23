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
        this.bunny = new Player(grid.columnToX(grid.getCols() / 2), grid.rowToY(grid.getRows() - 1));
        this.kb = new Keyboard(this);
    }

    public void start() throws Exception {


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

        // region Lane Building
        lanes[0] = new Lane(0);
        lanes[0].generateSafeLane(grid);

        lanes[1] = new Lane(1);
        lanes[1].generateRideableLane(grid, GameObject.Direction.LEFT, 3, 2);

        lanes[2] = new Lane(2);
        lanes[2].generateRideableLane(grid, GameObject.Direction.RIGHT, 2, 2);

        lanes[3] = new Lane(3);
        lanes[3].generateRideableLane(grid, GameObject.Direction.RIGHT, 3, 2);

        lanes[4] = new Lane(4);
        lanes[4].generateRideableLane(grid, GameObject.Direction.RIGHT, 4, 2);

        lanes[5] = new Lane(5);
        lanes[5].generateSafeLane(grid);

        lanes[6] = new Lane(6);
        lanes[6].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

        lanes[7] = new Lane(7);
        lanes[7].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

        lanes[8] = new Lane(8);
        lanes[8].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

        lanes[9] = new Lane(9);
        lanes[9].generateCollidableLane(grid, GameObject.Direction.RIGHT, 2, 2);

        lanes[10] = new Lane(10);
        lanes[10].generateSafeLane(grid);
        // endregion

        bunny.show();

        Picture moveText = new Picture(grid.PADDING, grid.PADDING, "Move.png");
        Picture quitText = new Picture(grid.getWidth() - 70, grid.PADDING, "Quit.png");

        moveText.draw();
        quitText.draw();

        moveAllLanes();

    }


    public void moveAllLanes() throws InterruptedException {
        int counter = 0;
        for (int i = 0; i < this.lanes.length; i++) {
            if (lanes[i].getObjects() != null) {
                System.out.println("Estou no lane " + i);
                for (int j = 0; j < lanes[i].getObjects().length; j++) {
                    if (lanes[i].getObjects()[j] != null) {
                        System.out.println("Estou na coluna " + j);
                        while (lanes[i].getObjects()[j].getSprite().getMaxX()  < grid.getWidth()||
                                lanes[i].getObjects()[j].getSprite().getX() > grid.PADDING) {
                            Thread.sleep(100);
                            if (lanes[i].getObjects()[j].getSprite().getMaxX() > grid.getWidth()||
                                    lanes[i].getObjects()[j].getSprite().getX() < grid.PADDING ) {
                                System.out.println("moved");
                                break;
                            }
                            lanes[i].getObjects()[j].move(lanes[i].getObjects()[j].getDir(), Grid.CELL_SIZE);
                            System.out.println("AQUI " + j);
                        }
                        counter++;
                        System.out.println("Deu a volta " + counter);
                    }
                }

            }
        }
    }


    // ((obstacle.getDir() == GameObject.Direction.LEFT && obstacle.getSprite().getX() == PADDING) ||
    //              (obstacle.getDir() == GameObject.Direction.RIGHT && obstacle.getSprite().getMaxX() == getWidth()));

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
                    bunny.moveUp();
                }
                break;

            case KeyboardEvent.KEY_DOWN:
                if (bunny.getPlayerSprite().getMaxY() < grid.getHeight()) {
                    bunny.moveDown();
                }
                break;

            case KeyboardEvent.KEY_LEFT:
                if (bunny.getPlayerSprite().getX() > grid.PADDING) {
                    bunny.moveLeft();
                }
                break;

            case KeyboardEvent.KEY_RIGHT:
                if (bunny.getPlayerSprite().getMaxX() < grid.getWidth()) {
                    bunny.moveRight();
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

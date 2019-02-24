package org.academiadecodigo.bootcamp;

public class Grid {

    // region Properties
    public static final int PADDING = 10;
    public static final int CELL_SIZE = 60;
    private final int COLS = 13;
    private final int ROWS = 11;
    // endregion

    public boolean isEdge(GameObject obstacle){
            return ((obstacle.getDir() == GameObject.Direction.LEFT && obstacle.getSprite().getX() == PADDING) ||
                    (obstacle.getDir() == GameObject.Direction.RIGHT && obstacle.getSprite().getMaxX() == getWidth()));
    }

    public int columnToX(int column) {
        return column * CELL_SIZE + PADDING;
    }

    public int rowToY(int row) {
        return row * CELL_SIZE + PADDING;
    }

    public int xToCol (int x) {
        return (x - PADDING)  / CELL_SIZE;
    }

    // region Getters

    public int getWidth() {
        return COLS * CELL_SIZE;
    }

    public int getHeight() {
        return ROWS * CELL_SIZE;
    }

    public int getCols() {
        return COLS;
    }

    public int getRows() {
        return ROWS;
    }
    // endregion


}
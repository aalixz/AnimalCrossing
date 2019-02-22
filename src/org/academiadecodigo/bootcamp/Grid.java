package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {

    // region Properties
    public final int PADDING = 10;
    public final int CELL_SIZE = 60;
    private final int COLS = 13;
    private final int ROWS = 11;
    // endregion

    public void draw() {
        Rectangle field = new Rectangle(PADDING, PADDING, getWidth(), getHeight());
        field.draw();
/*        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Picture tile = new Picture(columnToX(col), rowToY(row), "grassTile.png");
                tile.show();
            }
        }*/
    }

    public int columnToX(int column) {
        return column * CELL_SIZE + PADDING;
    }

    public int rowToY(int row) {
        return row * CELL_SIZE + PADDING;
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
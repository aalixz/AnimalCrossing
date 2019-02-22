package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lane {

    // region Properties
    private int rowIndex;
    private GameObject[] obstacles;
    // endregion

    public Lane(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void generateSafeLane(Grid grid) {
        this.obstacles = null;
        drawBackground(grid, "BackgroundTiles/GrassTile.png");
    }

    public void generateCollidableLane(Grid grid, Collidable.CollidableType type, int num, int spacing) {
        this.obstacles = new GameObject[num];

        // Creates a random difference between the starting positions of the obstacles
        int offset = (int)(Math.random() * 3);


        switch (type) {
            case FOX:
                drawBackground(grid, "BackgroundTiles/GrassTile.png");

            case TRACTOR:
                drawBackground(grid, "BackgroundTiles/CropTile.png");

            default:
                generateSafeLane(grid);
        }

    }

    private void drawBackground(Grid grid, String backgroundTile) {
        for (int col = 0; col < grid.getCols(); col++) {
            Picture tile = new Picture(grid.columnToX(col), grid.rowToY(rowIndex),
                    backgroundTile);
            tile.draw();
        }
    }

    // region Getters
    public int getRowIndex() {
        return rowIndex;
    }

    public GameObject[] getObstacles() {
        return obstacles;
    }
    // endregion

}

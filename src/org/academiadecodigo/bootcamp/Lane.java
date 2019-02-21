package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lane {

    // region Properties
    private Grid grid;
    private int rowIndex;
    private GameObject[] obstacles;
    // endregion

    public Lane(Grid grid, int rowIndex) {
        this.grid = grid;
        this.rowIndex = rowIndex;
    }

    public void generateSafeLane() {
        this.obstacles = null;
        drawBackground("GrassTile.png");
    }

    public void generateCollidableLane(Collidable.CollidableType type, int num, int spacing) {
        this.obstacles = new GameObject[num];

        // Creates a random difference between the starting positions of the obstacles
        int offset = (int)(Math.random() * 3);

        switch (type) {
            case FOX:
                drawBackground("GrassTile.png");
                //pedir fox ao factory;

            case TRACTOR:
                drawBackground("CropTile.png");
                //pedir tractor ao factory;

            default:
                generateSafeLane();
        }

    }

    private void drawBackground(String backgroundTile) {
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

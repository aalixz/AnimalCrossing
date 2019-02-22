package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.bootcamp.Collidable.CollidableType.*;

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

    public void generateCollidableLane(Grid grid, GameObject.Direction dir, int num, int spacing) {
        this.obstacles = new GameObject[num];

        // Creates a random difference between the starting positions of the obstacles
        int offset = (int) (Math.random() * 3 * grid.CELL_SIZE);
        spacing = spacing * grid.CELL_SIZE;
        int random = (int) (Math.random() * Collidable.CollidableType.values().length);
        Collidable.CollidableType type = Collidable.CollidableType.values()[random];


        switch (type) {
            case FOX:
                drawBackground(grid, "BackgroundTiles/GrassTile.png");
                if (dir == GameObject.Direction.LEFT) {
                    int posX = offset;
                    for (int i = 0; i < num; i++) {
                        GameObjectFactory.getNewCollidable(posX, grid.rowToY(rowIndex), FOX, dir);
                        posX -= spacing;
                    }
                } else {
                    int posX = offset;
                    for (int i = 0; i < num; i++) {
                        GameObjectFactory.getNewCollidable(posX, grid.rowToY(rowIndex), FOX, dir);
                        posX += spacing;
                    }
                }
                break;

            case TRACTOR:
                drawBackground(grid, "BackgroundTiles/CropTile.png");
                if (dir == GameObject.Direction.LEFT) {
                    int posX = offset;
                    for (int i = 0; i < num; i++) {
                        GameObjectFactory.getNewCollidable(posX, grid.rowToY(rowIndex), TRACTOR, dir);
                        posX -= spacing;
                    }
                } else {
                    int posX = offset;
                    for (int i = 0; i < num; i++) {
                        GameObjectFactory.getNewCollidable(posX, grid.rowToY(rowIndex), TRACTOR, dir);
                        posX += spacing;
                    }
                }
                break;

            default:
                generateSafeLane(grid);
                break;
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

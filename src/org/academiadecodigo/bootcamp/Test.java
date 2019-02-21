package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Test {
    public static void main(String[] args) throws Exception {

        Grid grid = new Grid();
        grid.draw();

        Player bunny = new Player(grid, grid.columnToX(grid.getCols() / 2), grid.rowToY(grid.getRows() - 1));
        bunny.init();
       GameObjectFactory.getNewCollidable(grid.getCols(),grid.getRows());



    }
}

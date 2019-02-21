package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Test {
    public static void main(String[] args) throws Exception {

        Grid grid = new Grid();
        grid.draw();

        Player bunny = new Player(grid, grid.columnToX(grid.getCols() / 2), grid.rowToY(grid.getRows() - 1));
        bunny.init();
        GameObjectFactory.getNewCollidable(grid.getCols(),grid.getRows());
        GameObjectFactory.getNewCollidable(grid.getCols(),grid.getRows());
        GameObjectFactory.getNewCollidable(grid.getCols()+30,grid.getRows());
        GameObjectFactory.getNewCollidable(grid.getCols(),grid.getRows()+70);
        GameObjectFactory.getNewCollidable(grid.getCols()+100,grid.getRows()+100);


    }
}

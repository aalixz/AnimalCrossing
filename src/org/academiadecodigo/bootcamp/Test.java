package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Test {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        Grid grid = new Grid();
        grid.draw();
        game.start();


//        Player bunny = new Player(grid, grid.columnToX(grid.getCols() / 2), grid.rowToY(grid.getRows() - 1));
//        GameObjectFactory.getNewCollidable(grid.getCols(), grid.getRows());


    }
}

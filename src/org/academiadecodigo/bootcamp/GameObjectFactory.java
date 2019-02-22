package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.bootcamp.Collidable.CollidableType.*;

public class GameObjectFactory implements Collidable {

    public static Collidable getNewCollidable(int col, int row, CollidableType type, GameObject.Direction dir) {

        Collidable obstacle;

        switch (type) {
            case FOX:
                obstacle = new Fox(col, row,dir);
                break;
            case TRACTOR:
                obstacle = new Tractor(col, row, dir);
                break;
            default:
                System.out.println("something really really weird happened");
                obstacle = new Fox(col, row, dir);
                break;
        }

        return obstacle;

    }

   /* private GameObject.Direction getRandomDirection(){
        int random = (int)(Math.random() * GameObject.Direction.values().length);
        GameObject.Direction dir = GameObject.Direction.values()[random];
        return dir;
    }*/

}

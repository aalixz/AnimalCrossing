package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.bootcamp.Collidable.CollidableType.*;

public class GameObjectFactory implements Collidable {

    public static Collidable getNewCollidable(int col, int row) {

        int random = (int)(Math.random() * values().length);
        CollidableType obstacleType = CollidableType.values()[random];

        Collidable obstacle;

        switch (obstacleType) {
            case FOX:
                obstacle = new Fox(col, row);
                break;
            case TRACTOR:
                obstacle = new Fox(col, row); //tractor
                break;
            default:
                System.out.println("something really really weird happened");
                obstacle = new Fox(col, row);
        }

        return obstacle;

    }

}

package org.academiadecodigo.bootcamp;


public class GameObjectFactory implements Collidable, Rideable {

    public static Collidable getNewCollidable(int col, int row, CollidableType type, GameObject.Direction dir) {

        Collidable obstacle;

        switch (type) {
            case FOX:
                obstacle = new Fox(col, row, dir);
                break;
            case TRACTOR:
                obstacle = new Tractor(col, row, dir);
                break;
            default:
                System.out.println("Something really weird happened.");
                obstacle = new Fox(col, row, dir);
                break;
        }

        return obstacle;

    }

    public static Rideable getNewRideable(int col, int row, RideableType type, GameObject.Direction dir) {

        Rideable obstacle;

        switch (type) {
            case PIG:
                obstacle = new Pig(col, row, dir);
                break;
            case PLANK:
                obstacle = new Plank(col, row, dir);
                break;
            default:
                System.out.println("Something really weird happened.");
                obstacle = new Pig(col, row, dir);
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

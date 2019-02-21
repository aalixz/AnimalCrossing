package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Tractor  implements  Collidable{

    public Tractor(int col, int row){

        Picture tractor = new Picture(col+100,row+100,"BunnyUp.png");
        tractor.draw();
    }



}

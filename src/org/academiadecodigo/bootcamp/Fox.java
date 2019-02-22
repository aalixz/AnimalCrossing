package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Fox implements Collidable{


    public Fox(int col,int row, GameObject.Direction dir) {
       Picture fox = new Picture(col,row,"BunnyUp.jpg");
       fox.draw();

    }
}

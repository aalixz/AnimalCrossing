package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Fox implements Collidable{


    public Fox(int col,int row) {
       Picture fox = new Picture(col,row,"fox.png");
       fox.draw();

    }
}

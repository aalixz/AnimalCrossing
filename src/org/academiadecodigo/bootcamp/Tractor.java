package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Tractor implements Collidable {

    public Tractor(int col, int row, GameObject.Direction dir) {
        if(dir == GameObject.Direction.LEFT){
            Picture tractorLeft= new Picture(col,row,"GameObjects/TractorLeft.png");
            tractorLeft.draw();
        }else {
            Picture tractorRight= new Picture(col,row,"GameObjects/TractorRight.png");
            tractorRight.draw();
        }
    }
}

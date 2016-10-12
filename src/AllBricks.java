/*
 * Created by ivanovcinnikov on 12.10.16.
 */

import java.awt.*;
import java.util.ArrayList;

class AllBricks {
    FallingBricks fallingBricks;
    private ArrayList<Brick> bList;

    AllBricks() {
        bList = new ArrayList<>();
    }

    void addNew(){
        fallingBricks = new FallingBricks();
        bList.add(fallingBricks.getB1());
        bList.add(fallingBricks.getB2());
    }

    void fall(int win_height){
        for (Brick brick : bList) {
            if(!brick.isHitFloor(win_height)){
                brick.setY(brick.getY() + 1);
            }
        }
    }

    void drawSelf(Graphics g) {
        for (Brick brick : bList) brick.drawSelf(g);
    }

}

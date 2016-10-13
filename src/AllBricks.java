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

    void moveLeft(){
        if(fallingBricks.getLeftBorder() > 0)
            fallingBricks.setNewPosition(fallingBricks.getLeftBorder() - 20);
    }

    void moveRight(int FIELD_WIDTH) {
        if(fallingBricks.getRightBorder() < FIELD_WIDTH - 1)
            fallingBricks.setNewPosition(fallingBricks.getLeftBorder() + 20);
    }

    void swapFalling() {
        fallingBricks.swapBricks();
    }

    void fall(int win_height){
        for (Brick brick : bList) {
            if(brick.isHitFloor(win_height)) {
                addNew();
            } else {
                brick.setY(brick.getY() + 1);
            }
        }
    }

    void drawSelf(Graphics g) {
        for (Brick brick : bList) brick.drawSelf(g);
    }

}

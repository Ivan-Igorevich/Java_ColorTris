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

    boolean fall(int win_height) {
        int count = 0;
        for (Brick brick : bList) {
            if(isHitOther(win_height) || brick.isHitFloor(win_height)) {
                count++;
                brick.setMovable(false);
            } else {
                if(brick.getMovable())
                    brick.setY(brick.getY() + 1);
            }
        }
        return count > (bList.size() - 1);
    }

    boolean isHitOther(int win_height) {
        int count = 0;
        int onFloor = 1;
        for (Brick brick1 : bList) {
            for (Brick brick2 : bList) {
                if ((brick1.getY() + brick1.getBrickSize() == brick2.getY()) && (brick1.getX() == brick2.getX())) {
                    count++;
                    brick1.setMovable(false);
                }
            }
        }
        for (Brick brick : bList) {
            if (brick.isHitFloor(win_height)) onFloor++;
        }
        return count > (bList.size() - onFloor);

    }

    void drawSelf(Graphics g) {
        for (Brick brick : bList) brick.drawSelf(g);
    }

}

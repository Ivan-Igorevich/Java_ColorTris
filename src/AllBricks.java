/*
 * Created by ivanovcinnikov on 12.10.16.
 */

import java.awt.*;
import java.util.ArrayList;

class AllBricks {
    private FallingBricks fallingBricks;
    private ArrayList<Brick> bList;
    ArrayList<Brick> topList;

    AllBricks() {
        topList = new ArrayList<>();
        bList = new ArrayList<>();
    }

    int addNew(int lastID){
        fallingBricks = new FallingBricks(lastID);
        bList.add(fallingBricks.getB1());
        bList.add(fallingBricks.getB2());
        return lastID + 2;
    }

    void moveLeft(){
        boolean canMove = true;
        if(fallingBricks.getLeftBorder() > 0) {
            for (Brick brick : bList) {
                if ((!brick.getMovable()) &&
                        (brick.getX() + brick.getBrickSize() == fallingBricks.getLeftBorder()) &&
                        (brick.getY() <= fallingBricks.getB1().getY() + fallingBricks.getB1().getBrickSize() ||
                                brick.getY() <= fallingBricks.getB2().getY() + fallingBricks.getB2().getBrickSize())) {
                    canMove = false;
                    break;
                } else canMove = true;
            }
            if ((canMove) && (!fallingBricks.isOneFell())) fallingBricks.setNewPosition(fallingBricks.getLeftBorder() - 20);
        }
    }

    void moveRight(int FIELD_WIDTH) {
        boolean canMove = true;
        if(fallingBricks.getRightBorder() < FIELD_WIDTH - 1){
            for (Brick brick : bList) {
                if ((!brick.getMovable()) &&
                        (brick.getX() == fallingBricks.getRightBorder()) &&
                        (brick.getY() <= fallingBricks.getB1().getY() + fallingBricks.getB1().getBrickSize() ||
                                brick.getY() <= fallingBricks.getB2().getY() + fallingBricks.getB2().getBrickSize())) {
                    canMove = false;
                    break;
                } else canMove = true;
            }
            if ((canMove) && (!fallingBricks.isOneFell()))fallingBricks.setNewPosition(fallingBricks.getLeftBorder() + 20);
        }
    }

    void swapFalling() { if (!fallingBricks.isOneFell()) fallingBricks.swapBricks(); }

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

    private boolean isHitOther(int win_height) {
        int count = 0;
        int onFloor = 1;

        //TODO make an array of top bricks and compare only to them (iterate through only 10 bricks, not all array)

        for (Brick brick1 : bList) {
            for (Brick brick2 : bList) {
                if ((brick1.getY() + brick1.getBrickSize() == brick2.getY()) && (brick1.getX() == brick2.getX())) {
                    count++;
                    brick1.setMovable(false);
                }
            }
        }
        for (Brick brick : bList) { if (brick.isHitFloor(win_height)) onFloor++; }
        return count > (bList.size() - onFloor);
    }

    void drawSelf(Graphics g) { for (Brick brick : bList) brick.drawSelf(g); }

}

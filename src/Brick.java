/*
 * Created by ivanovcinnikov on 12.10.16.
 */

import java.awt.*;

class Brick {
    private final static int BRICK_SIZE = 20;
    private final static int BRICK_ARC_SIZE = 5;
    private boolean isMovable;
    private int x;
    private int y;
    private Color color;
    private BrickColors bColors = new BrickColors();

    void setMovable (boolean b) { isMovable = b; }
    boolean getMovable () { return isMovable; }
    void setX(int _x){ x = _x; }
    void setY(int _y){ y = _y; }
    void changeColor() { color = bColors.getRandom(); }
    Color getColor() { return color; }
    int getBrickSize() { return BRICK_SIZE; }
    int getX(){ return x; }
    int getY(){ return y; }
    boolean isHitFloor(int win_height) { return y + BRICK_SIZE == win_height; }

    Brick(){
        isMovable = true;
        x = 0;
        y = 0;
        color = bColors.getRandom();
    }

    void drawSelf(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRoundRect(x, y, BRICK_SIZE, BRICK_SIZE, BRICK_ARC_SIZE, BRICK_ARC_SIZE);
        g.setColor(color);
        g.fillRoundRect(x + 1, y + 1, BRICK_SIZE-2, BRICK_SIZE-2, BRICK_ARC_SIZE-1, BRICK_ARC_SIZE-1);

    }
}

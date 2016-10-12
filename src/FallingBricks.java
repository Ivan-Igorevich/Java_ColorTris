/*
 * Created by ivanovcinnikov on 12.10.16.
 */

class FallingBricks {

    private Brick b1 = new Brick();
    private Brick b2 = new Brick();

    FallingBricks(){
        b1.setX(80);
        b2.setX(100);
        if(b1.getColor() == b2.getColor()) b2.changeColor();
    }

    Brick getB1(){
        return b1;
    }
    Brick getB2(){
        return b2;
    }

    int getLeftBorder(){
        return b1.getX();
    }

    int getRightBorder(){
        return b2.getX() + b2.getBrickSize();
    }

    void setNewPosition(int _x){
        b1.setX(_x);
        b2.setX(_x + b2.getBrickSize());
    }

    void swapBricks(){
        int old1, old2;
        old1 = b1.getX();
        old2 = b2.getX();
        b1.setX(old2);
        b2.setX(old1);
    }

}

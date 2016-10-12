/*
 * Created by ivanovcinnikov on 12.10.16.
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class  BrickColors {
    private ArrayList<Color> COLORS;
    private static Random random = new Random();

    Color getRandom() { return COLORS.get(random.nextInt(COLORS.size() - 1)); }

    BrickColors(){
        COLORS = new ArrayList<>();
        COLORS.add(Color.BLUE);
        COLORS.add(Color.ORANGE);
        COLORS.add(Color.GREEN);
        COLORS.add(Color.RED);
        COLORS.add(Color.MAGENTA);
        COLORS.add(Color.CYAN);
        COLORS.add(Color.YELLOW);
    }

}

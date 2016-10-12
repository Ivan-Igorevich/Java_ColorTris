/*
 * Created by ivanovcinnikov on 08.10.16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class ColorTris extends JFrame {

    private static ColorTris game;
    private static GameField mainField;
    private static AllBricks allBricks;
    private static Delay delay;

    private final static String TITLE = "ColorTris";
    private final static Color BG_COLOR = Color.DARK_GRAY;
    private final static int WIN_LOC = 100;
    private final static int WIN_WIDTH = 201;
    private final static int WIN_HEIGHT = 450;
    private final static int FIELD_WIDTH = 200;
    private final static int FIELD_HEIGHT = 425;
    private final static int FALL_SPEED = 30;
    private final static int KEY_LEFT = 37;
    private final static int KEY_UP = 38;
    private final static int KEY_RIGHT = 39;
    private final static int KEY_DOWN = 40;

    private void initialize() {
        mainField.setBackground(BG_COLOR);
        mainField.setSize(FIELD_WIDTH,FIELD_HEIGHT);
        game.add(mainField);

        game.setTitle(TITLE);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setLocation(WIN_LOC, WIN_LOC);
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setResizable(false);
        game.setVisible(true);
        game.setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KEY_LEFT:
                        if(allBricks.fallingBricks.getLeftBorder() > 0)
                            allBricks.fallingBricks.setNewPosition(allBricks.fallingBricks.getLeftBorder() - 20);
                        break;
                    case KEY_RIGHT:
                        if(allBricks.fallingBricks.getRightBorder() < WIN_WIDTH - 1)
                            allBricks.fallingBricks.setNewPosition(allBricks.fallingBricks.getLeftBorder() + 20);
                        break;
                    case KEY_UP:
                        allBricks.fallingBricks.swapBricks();
                        break;
                    default: break;

                }

            }
        });

    }

    public static void main(String[] args) {
        game = new ColorTris();
        mainField = new GameField();
        delay = new Delay();
        allBricks = new AllBricks();

        game.initialize();
        game.loop();

    }

    private void loop()
    {
        allBricks.addNew();

        while (true) {
            delay.wait(FALL_SPEED);

            allBricks.fall(FIELD_HEIGHT);

            mainField.repaint();
        }
    }

    private static class GameField extends JPanel {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            try {
                allBricks.drawSelf(g);
            } catch (NullPointerException npe) {}
        }
    }
}

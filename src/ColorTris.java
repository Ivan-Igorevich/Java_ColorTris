/*
 * Created by ivanovcinnikov on 08.10.16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ColorTris extends JFrame {

    private static ColorTris game;
    private static GameField mainField;
    private static AllBricks allBricks;
    private static Delay delay;
    private static ScoreField stats;

    private final static String TITLE = "ColorTris";
    private final static Color BG_COLOR = Color.DARK_GRAY;
    private final static int WIN_LOC = 100;
    private final static int WIN_WIDTH = 210;
    private final static int WIN_HEIGHT = 460;
    private final static int STAT_HEIGHT = 150;
    private final static int FIELD_WIDTH = 200;
    private final static int FIELD_HEIGHT = 420;

    private final static int KEY_ESCAPE = 27;
    private final static int KEY_LEFT = 37;
    private final static int KEY_UP = 38;
    private final static int KEY_RIGHT = 39;
    private final static int KEY_DOWN = 40;


    private static int FALL_SPEED = 30;
    private static int brickID;

    private void initialize() {
        game.setTitle(TITLE);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setLocation(WIN_LOC, WIN_LOC);
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setResizable(false);
        game.setBackground(Color.WHITE);

        stats.setSize(FIELD_WIDTH, STAT_HEIGHT);

        mainField.setBackground(BG_COLOR);
        mainField.setSize(FIELD_WIDTH,FIELD_HEIGHT);

        game.getContentPane().add(BorderLayout.CENTER, mainField);
        game.getContentPane().add(BorderLayout.SOUTH, stats);
        game.setVisible(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KEY_LEFT:
                        allBricks.moveLeft();
                        break;
                    case KEY_RIGHT:
                        allBricks.moveRight(FIELD_WIDTH);
                        break;
                    case KEY_UP:
                        allBricks.swapFalling();
                        break;
                    case KEY_DOWN:
                        FALL_SPEED = 0;
                        break;
                    case KEY_ESCAPE:
                        //TODO close window, terminate application
                        break;
                    default: break;
                }
            }
        });
        brickID = allBricks.addNew(brickID);     // initial new falling blocks
    }

    public static void main(String[] args) {
        game = new ColorTris();
        mainField = new GameField();
        delay = new Delay();
        allBricks = new AllBricks();
        stats = new ScoreField();

        game.initialize();
        game.loop();
    }

    private void loop()
    {
        while (true) {
            if(!allBricks.fall(FIELD_HEIGHT)) {
                delay.wait(FALL_SPEED);
            } else {
                brickID = allBricks.addNew(brickID);     // if blocks fell - create new
                FALL_SPEED = 30;
            }

            mainField.repaint();
            stats.repaint();
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

    private static class ScoreField extends JPanel {
        @Override
        public void paintComponent(Graphics g)
        {
            String str = Integer.toString(brickID);
            super.paintComponent(g);
            g.setColor(Color.black);
            g.drawString(str, 10, 10);
        }
    }
}

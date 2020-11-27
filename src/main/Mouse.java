package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse {

    private static MouseListener mouseListener;
    private static MouseMotionListener mouseMotionListener;
    private static Coords coords;
    private static boolean M1;

    public static void init() {
        coords = new Coords(0, 0);
        M1 = false;

        mouseListener = new MouseListener() {
            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
                M1 = true;

            }

            public void mouseReleased(MouseEvent e) {
                M1 = false;

            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

        };

        mouseMotionListener = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {}

            public void mouseMoved(MouseEvent e) {
                coords.setXY(e.getX(), e.getY());

            }

        };

    }

    public static boolean getM1() {
        boolean retVal = M1;
        return retVal;

    }

    public static Coords getCoords() {
        Coords retVal = new Coords(coords.getX() - 8, coords.getY() - 31);
        return retVal;

    }

    public static MouseListener getMouseListener() {
        return mouseListener;

    }

    public static MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;

    }

    private Mouse() {}

}

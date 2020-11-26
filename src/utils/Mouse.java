package utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse {

    private boolean leftClicked, rightClicked;
    private double xLoc, yLoc;

    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;

    public Mouse() {
        leftClicked = false;
        rightClicked = false;
        xLoc = 0;
        yLoc = 0;

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int button = e.getButton();
                switch(button) {
                    case MouseEvent.BUTTON1: leftClicked = true;
                    case MouseEvent.BUTTON2: rightClicked = true;

                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int button = e.getButton();
                switch(button) {
                    case MouseEvent.BUTTON1: leftClicked = false;
                    case MouseEvent.BUTTON2: rightClicked = false;

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        };

        mouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xLoc = e.getX();
                yLoc = e.getY();

            }

        };

    }

    public MouseListener getMouseListener() {
        return mouseListener;

    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;

    }

    public double getX() {
        return xLoc;

    }

    public double getY() {
        return yLoc;

    }

    public boolean getM1() {
        return leftClicked;

    }

    public boolean getM2() {
        return rightClicked;

    }

}

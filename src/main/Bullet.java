package main;

import utils.Coordinate;
import utils.Math2;

import javax.swing.*;

public class Bullet extends JLabel {

    // Constants
    private static int sizeX = 50;
    private static int sizeY = 50;

    private double slope, yInt;
    private double speed;
    private boolean direction;
    // direction is true when going right
    private Coordinate coords;
    private double health;

    public Bullet(double slope, double speed, boolean direction, double startX, double startY, double health) {
        this.slope = slope;
        this.yInt = 0-(slope * startX) + startY;
        this.speed = speed;
        this.direction = direction;
        coords = new Coordinate(startX, startY);
        this.health = health;

    }

    public void update() {
        move();
        setBounds(coords.getIntX() - (sizeX / 2), coords.getIntY() - (sizeY / 2), sizeX, sizeY);

    }

    public void move() {
        double xDiff = speed / (Math.pow(slope, 2) + 1);
        if(direction) {
            coords.moveRight(xDiff);

        } else {
            coords.moveLeft(xDiff);

        }
        coords.setY(Math2.f(slope, yInt, coords.getX()));

    }

}

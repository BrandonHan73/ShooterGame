package main;

public class Coords {

    private double x, y;

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void moveX(double distance) {
        x += distance;

    }

    public void moveY(double distance) {
        y += distance;

    }

    public double getX() {
        double retVal = x;
        return retVal;

    }

    public double getY() {
        double retVal = y;
        return retVal;

    }

}

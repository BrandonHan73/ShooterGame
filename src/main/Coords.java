package main;

public class Coords {

    private double x, y;

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double distanceFrom(Coords coords2) {
        return Math.sqrt(Math.pow(getX() - coords2.getX(), 2) + Math.pow(getY() - coords2.getY(), 2));

    }

    public void setXY(double x, double y) {
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

    public String toString() {
        return "(" + x + ", " + y + ")";

    }

}

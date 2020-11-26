package utils;

public class Coordinate {

    private double x, y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public int getIntX() {
        return (int)x;

    }

    public int getIntY() {
        return (int)y;

    }

    public void move(double x, double y) {
        this.x += x;
        this.y += y;

    }

    public void moveUp(double y) {
        this.y -= y;

    }

    public void moveDown(double y) {
        this.y += y;

    }

    public void moveLeft(double x) {
        this.x -= x;

    }

    public void moveRight(double x) {
        this.x += x;

    }

    public double getX() {
        return x;

    }

    public double getY() {
        return y;

    }

}

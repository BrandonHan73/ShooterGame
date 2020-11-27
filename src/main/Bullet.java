package main;

import java.util.ArrayList;

public class Bullet {

    private int health;
    private Coords loc;
    private double slope;
    private boolean vertical;
    private boolean isGoingLeft;
    private int speed;

    private ArrayList<Enemy> updateList;

    public Bullet(int piercePower, Coords startLoc, int speed) {
        health = piercePower;
        loc = new Coords(startLoc.getX(), startLoc.getY());
        vertical = false;
        this.speed = speed;
        this.slope = getSlope();

    }

    public void update(EnemyList enemies) {
//        updateList = new ArrayList<Enemy>();
        for(int i = 0; i < enemies.getListSize(); i++) {
            if(enemies.getEnemy(i).contains(loc)) enemies.getEnemy(i).startDying();
            health--;

        }
        move();
        System.out.println(loc);

    }

    public boolean isDead() {
        if(health <= 0) return true;
        else return false;

    }

    private void move() {
        double xDiff = speed / Math.sqrt(Math.pow(slope, 2) + 1);
        double yDiff = slope * xDiff;
        if(vertical) {
            xDiff = 0;
            yDiff = speed;

        }
        loc.moveX(xDiff);
        loc.moveY(yDiff);

    }

    private double getSlope() {
        Coords c1 = new Coords(Main.windowWidth / 2, Main.windowHeight / 2);
        Coords c2 = new Coords(Mouse.getCoords().getX(), Mouse.getCoords().getY());
        double num = c1.getY() - c2.getY();
        double den = c1.getX() - c2.getX();
        if(Mouse.getCoords().getX() < Main.windowWidth / 2) speed = -Math.abs(speed);
        else speed = Math.abs(speed);
        if(den == 0) {
            vertical = true;
            if(Mouse.getCoords().getY() < Main.windowHeight / 2) speed = -Math.abs(speed);
            else speed = Math.abs(speed);
            return 0;

        } else return num / den;

    }

}

package main;

import java.util.ArrayList;

public class Bullet {

    private int health;
    private Coords loc;
    private double slope;
    private boolean isGoingLeft;
    private int speed;

    private ArrayList<Enemy> updateList;

    public Bullet(int piercePower, Coords startLoc, double slope, int speed) {
        health = piercePower;
        loc = new Coords(startLoc.getX(), startLoc.getY());
        this.slope = slope;
        this.speed = speed;

    }

    public void update(EnemyList enemies) {
//        updateList = new ArrayList<Enemy>();
        for(int i = 0; i < enemies.getListSize(); i++) {
            if(enemies.getEnemy(i).contains(loc)) enemies.getEnemy(i).startDying();

        }

    }

}

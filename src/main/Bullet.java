package main;

import javax.swing.*;
import java.util.ArrayList;

public class Bullet extends JLabel {

    public static final int bulletWidth = 25, bulletHeight = 25;

    private int health;
    private Coords loc, startLoc;
    private double slope, yInt;
    private boolean vertical;
    private int speed;

    private ArrayList<Enemy> updateList;

    public Bullet(int piercePower, Coords startLoc, int speed) {
        health = piercePower;
        loc = new Coords(startLoc.getX(), startLoc.getY());
        this.startLoc = new Coords(startLoc.getX(), startLoc.getY());
        vertical = false;
        this.speed = speed;
        this.slope = getSlope();
        yInt = -(slope * loc.getX()) + loc.getY();
        setIcon(new ImageIcon("D:/Pictures/FinishedProblems.png"));

    }

    public void update(EnemyList enemies, Map obstacleMap) {
        updateList = new ArrayList<Enemy>();
        double lowXBound = loc.getX();
        move();
        double highXBound = loc.getX();
        if(highXBound < lowXBound) {
            double x = lowXBound;
            lowXBound = highXBound;
            highXBound = x;

        }
        for(int i = 0; i < enemies.getListSize(); i++) {
            if(enemies.getEnemy(i).containsLineSegment(slope, yInt, lowXBound, highXBound))
                updateList.add(enemies.getEnemy(i));

        }

        Coords[] obstacleIntersections = obstacleMap.getLineSegmentIntersections(slope, yInt, lowXBound, highXBound);
        if(obstacleIntersections.length != 0) {
            Coords wantedIntersection = obstacleIntersections[0];
            for(Coords x : obstacleIntersections) {
                if(startLoc.distanceFrom(x) < startLoc.distanceFrom(wantedIntersection)) wantedIntersection = new Coords(x.getX(), x.getY());

            }
            for(int i = updateList.size() - 1; i >= 0; i--) {
                if(startLoc.distanceFrom(updateList.get(i).getLoc()) > startLoc.distanceFrom(wantedIntersection)) updateList.remove(i);

            }
            health = 0;

        } else setBounds();

        for(int i = 0; i < updateList.size(); i++) updateList.get(i).startDying();

    }

    public boolean isDead() {
        return health <= 0;

    }

    private void move() {
        loc.moveSDV(slope, speed, vertical);

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

    public Coords getLoc() {
        Coords retVal = new Coords(loc.getX(), loc.getY());
        return retVal;

    }

    private void setBounds() {
        setBounds((int) loc.getX() - (bulletWidth / 2), (int) loc.getY() - (bulletHeight / 2), bulletWidth, bulletHeight);

    }

}

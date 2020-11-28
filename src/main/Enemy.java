package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends JLabel {

    public final static int realizeTime = 1500, attackTime = 1000, dyingTime = 2500;
    public final static int realizeDistance = 800, attackDistance = 50;
    private final static int speed = 4;
    public final static int hitboxWidth = 87;
    public final static int hitboxHeight = 120;

    private int health;

    private State state;
    private long timer;

    private static final ImageIcon shadowPNG = new ImageIcon("src/images/shadow.png");
    private static final int shadowWidth = shadowPNG.getIconWidth();
    private static final int shadowHeight = shadowPNG.getIconHeight();
    private JLabel shadow;

    private JLabel jl;

    private Coords coords;

    public enum State {
        IDLE, REALIZING, CHARGING, ATTACKING, DYING, DEAD

    }

    public Enemy(Map obstacleMap, Random random, Coords playerLoc) {

        health = 1000;

        while(true) {
            coords = new Coords(random.nextInt(obstacleMap.getWidth()), random.nextInt(obstacleMap.getHeight()));
            if(coords.distanceFrom(playerLoc) < Math.sqrt(Math.pow(Main.windowWidth, 2) + Math.pow(Main.windowHeight, 2)))
                continue;
            for(int x = (int) coords.getX() - (shadowWidth / 2); x <= (int) coords.getX() + (shadowWidth / 2); x++) {
                for(int y = (int) coords.getY() - shadowHeight; y <= (int) coords.getY() + (shadowWidth / 2); y++) {
                    if(obstacleMap.getLoc(x, y)) continue;

                }

            }
            break;

        }

        jl = new JLabel();
        add(jl);

        shadow = new JLabel(shadowPNG);
        add(shadow);

        state = State.IDLE;
        if(random.nextInt(10) != 0) startRealizing(playerLoc);

    }

    public void update(Player player, Map obstacleMap) {
        Coords playerLoc = player.getLoc();
        updateState(player);
        setBounds(playerLoc);
        safeMove(playerLoc, obstacleMap);

        switch(state) {
            case IDLE:
                jl.setText("idle");
                break;
            case REALIZING:
                jl.setText("realizing");
                break;
            case CHARGING:
                jl.setText("charging");
                break;
            case ATTACKING:
                jl.setText("attacking");
                break;
            case DYING:
                jl.setText("dying");
                break;
            case DEAD:
                jl.setText("dead");
                break;

        }

    }

    private void updateState(Player player) {
        Coords playerLoc = player.getLoc();
        if(state != State.DYING) {
            if(state == State.IDLE && playerLoc.distanceFrom(coords) <= realizeDistance) startRealizing(playerLoc);
            startCharging(playerLoc);
            attack(player);
            if(health <= 0 && state != State.DYING && state != State.DEAD) startDying();

        } else if(System.currentTimeMillis() - timer > dyingTime) state = State.DEAD;

    }

    private void move(Coords playerLoc) {
        if(state == State.CHARGING && playerLoc.distanceFrom(coords) > attackDistance) {
            double xDiff, yDiff;
            if(playerLoc.getX() == coords.getX()) {
                xDiff = 0;
                if(playerLoc.getY() > coords.getY()) yDiff = speed;
                else yDiff = -speed;

            } else {
                double slope = (playerLoc.getY() - coords.getY()) / (playerLoc.getX() - coords.getX());
                xDiff = speed / Math.sqrt(Math.pow(slope, 2) + 1);
                if(playerLoc.getX() < coords.getX()) xDiff = -xDiff;
                yDiff = slope * xDiff;

            }
            coords.moveX(xDiff);
            coords.moveY(yDiff);

        }

    }

    private void safeMove(Coords playerLoc, Map obstacleMap) {
        Coords startPoint = new Coords(coords.getX(), coords.getY());

        move(playerLoc);

        for(int i = (int) coords.getX() - (shadowWidth / 2); i < coords.getX() + (shadowWidth / 2); i++)
            for(int j = (int) coords.getY() - (shadowHeight / 2); j < coords.getY() + (shadowHeight / 2); j++) {
                if(obstacleMap.getLoc(i, j)) coords = new Coords(startPoint.getX(), startPoint.getY());
                break;

            }

    }

    private void startRealizing(Coords playerLoc) {
        state = State.REALIZING;
        timer = System.currentTimeMillis();

    }

    private void startCharging(Coords playerLoc) {
        if((state == State.REALIZING && System.currentTimeMillis() - timer > realizeTime) || (state == State.ATTACKING && playerLoc.distanceFrom(coords) > attackDistance)) {
            state = State.CHARGING;

        }

    }

    private void attack(Player player) {
        if(state == State.CHARGING && player.getLoc().distanceFrom(coords) <= attackDistance) {
            state = State.ATTACKING;
            player.takeDamage();
            timer = System.currentTimeMillis();

        } else if(state == State.ATTACKING && System.currentTimeMillis() - timer > attackTime) {
            player.takeDamage();
            timer = System.currentTimeMillis();

        }

    }

    public void startDying() {
        if(state != State.DYING) {
            state = State.DYING;
            timer = System.currentTimeMillis();

        }

    }

    public Coords getLoc() {
        Coords retVal = new Coords(coords.getX(), coords.getY());
        return retVal;

    }

    public boolean isDead() {
        return state == State.DEAD;

    }

    private void setBounds(Coords playerLoc) {
        int x, y;
        x = (Main.windowWidth / 2) - (int) playerLoc.getX() + (int) coords.getX();
        y = (Main.windowHeight / 2) - (int) playerLoc.getY() + (int) coords.getY();
        setBounds(x - 100, y - 100, 200, 2000);
        shadow.setBounds((200 - shadowWidth) / 2, (200 - shadowHeight) / 2, shadowWidth, shadowHeight);
        jl.setBounds(0, 0, 200, 100);

    }

    public boolean shadowContains(Coords location) {
        boolean retVal = false;
        if(coords.getX() - (shadowWidth / 2) < location.getX() && location.getX() < coords.getX() + (shadowWidth / 2))
            retVal = true;
        if(coords.getY() - shadowHeight < location.getY() && location.getY() < coords.getY()) retVal = true;
        return retVal;

    }

    public boolean contains(Coords point) {
        boolean retVal = false;
        if(point.getX() > coords.getX() - (hitboxWidth / 2) && point.getX() < coords.getX() + (hitboxWidth / 2)) {
            if(point.getY() < coords.getY() && point.getY() > coords.getY() - hitboxHeight) {
                retVal = true;

            }

        }
        return retVal;

    }

    public State getState() {
        return state;

    }

    public boolean containsLineSegment(double slope, double yInt, double lowXBound, double highXBound) {
        boolean retVal = false;
        Coords upLeft = new Coords(coords.getX() - (hitboxWidth / 2), coords.getY() - hitboxHeight);
        Coords downRight = new Coords(coords.getX() + (hitboxWidth / 2), coords.getY());
        double wantedXStart, wantedXEnd;
        wantedXStart = Math.max(upLeft.getX(), lowXBound);
        wantedXEnd = Math.min(downRight.getX(), highXBound);
        if(lowXBound <= downRight.getX() && highXBound >= upLeft.getX()) {
            retVal = true;
            double enterLoc = (slope * wantedXStart) + yInt;
            double exitLoc = (slope * wantedXEnd) + yInt;
            if((enterLoc < upLeft.getY() && exitLoc < upLeft.getY()) || (enterLoc > downRight.getY() && exitLoc > downRight.getY())) {
                retVal = false;

            }

        }
        return retVal;

    }

    public double distanceToLocationFromSelectSegment(double slope, double yInt, double lowXBound, double highXBound, Coords loc) {
        double startX, endX;
        if(coords.getX() - (hitboxWidth / 2) < lowXBound) startX = lowXBound;
        else startX = loc.getX();
        if(coords.getX() + (hitboxWidth / 2) < highXBound) endX = loc.getX();
        else endX = highXBound;

        Coords contestant1, contestant2;
        contestant1 = new Coords(startX, (slope * startX) + yInt);
        contestant2 = new Coords(endX, (slope * endX) + yInt);

        return Math.min(contestant1.distanceFrom(loc), contestant2.distanceFrom(loc));

    }

}

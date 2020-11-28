package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends JLabel {

    private final static int speed = 5;
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
        IDLE, REALIZING, ATTACKING, DYING, DEAD

    }

    public Enemy(int bkgWidth, int bkgHeight, Random random) {

        state = State.IDLE;

        health = 1000;

        coords = new Coords(random.nextInt(bkgWidth), random.nextInt(bkgHeight));

        jl = new JLabel();
        add(jl);

        shadow = new JLabel(shadowPNG);
        add(shadow);

    }

    public void update(Coords playerLoc) {
        updateState(playerLoc);
        setBounds(playerLoc);

        switch(state) {
            case IDLE:
                jl.setText("idle");
                break;
            case REALIZING:
                jl.setText("realizing");
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

    private void updateState(Coords playerLoc) {
        if(state != State.DYING) {
            if(playerLoc.distanceFrom(coords) < (Main.windowHeight / 2) - 100) startRealizing();
            if(System.currentTimeMillis() - timer > 1000 && state == State.REALIZING) state = State.ATTACKING;
            if(health <= 0 && state != State.DYING && state != State.DEAD) startDying();

        } else if(System.currentTimeMillis() - timer > 2500) state = State.DEAD;

    }

    private void startRealizing() {
        if(state == State.IDLE) {
            state = State.REALIZING;
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
            System.out.println("1. " + lowXBound + ", " + downRight.getX());
            System.out.println("2. " + highXBound + ", " + upLeft.getX());
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

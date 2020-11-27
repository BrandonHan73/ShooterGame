package main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends JLabel {

    private final static int speed = 5;

    private int health;

    private State state;
    private long dyingTimer;

    private int windowWidth, windowHeight;

    private Coords coords;

    public enum State {
        IDLE, REALIZING, ATTACKING, DYING, DEAD

    }

    public Enemy(int windowWidth, int frameHeight, int bkgWidth, int bkgHeight, Random random) {

        this.windowWidth = windowWidth;
        this.windowHeight = frameHeight;

        health = 1000;

        coords = new Coords(random.nextInt(bkgWidth), random.nextInt(bkgHeight));

    }

    public void update(Coords playerLoc) {
        updateState(playerLoc);
        setBounds(playerLoc);

        health--;
        setText("" + health);

    }

    private void updateState(Coords playerLoc) {
        if(coords.distanceFrom(playerLoc) < 100) {
            state = State.ATTACKING;
            state = State.DYING;
            dyingTimer = System.currentTimeMillis();

        }
        if(health <= 0 && state != State.DYING && state != State.DEAD) {
            state = State.DYING;
            dyingTimer = System.currentTimeMillis();

        }
        if(state == State.DYING && Math.abs(dyingTimer - System.currentTimeMillis()) > 2500) {
            state = State.DEAD;

        }

    }

    public boolean isDead() {
        if(state == State.DEAD) return true;
        else return false;

    }

    private void setBounds(Coords playerLoc) {
        int x, y;
        x = (windowWidth / 2) - (int)playerLoc.getX() + (int)coords.getX();
        y = (windowHeight / 2) - (int)playerLoc.getY() + (int)coords.getY();
        setBounds(x, y, 100, 30);

    }

}

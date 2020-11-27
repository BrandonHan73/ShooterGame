package main;

import javax.swing.*;
import java.util.Random;

public class Enemy extends JLabel {

    private int health;
    private Random random;

    private State state;
    private long dyingTimer;

    private int windowWidth, windowHeight;
    private int bkgWidth, bkgHeight;

    private Coords coords;

    public enum State {
        IDLE, REALIZING, ATTACKING, DYING, DEAD

    }

    public Enemy(int windowWidth, int frameHeight, int bkgWidth, int bkgHeight) {

        this.windowWidth = windowWidth;
        this.windowHeight = frameHeight;
        this.bkgWidth = bkgWidth;
        this.bkgHeight = bkgHeight;

        random = new Random();

        health = 1000;

        coords = new Coords(random.nextInt(1000), random.nextInt(1000));

    }

    public void update(Coords playerLoc) {
        setBounds(playerLoc);

        health--;
        setText("" + health);

    }

    private void checkDeath() {
        if(health <= 0 && state != State.DYING && state != State.DEAD) {
            state = State.DYING;
            dyingTimer = System.currentTimeMillis();

        }
        if(state == State.DYING && Math.abs(dyingTimer - System.currentTimeMillis()) > 2500) {
            state = State.DEAD;

        }

    }

    public int getHealth() {
        return health;

    }

    private void setBounds(Coords playerLoc) {
        int x, y;
        x = (windowWidth / 2) - (int)playerLoc.getX() + (int)coords.getX();
        y = (windowHeight / 2) - (int)playerLoc.getY() + (int)coords.getY();
        setBounds(x, y, 100, 30);

    }

}

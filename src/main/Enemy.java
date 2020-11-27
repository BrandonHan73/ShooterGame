package main;

import javax.swing.*;
import java.util.Random;

public class Enemy extends JLabel {

    private final static int speed = 5;
    public final static int hitboxWidth = 250;
    public final static int hitboxHeight = 500;

    private int health;

    private State state;
    private long dyingTimer;

    private int windowWidth, windowHeight;

    private static final ImageIcon shadowPNG = new ImageIcon("src/images/shadow.png");
    private static final int shadowWidth = shadowPNG.getIconWidth();
    private static final int shadowHeight = shadowPNG.getIconHeight();
    private JLabel shadow;

    private JLabel jl;

    private Coords coords;

    public enum State {
        IDLE, REALIZING, ATTACKING, DYING, DEAD

    }

    public Enemy(int windowWidth, int frameHeight, int bkgWidth, int bkgHeight, Random random) {

        state = State.IDLE;

        this.windowWidth = windowWidth;
        this.windowHeight = frameHeight;

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
            case IDLE: jl.setText("idle"); break;
            case REALIZING: jl.setText("realizing"); break;
            case ATTACKING: jl.setText("attacking"); break;
            case DYING: jl.setText("dying"); break;
            case DEAD: jl.setText("dead"); break;

        }

    }

    private void updateState(Coords playerLoc) {
        if(state != State.DYING && coords.distanceFrom(playerLoc) < 100) state = State.ATTACKING;
        if(health <= 0 && state != State.DYING && state != State.DEAD) startDying();
        if(state == State.DYING && Math.abs(dyingTimer - System.currentTimeMillis()) > 2500) state = State.DEAD;

    }

    public void startDying() {
        if(state != State.DYING) {
            state = State.DYING;
            dyingTimer = System.currentTimeMillis();

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
        x = (windowWidth / 2) - (int)playerLoc.getX() + (int)coords.getX();
        y = (windowHeight / 2) - (int)playerLoc.getY() + (int)coords.getY();
        setBounds(x - 100, y - 100, 200, 2000);
        shadow.setBounds(0, 100, shadowWidth, shadowHeight);
        jl.setBounds(0, 0, 200, 100);

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

    public boolean containsLineSegment(double slope, double yInt, double lowXBound, double highXBound) {
        boolean retVal = false;
        Coords upLeft = new Coords(coords.getX() - (hitboxWidth / 2), coords.getY() - hitboxHeight);
        Coords downRight = new Coords(coords.getX() + (hitboxWidth / 2), coords.getY());
        if(lowXBound <= downRight.getX() && highXBound >= upLeft.getX()) {
            retVal = true;
            double enterLoc = (slope * upLeft.getX()) + yInt;
            double exitLoc = (slope * downRight.getX()) + yInt;
            if((enterLoc < upLeft.getY() && exitLoc < upLeft.getY()) || (enterLoc > downRight.getY() && exitLoc > downRight.getY())) {
                retVal = false;

            }

        }
        return retVal;

    }

}

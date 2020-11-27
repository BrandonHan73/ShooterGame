package main;

import javax.swing.*;
import java.util.Random;

public class Enemy extends JLabel {

    private final static int speed = 5;

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

        health--;
        switch(state) {
            case IDLE: jl.setText("idle"); break;
            case REALIZING: jl.setText("realizing"); break;
            case ATTACKING: jl.setText("attacking"); break;
            case DYING: jl.setText("dying"); break;
            case DEAD: jl.setText("dead"); break;

        }

    }

    private void updateState(Coords playerLoc) {
        if(coords.distanceFrom(playerLoc) < 100) state = State.ATTACKING;
        if(health <= 0 && state != State.DYING && state != State.DEAD) startDying();
        if(state == State.DYING && Math.abs(dyingTimer - System.currentTimeMillis()) > 2500) state = State.DEAD;

    }

    private void startDying() {
        state = State.DYING;
        dyingTimer = System.currentTimeMillis();

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

}

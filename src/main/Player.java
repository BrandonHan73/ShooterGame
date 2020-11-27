package main;

import javax.swing.*;

public class Player extends JLabel {

    private final ImageIcon shadowPNG = new ImageIcon("src/images/shadow.png");
    private final int shadowWidth = shadowPNG.getIconWidth();
    private final int shadowHeight = shadowPNG.getIconHeight();
    private JLabel shadow;

    private boolean direction;

    private String imageDirectoryPath;

    private Coords coords;

    private int windowWidth, windowHeight;

    private BulletZone bulletZone;
    private BulletZone deathZone;

    public enum ArmState {
        IDLE, SHOOTING

    }

    public enum LegState {
        IDLE, MOVING

    }

    public Player(String imageDirectoryPath, Coords loc, int windowWidth, int windowHeight) {
        this.imageDirectoryPath = imageDirectoryPath;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        coords = new Coords(loc.getX(), loc.getY());
        bulletZone = new BulletZone();
        deathZone = new BulletZone();

        setBounds(0, 0, windowWidth, windowHeight);

        shadow = new JLabel(shadowPNG);
        shadow.setBounds((windowWidth / 2) - (shadowWidth / 2), (windowHeight / 2) - (shadowWidth / 2), shadowWidth, shadowHeight);
        add(shadow);

    }

    public void update() {
        move();

    }

    private void takeDamage() {}

    private void move() {
    }

    private void setBounds() {}

    private void lookLeft() {
        direction = false;

    }

    private void lookRight() {
        direction = true;

    }

    private String getDirection() {
        if(direction) return "right";
        else return "left";

    }

    public Coords getLoc() {
        Coords retVal = new Coords(coords.getX(), coords.getY());
        return retVal;

    }

}

package main;

import javax.swing.*;

public class Player extends JLabel {

    private final double speed = 5;

    // Temporary
    private JLabel upperText;
    private JLabel lowerText;

    private final double rt2 = Math.sqrt(2);

    private final ImageIcon shadowPNG = new ImageIcon("src/images/shadow.png");
    private final int shadowWidth = shadowPNG.getIconWidth();
    private final int shadowHeight = shadowPNG.getIconHeight();
    private JLabel shadow;

    private boolean direction;

    private ArmState armState;
    private LegState legState;

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

        // Temporary
        upperText = new JLabel();
        lowerText = new JLabel();
        upperText.setBounds(windowWidth / 2, windowHeight / 2 - 30, 200, 30);
        lowerText.setBounds(windowWidth / 2, windowHeight / 2, 200, 30);
        add(upperText);
        add(lowerText);

        lookRight();
        armState = ArmState.IDLE;
        legState = LegState.IDLE;

        coords = new Coords(loc.getX(), loc.getY());
        bulletZone = new BulletZone();
        deathZone = new BulletZone();

        setBounds(0, 0, windowWidth, windowHeight);

        shadow = new JLabel(shadowPNG);
        shadow.setBounds((windowWidth / 2) - (shadowWidth / 2), (windowHeight / 2) - (shadowHeight / 2), shadowWidth, shadowHeight);
        add(shadow);

    }

    public void update() {
        move();
        updateGraphics();

    }

    private void takeDamage() {}

    private void updateGraphics() {
        // Temporary
        switch(armState) {
            case IDLE: upperText.setText("idle"); break;
            case SHOOTING: upperText.setText("shooting"); break;

        }
        switch(legState) {
            case IDLE: lowerText.setText("idle"); break;
            case MOVING: lowerText.setText("moving"); break;

        }

    }

    private void move() {
        boolean w = Keyboard.getKey(Keyboard.w);
        boolean a = Keyboard.getKey(Keyboard.a);
        boolean s = Keyboard.getKey(Keyboard.s);
        boolean d = Keyboard.getKey(Keyboard.d);

        double wantedDistance = speed;

        if(w && s) {w = false; s = false;}
        if(a && d) {a = false; d = false;}

        if((w || s) && (a || d)) wantedDistance /= rt2;

        if((w || s) || (a || d)) legState = LegState.MOVING;
        else legState = LegState.IDLE;

        if(w) coords.moveY(-wantedDistance);
        if(a) coords.moveX(-wantedDistance);
        if(s) coords.moveY(wantedDistance);
        if(d) coords.moveX(wantedDistance);

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

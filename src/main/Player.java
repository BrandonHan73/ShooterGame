package main;

import javax.swing.*;

import static main.Main.windowHeight;
import static main.Main.windowWidth;

public class Player extends JLabel {

    private final double speed = 5;

    // Temporary
    private JLabel upperText;
    private JLabel lowerText;

    private final double rt2 = Math.sqrt(2);

    private final int startHealth = 100;

    private final ImageIcon shadowPNG = new ImageIcon("src/images/shadow.png");
    private final int shadowWidth = shadowPNG.getIconWidth();
    private final int shadowHeight = shadowPNG.getIconHeight();
    private JLabel shadow;

    private int bkgWidth, bkgHeight;

    private boolean direction;

    private ArmState armState;
    private LegState legState;

    private String imageDirectoryPath;

    private Coords coords;

    private int health;
    private final ImageIcon healthPNG = new ImageIcon("src/images/health.png");
    private final ImageIcon healthBkgPNG = new ImageIcon("src/images/healthBarBackground.png");
    private JLabel healthLabel;
    private JLabel healthBkg;

    public enum ArmState {
        IDLE, SHOOTING

    }

    public enum LegState {
        IDLE, MOVING

    }

    public Player(String imageDirectoryPath, Coords loc, int bkgWidth, int bkgHeight) {
        this.imageDirectoryPath = imageDirectoryPath;
        this.bkgWidth = bkgWidth;
        this.bkgHeight = bkgHeight;

        health = startHealth;
        healthLabel = new JLabel(healthPNG);
        healthBkg = new JLabel(healthBkgPNG);
        add(healthLabel);
        add(healthBkg);
        healthBkg.setBounds(10, 10, healthBkgPNG.getIconWidth(), healthBkgPNG.getIconHeight());

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

        setBounds(0, 0, windowWidth, windowHeight);

        shadow = new JLabel(shadowPNG);
        shadow.setBounds((windowWidth / 2) - (shadowWidth / 2), (windowHeight / 2) - (shadowHeight / 2), shadowWidth, shadowHeight);
        add(shadow);

    }

    public void update(Map obstacleMap) {
        safeMove(obstacleMap);
        shoot();
        updateGraphics();

    }

    public void takeDamage() {
        health--;
    }

    private void updateGraphics() {
        healthLabel.setBounds(20, 20, health * 10, 50);

        // Temporary
        switch(armState) {
            case IDLE:
                upperText.setText("idle");
                break;
            case SHOOTING:
                upperText.setText("shooting");
                break;

        }
        switch(legState) {
            case IDLE:
                lowerText.setText("idle");
                break;
            case MOVING:
                lowerText.setText("moving");
                break;

        }

    }

    private void shoot() {
        if(Mouse.getM1()) armState = ArmState.SHOOTING;
        else armState = ArmState.IDLE;

    }

    private void safeMove(Map obstacleMap) {
        Coords startPoint = new Coords(coords.getX(), coords.getY());

        move();

        boolean permission = true;

        for(int i = (int) coords.getX() - (shadowWidth / 2); i < coords.getX() + (shadowWidth / 2); i++)
            for(int j = (int) coords.getY() - (shadowHeight / 2); j < coords.getY() + (shadowHeight / 2); j++) {
                if((0 > i || i > bkgWidth) || (0 > j || j > bkgHeight)) permission = false;
                if(!permission) break;
                if(obstacleMap.getLoc(i, j)) permission = false;

            }

        if(!permission) coords = new Coords(startPoint.getX(), startPoint.getY());

    }

    private void move() {
        boolean w = Keyboard.getKey(Keyboard.w);
        boolean a = Keyboard.getKey(Keyboard.a);
        boolean s = Keyboard.getKey(Keyboard.s);
        boolean d = Keyboard.getKey(Keyboard.d);

        double wantedDistance = speed;

        if(w && s) {
            w = false;
            s = false;
        }
        if(a && d) {
            a = false;
            d = false;
        }

        if((w || s) && (a || d)) wantedDistance /= rt2;

        if((w || s) || (a || d)) legState = LegState.MOVING;
        else legState = LegState.IDLE;

        if(w) coords.moveY(-wantedDistance);
        if(a) coords.moveX(-wantedDistance);
        if(s) coords.moveY(wantedDistance);
        if(d) coords.moveX(wantedDistance);

    }

    public boolean shadowContains(Coords location) {
        boolean retVal = false;
        if(coords.getX() - (shadowWidth / 2) < location.getX() && location.getX() < coords.getX() + (shadowWidth / 2))
            retVal = true;
        if(coords.getY() - shadowHeight < location.getY() && location.getY() < coords.getY()) retVal = true;
        return retVal;

    }

    private void setBounds() {
    }

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

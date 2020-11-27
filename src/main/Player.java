package main;

import javax.swing.*;

public class Player extends JLabel {

    private Coords coords;

    private BulletZone bulletZone;
    private BulletZone deathZone;

    public Player(Coords loc) {
        coords = new Coords(loc.getX(), loc.getY());
        bulletZone = new BulletZone();
        deathZone = new BulletZone();

    }

    public void update() {}

    private void takeDamage() {}

    private void move() {}

    private void setBounds() {}

    public Coords getLoc() {
        Coords retVal = new Coords(coords.getX(), coords.getY());
        return retVal;

    }

}

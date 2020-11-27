package main;

public class Player {

    private Coords coords;

    private BulletZone bulletZone;
    private BulletZone deathZone;

    public Player() {
        coords = new Coords();
        bulletZone = new BulletZone();
        deathZone = new BulletZone();

    }

    public void update() {}

    private void takeDamage() {}

    private void move() {}

    private void setBounds() {}

}

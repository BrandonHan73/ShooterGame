package main;

import javax.swing.*;

public class Game extends JLabel {

    private int windowWidth, windowHeight;

    private Coords playerCoords;
    private BulletZone enemyBulletZone, playerBulletZone;

    private Background background;
    private Player player;
    private Enemy enemies;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        setBounds(0, 0, windowWidth, windowHeight);

        background = new Background("src/images/background.png", windowWidth, windowHeight);
        player = new Player(new Coords(100, 100));
        enemies = new Enemy();

        add(background);
        add(player);
        add(enemies);

    }

    public void update() {
        Coords playerLoc = player.getLoc();

        background.update(playerLoc);
        player.update();
        enemies.update();

    }

}

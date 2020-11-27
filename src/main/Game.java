package main;

import javax.swing.*;

public class Game extends JLabel {

    private int windowWidth, windowHeight;

    private Coords playerCoords;

    private Background background;
    private Player player;
    private EnemyList enemies;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        setBounds(0, 0, windowWidth, windowHeight);

        background = new Background("src/images/background.png", windowWidth, windowHeight);
        player = new Player("src/images/player", new Coords(1000, 100), windowWidth, windowHeight);
        enemies = new EnemyList(1000, windowWidth, windowHeight, background.getIconWidth(), background.getIconHeight());

        add(player);
        add(enemies);
        add(background);

    }

    public void update() {
        player.update(background.getObstacleMap());

        playerCoords = player.getLoc();

        background.update(playerCoords, enemies);
        enemies.update(playerCoords);

    }

}

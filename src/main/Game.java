package main;

import javax.swing.*;
import java.awt.*;

public class Game extends JLabel {

    private Coords playerCoords;

    private Background background;
    private Player player;
    private EnemyList enemies;

    public Game() {

        setBounds(0, 0, Main.windowWidth, Main.windowHeight);

        background = new Background("src/images/background.png");
        player = new Player("src/images/player", new Coords(100, 100), background.getIconWidth(), background.getIconHeight());
        enemies = new EnemyList(1000, background.getIconWidth(), background.getIconHeight());

        add(player);
        add(enemies);
        add(background);

    }

    public void update() {
        player.update(background.getObstacleMap());

        playerCoords = player.getLoc();

        background.update(playerCoords, enemies);
        enemies.update(background.getObstacleMap(), player);

    }

}

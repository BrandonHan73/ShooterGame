package main;

public class Game {

    private int windowWidth, windowHeight;

    private Coords playerCoords;
    private BulletZone enemyBulletZone, playerBulletZone;

    private Background background;
    private Player player;
    private Enemy enemies;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        background = new Background();
        player = new Player();
        enemies = new Enemy();

    }

    public void update() {
        background.update();
        player.update();
        enemies.update();

    }

}

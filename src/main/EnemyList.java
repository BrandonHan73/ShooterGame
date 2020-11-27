package main;

import javax.swing.*;
import java.util.ArrayList;

public class EnemyList extends JLabel {

    private int spawnRate;
    private long spawnTime;

    private int frameWidth, frameHeight;

    private BulletZone bulletZone;
    private BulletZone deathZone;

    private ArrayList<Enemy> mainList;

    public EnemyList(int spawnRate, int frameWidth, int frameHeight) {
        mainList = new ArrayList<Enemy>();

        bulletZone = new BulletZone();
        deathZone = new BulletZone();

        this.spawnRate = spawnRate;
        spawnTime = System.currentTimeMillis();

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

    }

    public void update() {
    }

    private void spawn() {
        if(spawnTime > spawnRate) {
            mainList.add(new Enemy());

        }

    }

}

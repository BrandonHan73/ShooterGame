package main;

import javax.swing.*;
import java.util.ArrayList;

public class EnemyList extends JLabel {

    private int spawnRate;
    private long spawnTime;

    private int frameWidth, frameHeight;
    private int bkgWidth, bkgHeight;

    private BulletZone bulletZone;
    private BulletZone deathZone;

    private ArrayList<Enemy> mainList;

    public EnemyList(int spawnRate, int frameWidth, int frameHeight, int bkgWidth, int bkgHeight) {
        mainList = new ArrayList<Enemy>();

        setBounds(0, 0, frameWidth, frameHeight);

        bulletZone = new BulletZone();
        deathZone = new BulletZone();

        this.spawnRate = spawnRate;
        spawnTime = System.currentTimeMillis();

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.bkgWidth = bkgWidth;
        this.bkgHeight = bkgHeight;

    }

    public void update(Coords playerLoc) {
        spawn();
        for(int i = 0; i < mainList.size(); i++) mainList.get(i).update(playerLoc);
        checkDeaths();

    }

    private void spawn() {
        long currentTime = System.currentTimeMillis();
        if(currentTime > spawnTime + spawnRate) {
            mainList.add(new Enemy(frameWidth, frameHeight, bkgWidth, bkgHeight));
            add(mainList.get(mainList.size() - 1));
            spawnTime = System.currentTimeMillis();

            System.out.println("enemy spawned");
            System.out.println(mainList.size());

        }

    }

    private void checkDeaths() {
        for(int i = mainList.size() - 1; i >= 0; i--) {
            if(mainList.get(i).getHealth() <= 0) {
                remove(mainList.get(i));
                mainList.remove(i);

            }

        }

    }

}

package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyList extends JLabel {

    private Random random;

    private int spawnRate;
    private long spawnTime;
    private int bkgWidth, bkgHeight;

    private ArrayList<Enemy> mainList;

    public EnemyList(int spawnRate, int bkgWidth, int bkgHeight) {
        mainList = new ArrayList<>();
        random = new Random();

        setBounds(0, 0, Main.windowWidth, Main.windowHeight);

        this.spawnRate = spawnRate;
        spawnTime = System.currentTimeMillis();

        this.bkgWidth = bkgWidth;
        this.bkgHeight = bkgHeight;

    }

    public void update(Map obstacleMap, Player player) {
        spawn(obstacleMap, player.getLoc());
        for(int i = 0; i < mainList.size(); i++) mainList.get(i).update(player, obstacleMap);
        checkDeaths();

    }

    private void spawn(Map obstacleMap, Coords playerLoc) {
        long currentTime = System.currentTimeMillis();
        if(currentTime > spawnTime + spawnRate) {
            mainList.add(new Enemy(obstacleMap, random, playerLoc));
            add(mainList.get(mainList.size() - 1));
            spawnTime = System.currentTimeMillis();

        }

    }

    private void checkDeaths() {
        for(int i = mainList.size() - 1; i >= 0; i--) {
            if(mainList.get(i).isDead()) {
                remove(mainList.get(i));
                mainList.remove(i);

            }

        }

    }

    public int getListSize() {
        return mainList.size();

    }

    public Enemy getEnemy(int index) {
        return mainList.get(index);

    }

}

package main;

import javax.swing.*;
import java.util.ArrayList;

public class BulletList extends JLabel {

    private ArrayList<Bullet> mainList;

    private int cooldown = 400;
    private long fireTime;

    private int bkgWidth, bkgHeight;

    public BulletList(int bkgWidth, int bkgHeight) {
        this.bkgWidth = bkgWidth;
        this.bkgHeight = bkgHeight;

        mainList = new ArrayList<Bullet>();
        fireTime = System.currentTimeMillis() - cooldown;
        setBounds(0, 0, bkgWidth, bkgHeight);

    }

    public void update(Coords playerLoc, int piercePower, int speed, EnemyList enemies, Map obstacleMap) {
        if(Mouse.getM1()) createBullet(piercePower, playerLoc, speed);
        for(int i = mainList.size(); i >= 0; i--) {
            if(i == mainList.size()) continue;
            mainList.get(i).update(enemies, obstacleMap);
            if(mainList.get(i).getLoc().getX() < 0 || mainList.get(i).getX() > bkgWidth || mainList.get(i).getLoc().getY() < 0 || mainList.get(i).getY() > bkgHeight || mainList.get(i).isDead()) {
                remove(mainList.get(i));
                mainList.remove(i);

            }

        }

    }

    public void createBullet(int piercePower, Coords startLoc, int speed) {
        if(fireTime + cooldown < System.currentTimeMillis()) {
            mainList.add(new Bullet(piercePower, startLoc, speed));
            add(mainList.get(mainList.size() - 1));
            fireTime = System.currentTimeMillis();

        }

    }

}

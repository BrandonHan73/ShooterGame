package main;

import javax.swing.*;
import java.util.ArrayList;

public class BulletList extends JLabel {

    private ArrayList<Bullet> mainList;

    private int cooldown = 100;
    private long fireTime;

    public BulletList(int bkgWidth, int bkgHeight) {
        mainList = new ArrayList<Bullet>();
        fireTime = System.currentTimeMillis() - cooldown;
        setBounds(0, 0, bkgWidth, bkgHeight);

    }

    public void update(Coords playerLoc, int piercePower, int speed, EnemyList enemies) {
        if(Mouse.getM1()) createBullet(piercePower, playerLoc, speed);
        for(int i = 0; i < mainList.size(); i++) mainList.get(i).update(enemies);

    }

    public void createBullet(int piercePower, Coords startLoc, int speed) {
        if(fireTime + cooldown < System.currentTimeMillis()) {
            mainList.add(new Bullet(piercePower, startLoc, speed));
            add(mainList.get(mainList.size() - 1));
            fireTime = System.currentTimeMillis();

        }

    }

}

package main;

import javax.swing.*;

public class Background extends JLabel {

    private int windowWidth, windowHeight;

    private Map obstacleMap;

    private Bullet bullet;

    private ImageIcon image;
    private int iconWidth, iconHeight;

    public Background(String imageFilePath, int windowWidth, int windowHeight) {
        image = new ImageIcon(imageFilePath);

        iconWidth = image.getIconWidth();
        iconHeight = image.getIconHeight();

        obstacleMap = new Map(iconWidth, iconHeight);
        obstacleMap.drawLine(new Coords(0, 0), new Coords(0, 1000), true);

        setBounds(new Coords(0, 0));

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        bullet = new Bullet(3, new Coords(0, 0), 3, 3);

        setIcon(image);

    }

    public void update(Coords playerLoc, EnemyList enemies) {
        setBounds(playerLoc);

        if(Mouse.getM1()) {
            bullet = new Bullet(1, playerLoc, 3, 3);

        }

        bullet.update(enemies);

//        System.out.println("Updated background");

    }

    private void changeIcon(String filePath) {
        image = new ImageIcon(filePath);
        iconWidth = image.getIconWidth();
        iconHeight = image.getIconHeight();

//        System.out.println("Changed background image");

    }

    public Map getObstacleMap() {
        return obstacleMap;

    }

    public int getIconWidth() {
        int retVal = iconWidth;
        return retVal;

    }

    public int getIconHeight() {
        int retVal = iconHeight;
        return retVal;

    }

    private void setBounds(Coords playerLoc) {
        int x, y;
        x = (windowWidth / 2) - (int)playerLoc.getX();
        y = (windowHeight / 2) - (int)playerLoc.getY();
        setBounds(x, y, iconWidth, iconHeight);

//        System.out.println("Set background location to " + x + ", " + y);

    }

}

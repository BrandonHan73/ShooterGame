package main;

import javax.swing.*;

public class Background extends JLabel {

    private int windowWidth, windowHeight;

    private Map obstacleMap;

    private BulletList bullets;

    private ImageIcon image;
    private int iconWidth, iconHeight;

    public Background(String imageFilePath, int windowWidth, int windowHeight) {
        image = new ImageIcon(imageFilePath);

        iconWidth = image.getIconWidth();
        iconHeight = image.getIconHeight();

        obstacleMap = new Map(iconWidth, iconHeight);
        obstacleMap.drawLine(new Coords(1000, 0), new Coords(1000, 1000), true);
        obstacleMap.drawLine(new Coords(1000, 1000), new Coords(500, 1000), true);
        obstacleMap.drawLine(new Coords(1000, 1000), new Coords(1500, 1500), true);
        obstacleMap.drawLine(new Coords(1000, 0), new Coords(500, 500), true);

        setBounds(new Coords(0, 0));

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        bullets = new BulletList(iconWidth, iconHeight);
        add(bullets);

        setIcon(image);

    }

    public void update(Coords playerLoc, EnemyList enemies) {
        setBounds(playerLoc);

        bullets.update(playerLoc, 2, 50, enemies, obstacleMap);

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

package main;

import javax.swing.*;

public class Background extends JLabel {

    private int windowWidth, windowHeight;

    private ImageIcon image;
    private int iconWidth, iconHeight;

    public Background(String imageFilePath, int windowWidth, int windowHeight) {
        image = new ImageIcon(imageFilePath);

        iconWidth = image.getIconWidth();
        iconHeight = image.getIconHeight();

        setBounds(new Coords(0, 0));

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        setIcon(image);

//        System.out.println("Created background");

    }

    public void update(Coords playerLoc) {
        setBounds(playerLoc);

//        System.out.println("Updated background");

    }

    private void changeIcon(String filePath) {
        image = new ImageIcon(filePath);
        iconWidth = image.getIconWidth();
        iconHeight = image.getIconHeight();

//        System.out.println("Changed background image");

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

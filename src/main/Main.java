package main;

import javax.swing.*;

public class Main {

    // Constants
    public static int windowWidth = 1900;
    public static int windowHeight = 1100;

    private static JFrame mainFrame;

    private static Game mainGame;
    private static Inventory mainInventory;

    // Inputs
    private static Keyboard keyboard;
    private static Mouse mouse;

    public static void main(String[] args) {

        // Set up window
        mainFrame = new JFrame("Shooter Game");
        mainFrame.setResizable(false);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set up variables
        mainGame = new Game(windowWidth, windowHeight);
        mainInventory = new Inventory();

        // Add to frame
        mainFrame.add(mainGame);
        mainFrame.add(mainInventory);
        mainFrame.addKeyListener(Keyboard.getKeyListener());

        mainFrame.setVisible(true);

        // Game loop
        while(true) {
            mainGame.update();
            mainInventory.update();
            waitMillis(15);

        }

    }

    private static void waitMillis(long time) {
        long targetTime = System.currentTimeMillis() + time;
        while(targetTime > System.currentTimeMillis()) {}

    }

}

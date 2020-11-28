package main;

import javax.swing.*;

public class Main {

    // Constants
    public static final int windowWidth = 1900;
    public static final int windowHeight = 1100;

    private static JFrame mainFrame;

    private static Game mainGame;
    private static Inventory mainInventory;

    public static void main(String[] args) {

        // Set up window
        mainFrame = new JFrame("Shooter Game");
        mainFrame.setResizable(false);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set up inputs
        Keyboard.init();
        Mouse.init();

        // Set up variables
        mainGame = new Game();
        mainInventory = new Inventory();

        // Add to frame
        mainFrame.add(mainGame);
        mainFrame.add(mainInventory);
        mainFrame.addKeyListener(Keyboard.getKeyListener());
        mainFrame.addMouseListener(Mouse.getMouseListener());
        mainFrame.addMouseMotionListener(Mouse.getMouseMotionListener());

        mainFrame.setVisible(true);

        // Game loop
        long loopStartTime;
        while(true) {
            loopStartTime = System.currentTimeMillis();
            mainGame.update();
            mainInventory.update();
            while(System.currentTimeMillis() - loopStartTime < 15) {}

        }

    }

}

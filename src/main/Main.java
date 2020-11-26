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

        mainFrame.setVisible(true);

    }

}

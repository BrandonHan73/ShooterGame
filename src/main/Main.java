package main;

import javax.swing.*;

public class Main {

    // Constants
    static final int windowWidth = 1900;
    static final int windowHeight = 1100;

    // Main window
    private static JFrame MainFrame;

    // Game
    private static Game MainGame;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Set up main window
        MainFrame = new JFrame("Shooter Game");
        MainFrame.setSize(windowWidth, windowHeight);
        MainFrame.setResizable(false);
        MainFrame.setLayout(null);
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);

        // Set up game
        MainGame = new Game();

        // Adding to main window;
        MainFrame.add(MainGame.get());

        // Setting bounds
        MainGame.get().setBounds(0, 0, windowWidth, windowHeight);

    }

}

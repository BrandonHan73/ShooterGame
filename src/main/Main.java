package main;

import javax.swing.*;

public class Main {

    // Constants


    // Main window
    private static JFrame MainFrame;

    // Game
    private static Game MainGame;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Set up main window
        MainFrame = new JFrame("Shooter Game");
        MainFrame.setSize(1900, 1100);
        MainFrame.setLayout(null);
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);

        // Set up game;
        MainGame = new Game();

    }

}

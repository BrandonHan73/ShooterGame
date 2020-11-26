package main;

import utils.*;

import javax.swing.*;

public class Main {

    // Constants
    static final int windowWidth = 1900;
    static final int windowHeight = 1100;

    // Main window
    private static JFrame MainFrame;

    // Game
    private static Game MainGame;

    // Keyboard and mouse
    private static Keyboard keyboard;
    private static Mouse mouse;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Set up main window
        MainFrame = new JFrame("Shooter Game");
        MainFrame.setSize(windowWidth, windowHeight);
        MainFrame.setResizable(false);
        MainFrame.setLayout(null);
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set up game
        MainGame = new Game(windowWidth, windowHeight);

        // Set up keyboard and mouse
        keyboard = new Keyboard();
        mouse = new Mouse();

        // Adding to main window
        MainFrame.add(MainGame);
        MainFrame.addKeyListener(keyboard.getKeyListener());
        MainFrame.addMouseListener(mouse.getMouseListener());
        MainFrame.addMouseMotionListener(mouse.getMouseMotionListener());

        MainFrame.setVisible(true);


        // Game loop
        while(true) {
            waitMillis(1000);

        }

    }

    public static void waitMillis(long time) {
        long targetTime = System.currentTimeMillis() + time;
        while(targetTime > System.currentTimeMillis()) {

        }

    }

}

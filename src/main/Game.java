package main;

import javax.swing.*;

public class Game extends JLabel {

    // Constants
    private int frameWidth, frameHeight;
    private final ImageIcon backgroundPNG = new ImageIcon("src/images/background.png");

    // Background
    private JLabel bkg;

    // Player
    private Player player;

    public Game(int width, int height) {

        // Set up constants
        frameWidth = width;
        frameHeight = height;

        // Set up panel
        setSize(frameWidth, height);
        setLayout(null);

        // Set up background
        bkg = new JLabel(backgroundPNG);
        bkg.setBounds(0, 0, 7090, 4120);

        // Set up player
        player = new Player(frameWidth, frameHeight);

        // Add to panel
        add(player);
        add(bkg);

    }

}

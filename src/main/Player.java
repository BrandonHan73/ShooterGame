package main;

import javax.swing.*;

public class Player extends JLabel {

    // Constants
    private int gameWidth, gameHeight;
    private final int frameWidth = 500, frameHeight = 500;

    public Player(int gameWidth, int gameHeight) {

        // Set up constants
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        // Set up frame
        setSize(frameWidth, frameHeight);
        setLayout(null);
        setBackground(null);
        setBounds((gameWidth / 2) - (frameWidth / 2), (gameHeight / 2) - (frameHeight / 2), frameWidth, frameHeight);

    }

}

package main;

import utils.Coordinate;

import javax.swing.*;

public class Player extends JLabel {

    // Constants
    private final double startX = 500, startY = 500;
    private int gameWidth, gameHeight;
    private final int frameWidth = 500, frameHeight = 500;

    private Coordinate coords;

    public Player(int gameWidth, int gameHeight) {

        // Set up constants
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        // Variables
        coords = new Coordinate(startX, startY);

        // Set up frame
        setSize(frameWidth, frameHeight);
        setLayout(null);
        setBackground(null);
        setBounds((gameWidth / 2) - (frameWidth / 2), (gameHeight / 2) - (frameHeight / 2), frameWidth, frameHeight);

    }

    public Coordinate getCoords() {
        return coords;

    }

}

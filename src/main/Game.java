package main;

import javax.swing.*;

public class Game extends JPanel {

    // Constants
    private int frameWidth, frameHeight;
    private final ImageIcon backgroundPNG = new ImageIcon("src/images/background.png");

    // Background
    private JLabel Background;

    public Game(int width, int height) {

        // Set up constants
        frameWidth = width;
        frameHeight = height;

        // Set up panel
        setSize(frameWidth, height);

        // Set up background
        Background = new JLabel(backgroundPNG);
        Background.setBounds(0, 0, frameWidth, frameHeight);

        // Add to panel
        add(Background);

    }

}

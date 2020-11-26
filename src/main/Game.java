package main;

import javax.swing.*;

public class Game {

    // Constants
    private int frameWidth, frameHeight;
    private final ImageIcon backgroundPNG = new ImageIcon("src/images/background.png");

    // Main panel
    private JPanel MainPanel;

    // Background
    private JLabel Background;

    public Game(int width, int height) {

        // Set up constants
        frameWidth = width;
        frameHeight = height;

        // Set up main panel
        MainPanel = new JPanel();
        MainPanel.setSize(frameWidth, height);

        // Set up background
        Background = new JLabel(backgroundPNG);
        Background.setBounds(0, 0, frameWidth, frameHeight);

        // Add to main panel
        MainPanel.add(Background);

    }

    public JPanel get() {
        return MainPanel;

    }

}

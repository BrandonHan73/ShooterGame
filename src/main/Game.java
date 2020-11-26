package main;

import utils.Keyboard;
import utils.Mouse;

import javax.swing.*;
import java.util.ArrayList;

public class Game extends JLabel {

    // Constants
    private final int speed = 5;
    private int frameWidth, frameHeight;
    private final ImageIcon backgroundPNG = new ImageIcon("src/images/background.png");

    // Background
    private JLabel bkg;

    // Player
    private Player player;

    // Enemies
    private ArrayList<Enemy> enemies;

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

        // Set up enemies
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy());

        // Add to panel
        add(player);
        add(bkg);

    }

    public void update(Keyboard k, Mouse m) {
        if(k.getKey(Keyboard.w)) {
            player.getCoords().moveUp(speed);

        }
        if(k.getKey(Keyboard.a)) {
            player.getCoords().moveLeft(speed);

        }
        if(k.getKey(Keyboard.s)) {
            player.getCoords().moveDown(speed);

        }
        if(k.getKey(Keyboard.d)) {
            player.getCoords().moveRight(speed);

        }

        bkg.setBounds(-player.getCoords().getIntX() + (frameWidth/2), -player.getCoords().getIntY() + (frameHeight/2), 7090, 4120);

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).setBounds(enemies.get(i).getCoords().getIntX() - player.getCoords().getIntX() + (frameWidth/2),enemies.get(i).getCoords().getIntY() - player.getCoords().getIntY() + (frameHeight/2), 100, 100);

        }

    }

}

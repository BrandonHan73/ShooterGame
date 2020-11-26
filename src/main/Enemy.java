package main;

import utils.Coordinate;

import javax.swing.*;

public class Enemy extends JLabel {

    private int health;
    private Coordinate coords;

    public Enemy() {
        coords = new Coordinate(600, 600);
        setIcon(new ImageIcon("D:/Pictures/FinishedProblems.PNG"));

    }

    public Coordinate getCoords() {
        return coords;

    }
}

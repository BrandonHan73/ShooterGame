package main;

import javax.swing.*;

public class Inventory extends JLabel {

    private State state;

    private final ImageIcon bkgImage = new ImageIcon("src/images/inventoryBackground.png");
    public final int windowWidth = bkgImage.getIconWidth(), windowHeight = bkgImage.getIconHeight();
    private final int moveSpeed = 100;
    public final int xLoc = 50;

    private int yLoc;

    public enum State {
        STORED, OPENING, OPEN, STORING

    }

    public Inventory() {
        yLoc = -windowHeight;

        state = State.STORED;
        setIcon(bkgImage);

    }

    public void update() {
        updateState();
        setBounds();

    }

    private void updateState() {
        if(Keyboard.getKey(Keyboard.e)) {
            if(state != State.OPEN) state = State.OPENING;

        } else if(state != State.STORED) state = State.STORING;
        if(state == State.OPENING) yLoc += moveSpeed;
        if(state == State.STORING) yLoc -= moveSpeed;
        if(yLoc <= -windowHeight) {
            state = State.STORED;
            yLoc = -windowHeight;

        }
        if(yLoc >= 0) {
            state = State.OPEN;
            yLoc = 0;

        }

    }

    private void setBounds() {
        setBounds(xLoc, yLoc, windowWidth, windowHeight);

    }

    public void show() {
    }

}

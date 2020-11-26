package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {

    // Constants
    private final int numOfKeys = 4;
    public static final int one = 1;
    public static final int two = 2;
    public static final int three = 3;
    public static final int four = 4;
    public static final int w = 5;
    public static final int a = 6;
    public static final int s = 7;
    public static final int d = 8;

    private KeyListener keyListener;

    private boolean[] keys;

    public Keyboard() {
        keys = new boolean[numOfKeys];

        for(int i = 0; i < numOfKeys; i++) {
            keys[i] = false;

        }

        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                switch(key) {
                    case '1': keys[Keyboard.one] = true; break;
                    case '2': keys[Keyboard.two] = true; break;
                    case '3': keys[Keyboard.three] = true; break;
                    case '4': keys[Keyboard.four] = true; break;
                    case 'w': keys[Keyboard.w] = true; break;
                    case 'a': keys[Keyboard.a] = true; break;
                    case 's': keys[Keyboard.s] = true; break;
                    case 'd': keys[Keyboard.d] = true; break;

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                char key = e.getKeyChar();
                switch(key) {
                    case '1': keys[Keyboard.one] = false; break;
                    case '2': keys[Keyboard.two] = false; break;
                    case '3': keys[Keyboard.three] = false; break;
                    case '4': keys[Keyboard.four] = false; break;
                    case 'w': keys[Keyboard.w] = false; break;
                    case 'a': keys[Keyboard.a] = false; break;
                    case 's': keys[Keyboard.s] = false; break;
                    case 'd': keys[Keyboard.d] = false; break;

                }

            }
        };

    }

    public void setKey(int key, boolean value) {
        keys[key] = value;

    }

    public boolean getKey(int key) {
        return keys[key];

    }

}

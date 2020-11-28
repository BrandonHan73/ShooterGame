package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {

    public static final int w = 0, a = 1, s = 2, d = 3, space = 4, e = 5, q = 6, r = 7, f = 8;
    private static final int amountOfSupportedKeys = 9;

    private static KeyListener keyListener;
    private static boolean[] keys;

    public static void init() {
        keys = new boolean[amountOfSupportedKeys];
        for(int i = 0; i < amountOfSupportedKeys; i++) {
            keys[i] = false;

        }

        keyListener = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                switch(key) {
                    case 'w':
                        keys[Keyboard.w] = true;
                        break;
                    case 'a':
                        keys[Keyboard.a] = true;
                        break;
                    case 's':
                        keys[Keyboard.s] = true;
                        break;
                    case 'd':
                        keys[Keyboard.d] = true;
                        break;
                    case ' ':
                        keys[Keyboard.space] = true;
                        break;
                    case 'e':
                        keys[Keyboard.e] = true;
                        break;
                    case 'q':
                        keys[Keyboard.q] = true;
                        break;
                    case 'r':
                        keys[Keyboard.r] = true;
                        break;
                    case 'f':
                        keys[Keyboard.f] = true;
                        break;

                }

            }

            public void keyReleased(KeyEvent e) {
                char key = e.getKeyChar();
                switch(key) {
                    case 'w':
                        keys[Keyboard.w] = false;
                        break;
                    case 'a':
                        keys[Keyboard.a] = false;
                        break;
                    case 's':
                        keys[Keyboard.s] = false;
                        break;
                    case 'd':
                        keys[Keyboard.d] = false;
                        break;
                    case ' ':
                        keys[Keyboard.space] = false;
                        break;
                    case 'e':
                        keys[Keyboard.e] = false;
                        break;
                    case 'q':
                        keys[Keyboard.q] = false;
                        break;
                    case 'r':
                        keys[Keyboard.r] = false;
                        break;
                    case 'f':
                        keys[Keyboard.f] = false;
                        break;

                }

            }

        };

    }

    public static boolean getKey(int key) {
        return keys[key];

    }

    public static KeyListener getKeyListener() {
        return keyListener;

    }

    private Keyboard() {
    }

}

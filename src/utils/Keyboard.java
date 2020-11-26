package utils;

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

    private boolean[] keys;

    public Keyboard() {
        keys = new boolean[numOfKeys];

        for(int i = 0; i < numOfKeys; i++) {
            keys[i] = false;

        }

    }

    public void setKey(int key, boolean value) {
        keys[key] = value;

    }

    public boolean getKey(int key) {
        return keys[key];

    }

}

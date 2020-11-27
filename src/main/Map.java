package main;

public class Map {

    private boolean[][] map;
    private int width, height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        map = new boolean[width][height];

        for(int i = 0; i < width; i++) for(int j = 0; j < height; j++) map[i][j] = false;

    }

}

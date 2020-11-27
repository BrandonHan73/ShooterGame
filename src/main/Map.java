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

    public void drawLine(Coords start, Coords end, boolean value) {
        boolean vertical = (start.getX() == end.getX());
        double slope = 0;
        if(!vertical) {
            slope = (start.getY() - end.getY()) / (start.getX() - end.getX());

        }

        if(slope < 1 && !vertical) {
            for(int i = (int)start.getX(); i != (int)end.getX(); i += 0) {
                map[i][(int)(slope * (i - (int)start.getX()) + (int)start.getY())] = value;
                if(start.getX() < end.getX()) i++;
                else i--;

            }

        } else {
            if(!vertical) slope = 1 / slope;
            for(int i = (int)start.getY(); i != (int)end.getY(); i += 0) {
                map[(int)(slope * (i - (int)start.getY()) + (int)start.getX())][i] = value;
                if(start.getY() < end.getY()) i++;
                else i--;

            }

        }
        map[(int)end.getX()][(int)end.getY()] = value;

    }

}

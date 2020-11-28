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
                if(i < 0) continue;
                map[i][(int)(slope * (i - (int)start.getX()) + (int)start.getY())] = value;
                if(start.getX() < end.getX()) i++;
                else i--;

            }

        } else {
            if(!vertical) slope = 1 / slope;
            for(int i = (int)start.getY(); i != (int)end.getY(); i += 0) {
                if(i < 0) continue;
                map[(int)(slope * (i - (int)start.getY()) + (int)start.getX())][i] = value;
                if(start.getY() < end.getY()) i++;
                else i--;

            }

        }
        map[(int)end.getX()][(int)end.getY()] = value;

    }

    public boolean getLoc(int x, int y) {
        int safeX = x, safeY = y;
        if(x < 0) safeX = 0;
        if(x >= map.length) safeX = map.length - 1;
        if(y < 0) safeY = 0;
        if(y >= map.length) safeY = map[0].length - 1;
        boolean retVal = map[safeX][safeY];
        return retVal;

    }

    public boolean containsLineSegment(double slope, double yInt, double lowXBound, double highXBound) {
        boolean retVal = false;
        if(Math.abs(slope) < 1) for (double i = lowXBound; i <= highXBound; i += 1) {
            double y = (slope * i) + yInt;
            if ((i < 0 || i > map.length - 1) || (y < 0 || y > map[0].length - 1)) continue;
            if (map[(int) i][(int) y]) retVal = true;

        } else {
            double startY = (slope * lowXBound) + yInt;
            double endY = (slope * highXBound) + yInt;
            if(startY > endY) {
                double temp = startY;
                startY = endY;
                endY = temp;

            }
            for(double y = startY; y <= endY; y += 1) {
                double x = (y - yInt) / slope;
                if ((x < 0 || x > map.length - 1) || (y < 0 || y > map[0].length - 1)) continue;
                if (map[(int) x][(int) y]) retVal = true;

            }

        }
        return retVal;

    }

}

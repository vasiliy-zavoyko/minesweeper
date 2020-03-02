package sweeper;

import java.util.ArrayList;

public class Ranges {

    static private Coordinate size;
    static private ArrayList<Coordinate> allCoordinates;

    static public Coordinate getSize() {
        return size;
    }

    static void setSize(Coordinate size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<>();
        for(int x = 0; x < size.x; x++) {
            for (int y =0; y < size.y; y++) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }
    }

    static public ArrayList<Coordinate> getAllCoordinates() {
        return allCoordinates;
    }

    public static boolean inRange(Coordinate coordinate) {
        return coordinate != null
                && coordinate.x >= 0
                && coordinate.x < size.x
                && coordinate.y >= 0
                && coordinate.y < size.y;
    }

}

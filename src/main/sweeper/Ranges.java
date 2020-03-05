package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {

    static private Coordinate size;
    static private ArrayList<Coordinate> allCoordinates;
    static private Random random = new Random();

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

    static boolean inRange(Coordinate coordinate) {
        return coordinate != null
                && coordinate.x >= 0
                && coordinate.x < size.x
                && coordinate.y >= 0
                && coordinate.y < size.y;
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coordinate> getCoordinateAround(Coordinate coordinate) {
        Coordinate around;
        ArrayList<Coordinate> list = new ArrayList<>();
        for (int x = coordinate.x - 1; x <= coordinate.x + 1; x++)
            for (int y = coordinate.y -1; y <= coordinate.y + 1; y++)
                if (inRange(around = new Coordinate(x,y)))
                    if (!around.equals(coordinate))
                        list.add(around);

        return list;
    }

}

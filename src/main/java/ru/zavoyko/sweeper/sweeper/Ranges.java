package ru.zavoyko.sweeper.sweeper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Ranges {

    private static Coordinate size;
    private static ArrayList<Coordinate> allCoordinates;
    private static final Random random = new Random();

    static Coordinate getSize() {
        return size;
    }

    static void setSize(Coordinate size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<>();
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }
    }

    static List<Coordinate> getAllCoordinates() {
        return allCoordinates;
    }

    static boolean inRange(Coordinate coordinate) {
        return coordinate != null
                && coordinate.getX() >= 0
                && coordinate.getX() < size.getX()
                && coordinate.getY() >= 0
                && coordinate.getY() < size.getY();
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }

    static List<Coordinate> getCoordinateAround(Coordinate coordinate) {
        final var list = new ArrayList<Coordinate>();
        for (int x = coordinate.getX() - 1; x <= coordinate.getX() + 1; x++)
            for (int y = coordinate.getY() - 1; y <= coordinate.getY() + 1; y++) {
                final var around = new Coordinate(x, y);
                if (inRange(around) && (!around.equals(coordinate))) {
                    list.add(around);
                }
            }
        return list;
    }

    static int getSquare() {
        return size.getX() * size.getY();
    }

}

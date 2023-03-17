package ru.zavoyko.sweeper.sweeper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Ranges {

    private static Coordinate size;
    private static List<Coordinate> allCoordinates;
    private static final Random random = new Random();

    static Coordinate getSize() {
        return size;
    }

    static void setSize(Coordinate size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<>();
        for (int x = 0; x < size.x(); x++) {
            for (int y = 0; y < size.y(); y++) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }
    }

    static List<Coordinate> getAllCoordinates() {
        return allCoordinates;
    }

    static boolean inRange(Coordinate coordinate) {
        return coordinate != null
                && coordinate.x() >= 0
                && coordinate.x() < size.x()
                && coordinate.y() >= 0
                && coordinate.y() < size.y();
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(random.nextInt(size.x()), random.nextInt(size.y()));
    }

    static List<Coordinate> getCoordinateAround(Coordinate coordinate) {
        final var list = new ArrayList<Coordinate>();
        for (int x = coordinate.x() - 1; x <= coordinate.x() + 1; x++) {
            for (int y = coordinate.y() - 1; y <= coordinate.y() + 1; y++) {
                final var around = new Coordinate(x, y);
                if (inRange(around) && (!around.equals(coordinate))) {
                    list.add(around);
                }
            }
        }
        return list;
    }

    static int getSquare() {
        return size.x() * size.y();
    }

}

package ru.zavoyko.sweeper.sweeper;

public class Matrix {

    private Box [][] matrix;

    Matrix (Box box) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coordinate coordinate : Ranges.getAllCoordinates())
            matrix[coordinate.x][coordinate.y] = box;
    }

    Box get(Coordinate coordinate) {
        if (Ranges.inRange(coordinate))
            return matrix[coordinate.x][coordinate.y];

        return null;
    }

    void set (Coordinate coordinate, Box box) {
        if (Ranges.inRange(coordinate))
            matrix[coordinate.x][coordinate.y] = box;
    }

}

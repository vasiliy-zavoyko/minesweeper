package ru.zavoyko.sweeper.sweeper;

class Matrix {

    private Box[][] matrix;

    Matrix(Box box) {
        matrix = new Box[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for (final var coordinate : Ranges.getAllCoordinates())
            matrix[coordinate.getX()][coordinate.getY()] = box;
    }

    Box get(Coordinate coordinate) {
        if (Ranges.inRange(coordinate))
            return matrix[coordinate.getX()][coordinate.getY()];

        return null;
    }

    void set (Coordinate coordinate, Box box) {
        if (Ranges.inRange(coordinate))
            matrix[coordinate.getX()][coordinate.getY()] = box;
    }

}

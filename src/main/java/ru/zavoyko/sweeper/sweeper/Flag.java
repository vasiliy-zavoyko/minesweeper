package ru.zavoyko.sweeper.sweeper;

class Flag {

    private Matrix flagMap;
    private int totalFlags;
    private int totalClosed;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalClosed = Ranges.getSquare();
        totalFlags = 0;
    }

    Box get(Coordinate coordinate) {
        return flagMap.get(coordinate);
    }

    void setOpendToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.OPENED);
        totalClosed--;
    }

    void toggleFlagedToBox(Coordinate coordinate) {
        if (flagMap.get(coordinate) == Box.CLOSED) {
            setFlagedToBox(coordinate);
        } else {
            setClosedToBox(coordinate);
        }
    }

    private void setClosedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.CLOSED);
        totalFlags--;
    }

    private void setFlagedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.FLAGGED);
        totalFlags++;
    }

    int getTotalFlags() {
        return totalFlags;
    }

    int getTotalClosed() {
        return totalClosed;
    }

    void setFlaggedToLastClosedBoxes() {
        for (final var coordinate : Ranges.getAllCoordinates()) {
            if (Box.CLOSED == flagMap.get(coordinate)) {
                setFlagedToBox(coordinate);
            }
        }
    }

    void setBombedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.BOMBED);
    }

    void setOpendToBoxClosedBox(Coordinate coordinate) {
        if (Box.CLOSED == flagMap.get(coordinate)) {
            flagMap.set(coordinate, Box.OPENED);
        }
    }

    void setNoBombToFlaggedBox(Coordinate coordinate) {
        if (Box.FLAGGED == flagMap.get(coordinate)) {
            flagMap.set(coordinate, Box.NOBOMB);
        }
    }

    int getCountOfFlaggedBoxesAround(Coordinate coordinate) {
        int count = 0;
        for (final var around : Ranges.getCoordinateAround(coordinate)) {
            if (Box.FLAGGED == flagMap.get(around)) {
                count++;
            }
        }
        return count;
    }

}

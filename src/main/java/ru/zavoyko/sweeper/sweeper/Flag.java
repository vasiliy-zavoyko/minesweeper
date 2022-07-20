package ru.zavoyko.sweeper.sweeper;

class Flag {
    private Matrix flagMap;
    private int totalFlags;
    private int totalClosed;

    public void start() {
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
        switch (flagMap.get(coordinate)) {
            case FLAGGED: setClosedToBox(coordinate); break;
            case CLOSED: setFlagedToBox(coordinate); break;
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
        for (Coordinate coordinate : Ranges.getAllCoordinates()) {
            if (Box.CLOSED == flagMap.get(coordinate))
                setFlagedToBox(coordinate);
        }
    }

    void setBombedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.BOMBED);
    }

    void setOpendToBoxClosedBox(Coordinate coordinate) {
        if (Box.CLOSED == flagMap.get(coordinate)) flagMap.set(coordinate, Box.OPENED);
    }

    void setNoBombToFlaggedBox(Coordinate coordinate) {
        if (Box.FLAGGED == flagMap.get(coordinate)) flagMap.set(coordinate, Box.NOBOMB);
    }

    int getCountOfFlaggedBoxesAround(Coordinate coordinate) {
        int count = 0;
        for (Coordinate around : Ranges.getCoordinateAround(coordinate))
            if (Box.FLAGGED == flagMap.get(around)) count++;

        return count;
    }

}

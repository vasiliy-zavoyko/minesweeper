package sweeper;

class Flag {
    private Matrix flagMap;
    int totalFlags;
    int countOfClousedBoxes;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
    }

    Box get(Coordinate coordinate) {
        return flagMap.get(coordinate);
    }

    void setOpendToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.OPENED);
    }

    void toggleFlagedToBox(Coordinate coordinate) {
        switch (flagMap.get(coordinate)) {
            case FLAGGED: setClosedToBox(coordinate); break;
            case CLOSED: setFlagedToBox(coordinate); break;
        }
    }

    private void setClosedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.CLOSED);
    }

    void setFlagedToBox(Coordinate coordinate) {
        flagMap.set(coordinate, Box.FLAGGED);
    }

}

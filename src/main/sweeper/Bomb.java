package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
    }

    Box get(Coordinate coordinate) {
        return bombMap.get(coordinate);
    }

}

package ru.zavoyko.sweeper.sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombsToSetUp) {
        this.totalBombs = totalBombsToSetUp;
        fixBombCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coordinate coordinate) {
        return bombMap.get(coordinate);
    }

    private void placeBomb() {
        while (true) {
            final var coordinate = Ranges.getRandomCoordinate();
            if (Box.BOMB != bombMap.get(coordinate)) {
                incNumbersAroundBomb(coordinate);
                break;
            }
        }
    }

    private void incNumbersAroundBomb(Coordinate coordinate) {
        bombMap.set(coordinate, Box.BOMB);
        for (final var around : Ranges.getCoordinateAround(coordinate)) {
            final var box = bombMap.get(around);
            if (Box.BOMB != box) {
                bombMap.set(around, box.nextNumberBox());
            }
        }
    }

    private void fixBombCount() {
        int maxBombs = Ranges.getSize().x() * Ranges.getSize().y() / 2;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }

}

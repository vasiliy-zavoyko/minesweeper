package ru.zavoyko.sweeper.sweeper;

class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinate(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    void pressLeftButton(Coordinate coordinate) {
        if (isGameOver()) {
            return;
        }
        openBox(coordinate);
        checkWinner();
    }

    void pressRightButton(Coordinate coordinate) {
        if (isGameOver()) {
            return;
        }
        flag.toggleFlagedToBox(coordinate);
    }

    private void openBox(Coordinate coordinate) {
        if (Box.OPENED == flag.get(coordinate)) {
            setOpenedToClosedBoxesAroundNumbers(coordinate);
        } else if (Box.CLOSED == flag.get(coordinate)) {
            switch (bomb.get(coordinate)) {
                case ZERO -> openBoxesAround(coordinate);
                case BOMB -> openBombs(coordinate);
                default -> flag.setOpendToBox(coordinate);
            }
        }
    }

    private void setOpenedToClosedBoxesAroundNumbers(Coordinate coordinate) {
        if (Box.BOMB != bomb.get(coordinate) && (bomb.get(coordinate).getNumber() == flag.getCountOfFlaggedBoxesAround(coordinate))) {
            for (final var around : Ranges.getCoordinateAround(coordinate)) {
                if (Box.CLOSED == flag.get(around)) {
                    openBox(around);
                }
            }
        }
    }

    private void openBombs(Coordinate coordinate) {
        flag.setBombedToBox(coordinate);
        for (final var other : Ranges.getAllCoordinates()) {
            if (bomb.get(other) == Box.BOMB) {
                flag.setOpendToBoxClosedBox(other);
            } else {
                flag.setNoBombToFlaggedBox(other);
            }
        }
        state = GameState.BOMBED;
    }

    private void openBoxesAround(Coordinate coordinate) {
        flag.setOpendToBox(coordinate);
        for (final var around : Ranges.getCoordinateAround(coordinate)) {
            openBox(around);
        }
    }

    GameState getState() {
        return state;
    }

    private void checkWinner() {
        if (GameState.PLAYED == state && (flag.getTotalClosed() == bomb.getTotalBombs())) {
            state = GameState.WINNER;
            flag.setFlaggedToLastClosedBoxes();
        }
    }

    Box getBox(Coordinate coordinate) {
        if (Box.OPENED == flag.get(coordinate)) {
            return bomb.get(coordinate);
        }
        return flag.get(coordinate);
    }

    int getTotalBombs() {
        return bomb.getTotalBombs();
    }

    int getTotalFlagged() {
        return flag.getTotalFlags();
    }

    private boolean isGameOver() {
        if (GameState.PLAYED != state) {
            start();
            return true;
        }
        return false;
    }

}

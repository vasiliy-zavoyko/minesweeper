package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinate(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public void pressLeftButton(Coordinate coordinate) {
        if (isGameOver()) return;
        openBox(coordinate);
        checkWinner();
    }

    public void pressRightButton(Coordinate coordinate) {
        if (isGameOver()) return;
        flag.toggleFlagedToBox(coordinate);
    }

    private void openBox(Coordinate coordinate) {
        switch (flag.get(coordinate)) {
            case OPENED: setOpenedToClosedBoxesArroundNumbers(coordinate); break;
            case FLAGGED: break;
            case CLOSED:
                switch (bomb.get(coordinate)) {
                    case ZERO: openBoxesAround(coordinate); break;
                    case BOMB: openBombs(coordinate); break;
                    default: flag.setOpendToBox(coordinate); break;
                }
        }
    }

    private void setOpenedToClosedBoxesArroundNumbers(Coordinate coordinate) {
        if (Box.BOMB != bomb.get(coordinate))
            if (bomb.get(coordinate).getNumber() == flag.getCountOfFlaggedBoxesAround(coordinate)) {
                for (Coordinate around : Ranges.getCoordinateAround(coordinate))
                    if (Box.CLOSED == flag.get(around)) openBox(around);
            }
    }

    private void openBombs(Coordinate coordinate) {
        flag.setBombedToBox(coordinate);
        for (Coordinate other : Ranges.getAllCoordinates())
            if (bomb.get(other) == Box.BOMB) {
                flag.setOpendToBoxClosedBox(other);
            } else {
                flag.setNoBombToFlaggedBox(other);
            }
        state = GameState.BOMBED;
    }

    private void openBoxesAround(Coordinate coordinate) {
        flag.setOpendToBox(coordinate);
        for (Coordinate around : Ranges.getCoordinateAround(coordinate))
            openBox(around);
    }

    public GameState getState() {
        return state;
    }

    private void checkWinner() {
        if (GameState.PLAYED == state)
            if (flag.getTotalClosed() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
                flag.setFlaggedToLastClosedBoxes();
            }
    }

    public Box getBox (Coordinate coordinate) {
        if (Box.OPENED == flag.get(coordinate))
            return bomb.get(coordinate);

        return flag.get(coordinate);
    }

    public int getTotalBombs() {
        return bomb.getTotalBombs();
    }

    public int getTotalFlagged() {
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

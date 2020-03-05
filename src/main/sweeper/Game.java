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
        flag.setOpendToBox(coordinate);
    }

    public void pressRightButton(Coordinate coordinate) {
        flag.toggleFlagedToBox(coordinate);
    }

    public GameState getState() {
        return state;
    }

    public Box getBox (Coordinate coordinate) {
        if (Box.OPENED == flag.get(coordinate))
            return bomb.get(coordinate);

        return flag.get(coordinate);
    }

}

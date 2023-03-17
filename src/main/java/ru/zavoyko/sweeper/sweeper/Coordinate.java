package ru.zavoyko.sweeper.sweeper;

import lombok.Data;

@Data
class Coordinate {

    private int x;
    private int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

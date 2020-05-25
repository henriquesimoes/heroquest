package br.unicamp.ic.mc322.heroquest.map.core.geom;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;

            return coordinate.getX() == this.getX() && coordinate.getY() == this.getY();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

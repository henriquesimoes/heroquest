package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.*;
import java.util.Objects;

public class Coordinate {
    private Point coordinate;

    public Coordinate() {
        this.coordinate = null;
    }

    public Coordinate(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public static Coordinate getOrigin() {
        return new Coordinate(0, 0);
    }

    public static Coordinate shift(Coordinate reference, int dx, int dy) {
        return new Coordinate(reference.getX() + dx, reference.getY() + dy);
    }

    public int getX() {
        return coordinate.x;
    }

    public int getY() {
        return coordinate.y;
    }

    public Coordinate shift(Direction direction) {
        switch (direction) {
            case NORTH:
                return new Coordinate(getX(), getY() - 1);
            case SOUTH:
                return new Coordinate(getX(), getY() + 1);
            case EAST:
                return new Coordinate(getX() + 1, getY());
            case WEST:
                return new Coordinate(getX() - 1, getY());
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    private Coordinate[] getNeighborCoordinates(int[] dx, int[] dy) {
        Coordinate[] neighbors = new Coordinate[dx.length];

        for (int i = 0; i < dx.length; i++)
            neighbors[i] = new Coordinate(this.getX() + dx[i], this.getY() + dy[i]);

        return neighbors;
    }

    public Coordinate[] getCardinalNeighborCoordinates() {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        return getNeighborCoordinates(dx, dy);
    }

    public Coordinate[] getAdjacentNeighborCoordinates() {
        int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
        int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};

        return getNeighborCoordinates(dx, dy);
    }

    public void copyValue(Coordinate coordinate) {
        this.coordinate = new Point(coordinate.getX(), coordinate.getY());
    }

    public Coordinate toRelative() {
        Coordinate origin = getOrigin();

        return Coordinate.shift(this, -origin.getX(), -origin.getY());
    }

    public boolean isInside(Dimension dimension) {
        Coordinate origin = getOrigin();

        return origin.getX() <= this.getX() && this.getX() < dimension.getWidth()
                && origin.getY() <= this.getY() && this.getY() < dimension.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o || (o == null && coordinate == null)) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return coordinate != null && coordinate.equals(that.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return "Coordinate (" + getX() + ',' + getY() + ")";
    }
}

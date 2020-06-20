package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.*;
import java.util.Objects;

public class Coordinate {
    private Point coordinate;

    public Coordinate(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public int getX() {
        return (int) coordinate.x;
    }

    public int getY() {
        return (int) coordinate.y;
    }

    public Coordinate[] getNeighborCoordinates() {
        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};

        Coordinate[] neighbors = new Coordinate[dx.length];

        for (int i = 0; i < dx.length; i++)
            neighbors[i] = new Coordinate(this.getX() + dx[i], this.getY() + dy[i]);

        return neighbors;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;

            return coordinate.getX() == this.getX() && coordinate.getY() == this.getY();
        }

        return false;
    }

    public void copyValue(Coordinate coordinate){
        this.coordinate.setLocation(coordinate.getX(), coordinate.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return "Coordinate (" + getX() + ',' + getY() + ")";
    }

    public boolean inside(Dimension dimension) {
        Coordinate origin = getOrigin();

        return origin.getX() <= this.getX() && this.getX() < dimension.getWidth()
                && origin.getY() <= this.getY() && this.getY() < dimension.getHeight();
    }

    public static Coordinate getOrigin() {
        return new Coordinate(0, 0);
    }

    public static Coordinate shift(Coordinate reference, int dx, int dy) {
        return new Coordinate(reference.getX() + dx, reference.getY() + dy);
    }
}

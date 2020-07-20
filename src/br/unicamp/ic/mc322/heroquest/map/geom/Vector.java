package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.geom.Point2D;

public class Vector implements Comparable {
    private final double EPS = 1e-6;
    private Double angle;

    Vector(Point2D reference, Point2D object) {
        double x1 = reference.getX();
        double y1 = reference.getY();
        double x2 = object.getX();
        double y2 = object.getY();

        if (reference.equals(object))
            throw new IllegalArgumentException("A point does not define a line");

        if (Math.abs(x1 - x2) < EPS) {
            angle = (y2 >= y1 ? Math.PI : 3 * Math.PI) / 2;
        } else {
            double m = (y1 - y2) / (x1 - x2);
            angle = m >= 0 ? Math.atan(m) : (Math.atan(m) + Math.PI);
            angle += y2 < y1 || (y2 == y1 && x2 < x1) ? Math.PI : 0;
        }
    }

    Vector(Coordinate coordinateReference, Coordinate coordinateObject) {
        this(new Point2D.Double(coordinateReference.getX(), coordinateReference.getY()),
                new Point2D.Double(coordinateObject.getX(), coordinateObject.getY()));
    }

    @Override
    public int compareTo(Object other) {
        if (getClass() != other.getClass())
            throw new ClassCastException();

        Vector vector = (Vector) other;
        return this.angle.compareTo(vector.angle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        return Math.abs(this.angle - vector.angle) % (2 * Math.PI) < EPS;
    }
}

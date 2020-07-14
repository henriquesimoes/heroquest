package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Vector implements Comparable{
    private Double angle, b, m;
    private final double EPS = 1e-6;
    private Point2D reference, object;

    Vector(Point2D reference, Point2D object){
        double x1 = reference.getX();
        double y1 = reference.getY();
        double x2 = object.getX();
        double y2 = object.getY();
        this.reference = reference;
        this.object = object;

        if(reference.equals(object))
            throw new IllegalArgumentException("A point does not define a line");

        if(Math.abs(x1 - x2) < EPS){
            angle = Math.PI/2;
        }
        else{
            m = (y1 - y2) / (x1 - x2);
            angle = Math.atan(m);
            if (angle < 0)
                angle = Math.PI + angle;
            b = y1 - m * x1;
        }
    }

    Vector(Coordinate coordinateReference, Coordinate coordinateObject){
        this (  new Point2D.Double(coordinateReference.getX(), coordinateReference.getY()),
                new Point2D.Double(coordinateObject.getX(), coordinateObject.getY()));
    }

    @Override
    public int compareTo(Object other) {
        if (getClass() != other.getClass())
            throw new ClassCastException();

        Vector vector = (Vector)other;
        return this.angle.compareTo(vector.angle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        return Math.abs(this.angle - vector.angle) % Math.PI < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(angle);
    }

    boolean extendsTo(Coordinate current) {
        Point2D point = new Point2D.Double(current.getX(), current.getY());
        Double referenceToPoint = reference.distance(point);
        Double objectToPoint = object.distance(point);
        return referenceToPoint >= objectToPoint;
    }
}

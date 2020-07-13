package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Vector implements Comparable{
    private Double m, b;
    private final double EPS = 1e-6;
    private final double RELATIVE_ERROR = 1e-3;
    private boolean vertical;
    private Point2D reference, object;

    Vector(Point2D reference, Point2D object){
        double x1 = reference.getX();
        double y1 = reference.getY();
        double x2 = object.getX();
        double y2 = object.getY();
        this.reference = reference;
        this.object = object;

        if(reference.equals(object))
            throw new IllegalArgumentException("A point not define a line");

        if(Math.abs(x1 - x2) < EPS)
            vertical = true;
        else{
            vertical = false;
            m = (y1 - y2) / (x1 - x2);
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
        if (this.vertical == vector.vertical){
            if (this.vertical)
                return 0;
            return this.m.compareTo(vector.m);
        }
        return this.vertical ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        if (this.vertical == vector.vertical){
            if (this.vertical)
                return true;
            return Math.abs(this.m - vector.m) < EPS;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m, vertical);
    }

    void bitLeftShift() {
        m -= RELATIVE_ERROR * Math.abs(m);
    }

    void bitRightShift() {
        m += RELATIVE_ERROR * Math.abs(m);
    }

    boolean extendsVector(Coordinate current) {
        Point2D point = new Point2D.Double(current.getX(), current.getY());
        Double referenceToPoint = reference.distance(point);
        Double objectToPoint = object.distance(point);
        return referenceToPoint >= objectToPoint;
    }

    public boolean isProximate(Vector vector2) {
        // TODO: FIX IT
        return true;
    }
}

package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.geom.Point2D;

public class Vector {
    private double m, b;
    private final double EPS = 1e-6;
    private boolean vertical;
    private Point2D reference, object;

    Vector(Coordinate coordinateReference, Coordinate coordinateObject){
        double x1 = coordinateReference.getX();
        double y1 = coordinateReference.getY();
        double x2 = coordinateObject.getX();
        double y2 = coordinateObject.getY();
        this.reference = new Point2D.Double(x1, y1);
        this.object = new Point2D.Double(x2, y2);

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
}

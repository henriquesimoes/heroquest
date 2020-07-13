package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VectorRange {
    private Vector lowerBound, upperBound;
    private final double RADIUS = 0.5;
    private final double EPS = 1e-6;

    /**
     * This Class puts a circle in coordinateObject,
     * and find the vectors lowerBound and upperBound, which are the vectors tangent to circle.
     * @param coordinateReference
     * @param coordinateObject
     */
    public VectorRange(Coordinate coordinateReference, Coordinate coordinateObject){
        Point2D reference = new Point2D.Double(coordinateReference.getX(), coordinateReference.getY());
        Point2D object = new Point2D.Double(coordinateObject.getX(), coordinateObject.getY());

        if(reference.equals(object))
            throw new IllegalArgumentException("A point not define a line");

        ArrayList<Point2D> pointsTangent = findTangentPoints(reference, object);
        lowerBound = new Vector(reference, pointsTangent.get(0));
        upperBound = new Vector(reference, pointsTangent.get(1));
    }

    private ArrayList<Point2D> findTangentPoints(Point2D linePoint, Point2D center) {
        center = new Point2D.Double(center.getX() - linePoint.getX(), center.getY() - linePoint.getY());

        ArrayList<Point2D> result = new ArrayList<>();
        result.add(findIntersectionPoint(center, linePoint, true));
        result.add(findIntersectionPoint(center, linePoint, false));

        return result;
    }

    private Point2D findIntersectionPoint(Point2D center, Point2D linePoint, boolean positive){
        double x = center.getX();
        double y = center.getY();
        double delta = calculateDelta(x, y);
        double cosine = positive ? (-RADIUS * x + delta) / (x * x + y * y) : (-RADIUS * x - delta) / (x * x + y * y);

        if (cosine < - (1 + EPS) || cosine > 1 + EPS)
            throw new IllegalArgumentException("Not found intersection point");

        double angle = positive ? -Math.acos(cosine) : Math.acos(cosine);

        return makePoint(angle, center, linePoint);
    }

    private Point2D makePoint(double angle, Point2D center, Point2D linePoint){
        double x = center.getX() + RADIUS * Math.cos(angle) + linePoint.getX();
        double y = center.getY() + RADIUS * Math.sin(angle) + linePoint.getY();
        return new Point2D.Double(x, y);
    }

    private double calculateDelta(double x, double y){
        return y * Math.sqrt(x * x + y * y - RADIUS * RADIUS);
    }
}

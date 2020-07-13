package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

public class VectorRange {
    private Vector lowerBound, upperBound, current;
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

        HashSet<Point2D> tangentPoints = findTangentPoints(reference, object);
        if (tangentPoints.size() != 2)
            throw new IllegalArgumentException("No is possible have more or less than two tangent points");

        Vector[] vectors = new Vector[2];
        int i = 0;
        for (Point2D point : tangentPoints)
            vectors[i++] = new Vector(reference, point);
        current = new Vector(reference, object);

        if (vectors[0].compareTo(vectors[1]) > 0){
            Vector temp = vectors[0];
            vectors[0] = vectors[1];
            vectors[1] = temp;
        }
        lowerBound = vectors[0].isProximate(vectors[1]) ? vectors[0] : vectors[1];
        upperBound = vectors[0].isProximate(vectors[1]) ? vectors[1] : vectors[0];

        lowerBound.bitLeftShift();
        upperBound.bitRightShift();
    }

    private HashSet<Point2D> findTangentPoints(Point2D linePoint, Point2D center) {
        Point2D centerOffset = new Point2D.Double(center.getX() - linePoint.getX(), center.getY() - linePoint.getY());

        HashSet<Point2D> result = new HashSet<>();
        for (int i = 0; i < 4; i++){
            Point2D point = findIntersectionPoint(centerOffset, linePoint, i % 2 == 0, (i / 2 ) % 2 == 0);
            if (isPerpendicular(center, point, linePoint))
                result.add(point);
        }

        return result;
    }

    private Point2D findIntersectionPoint(Point2D center, Point2D linePoint, boolean signal1, boolean signal2){
        double x = center.getX();
        double y = center.getY();
        double delta = calculateDelta(x, y);
        double cosine = signal1 ? (-RADIUS * x + delta) / (x * x + y * y) : (-RADIUS * x - delta) / (x * x + y * y);

        if (cosine < - (1 + EPS) || cosine > 1 + EPS)
            throw new IllegalArgumentException("Not found intersection point");

        double angle = signal2 ? -Math.acos(cosine) : Math.acos(cosine);

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

    private boolean isPerpendicular(Point2D p1, Point2D p2, Point2D p3){
        Point2D v1 = new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        Point2D v2 = new Point2D.Double(p3.getX() - p2.getX(), p3.getY() - p2.getY());
        Double d = Math.abs(v1.getX() * v2.getX() + v1.getY() * v2.getY());
        return  d < EPS;
    }

    Vector getLowerBound() {
        return  lowerBound;
    }

    Vector getUpperBound() {
        return upperBound;
    }

    public Vector getCurrent() {
        return current;
    }
}

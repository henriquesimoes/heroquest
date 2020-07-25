package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.*;

public class Line {
    private int a, b, c;

    private Line(Coordinate point1, Coordinate point2) {
        int x1 = point1.getX();
        int y1 = point1.getY();
        int x2 = point2.getX();
        int y2 = point2.getY();

        a = y1 - y2;
        b = x2 - x1;
        c = x1 * y2 - x2 * y1;
    }

    static boolean isAlmostCollinear(Coordinate point1, Coordinate point2, Coordinate point3) {
        int x1 = point1.getX();
        int y1 = point1.getY();
        int x2 = point2.getX();
        int y2 = point2.getY();
        int x3 = point3.getX();
        int y3 = point3.getY();

        double distance12 = Point.distance(x1, y1, x2, y2);
        double distance13 = Point.distance(x1, y1, x3, y3);
        double distance23 = Point.distance(x2, y2, x3, y3);

        if (distance23 >= distance13 || distance12 >= distance13)
            return false;

        Line line = new Line(point1, point3);

        return line.getDistanceToPoint(point2) < 0.3;
    }

    private double getDistanceToPoint(Coordinate point) {
        return Math.abs(a * point.getX() + b * point.getY() + c) / Math.sqrt(a * a + b * b);
    }
}

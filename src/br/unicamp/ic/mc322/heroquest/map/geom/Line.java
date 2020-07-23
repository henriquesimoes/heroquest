package br.unicamp.ic.mc322.heroquest.map.geom;

import java.awt.*;

public class Line {
    static final int attenuator = 6;

    static boolean isCollinear(Coordinate point1, Coordinate point2, Coordinate point3) {
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

        Point vector1 = new Point(x1 - x2, y1 - y2);
        Point vector2 = new Point(x3 - x2, y3 - y2);

        double angle = Math.acos(dot(vector1, vector2) / (norm(vector1) * norm(vector2)));

        return angle >= 0 && (Math.PI - angle) < Math.PI / (distance12 * attenuator);
    }

    private static double dot(Point vector1, Point vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    private static double norm(Point vector) {
        return Math.sqrt(dot(vector, vector));
    }
}

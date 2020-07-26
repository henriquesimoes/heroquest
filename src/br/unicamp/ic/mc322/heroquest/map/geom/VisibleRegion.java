package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Region that is seen by something on the reference coordinate.
 */
public class VisibleRegion extends LimitedRegion {
    private static final int MAXIMUM_VISIBILITY_RADIUS = 10;
    private Collection<Coordinate> obstacles;

    VisibleRegion(Coordinate reference) {
        super(reference, MAXIMUM_VISIBILITY_RADIUS);

        obstacles = new ArrayList<>();
    }

    public static int getMaximumVisibilityRadius() {
        return MAXIMUM_VISIBILITY_RADIUS;
    }

    @Override
    protected void update(Pair<Coordinate, Integer> lastVisitedPosition) {
        Coordinate lastCoordinate = lastVisitedPosition.getFirst();
        int distance = lastVisitedPosition.getSecond();

        if (isExpandable(lastCoordinate) || lastCoordinate.equals(reference)) {
            for (Coordinate neighbor : lastCoordinate.getCardinalNeighborCoordinates()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    if (isValid(neighbor) && isVisible(neighbor)) {
                        queue.add(new Pair<>(neighbor, distance + 1));
                        if (!isExpandable(neighbor))
                            obstacles.add(neighbor);
                    } else
                        obstacles.add(neighbor);
                }
            }
        }
    }

    private boolean isVisible(Coordinate current) {
        for (Coordinate obstacle : obstacles)
            if (Line.isAlmostCollinear(reference, obstacle, current))
                return false;

        return true;
    }
}

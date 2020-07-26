package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Region that is seen by something on the reference coordinate.
 */
class VisibleRegion extends LimitedRegion {
    private Collection<Coordinate> obstacles;

    VisibleRegion(Coordinate reference, int mapWidth, int mapHeight) {
        super(reference, mapWidth + mapHeight + 1);
        obstacles = new ArrayList<>();
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

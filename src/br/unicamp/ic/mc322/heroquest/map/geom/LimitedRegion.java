package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Region that includes all coordinates within a (Manhattan) distance
 * range. In other words, the distance unit corresponds to a single
 * walk move in the map.
 */
class LimitedRegion extends Region {
    private int limit;
    private Queue<Pair<Coordinate, Integer>> queue;
    private Set<Coordinate> visited;

    LimitedRegion(Coordinate reference, int limit) {
        super(reference);
        this.limit = limit;
        queue = new LinkedList<>();
        visited = new HashSet<>();
    }

    @Override
    protected void build() {
        visited.add(reference);
        queue.add(new Pair<>(reference, 0));

        while (!queue.isEmpty() && queue.peek().getSecond() <= limit) {
            Pair<Coordinate, Integer> current = queue.poll();

            update(current);

            coordinates.add(current.getFirst());
        }
    }

    private void update(Pair<Coordinate, Integer> lastVisitedPosition) {
        Coordinate lastCoordinate = lastVisitedPosition.getFirst();
        int distance = lastVisitedPosition.getSecond();

        if (isExpandable(lastCoordinate) || lastCoordinate.equals(reference)) {
            for (Coordinate neighbor : lastCoordinate.getCardinalNeighborCoordinates()) {
                if (!visited.contains(neighbor) && isValid(neighbor)) {
                    queue.add(new Pair<>(neighbor, distance + 1));
                    visited.add(neighbor);
                }
            }
        }
    }
}

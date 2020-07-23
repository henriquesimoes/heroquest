package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.*;

class VisibleRegion extends Region {
    private final int MAXIMUM_VISIBILITY_RADIUS = Integer.MAX_VALUE;
    private Queue<Pair<Coordinate, Integer>> queue;
    private Set<Coordinate> visited;
    private Collection<Coordinate> obstacles;

    VisibleRegion(Coordinate reference) {
        super(reference);
        queue = new LinkedList<>();
        visited = new HashSet<>();
        obstacles = new ArrayList<>();
    }

    @Override
    protected void build() {
        visited.add(reference);
        queue.add(new Pair<>(reference, 0));

        while (!queue.isEmpty() && queue.peek().getSecond() <= MAXIMUM_VISIBILITY_RADIUS) {
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
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    if (isValid(neighbor) && isVisible(neighbor)) {
                        queue.add(new Pair<>(neighbor, distance + 1));
                        if (!isExpandable(neighbor))
                            obstacles.add(neighbor);
                    }
                }
            }
        }
    }

    private boolean isVisible(Coordinate current) {
        for (Coordinate obstacle : obstacles)
            if (Line.isCollinear(reference, obstacle, current))
                return false;

        return true;
    }
}

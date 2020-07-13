package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.*;

class VisibleRegion extends Region {
    private final int MAXIMUM_VISIBILITY_RADIUS = Integer.MAX_VALUE;
    private Queue<Pair<Coordinate, Integer>> queue;
    private Set<Coordinate> visited;
    private Set<Coordinate> obstacles;
    private SortedSet<Vector> vectorsOfObstacle;

    VisibleRegion(Coordinate reference) {
        super(reference);
        queue = new LinkedList<>();
        visited = new HashSet<>();
        obstacles = new HashSet<>();
        vectorsOfObstacle = new TreeSet<>();
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

        if (!obstacles.contains(lastCoordinate)){
            int distance = lastVisitedPosition.getSecond();

            for (Coordinate neighbor : lastCoordinate.getNeighborCoordinates()) {
                if (!visited.contains(neighbor)){
                    visited.add(neighbor);

                    if (isVisible(neighbor)){
                        queue.add(new Pair<>(neighbor, distance + 1));
                        visited.add(neighbor);

                        if (!isValid(neighbor)){
                            obstacles.add(neighbor);
                            vectorsOfObstacle.add(new Vector(reference, neighbor));
                        }
                    }
                }
            }
        }
    }

    private boolean isVisible(Coordinate current) {
        VectorRange vectorRange = new VectorRange(reference, current);
        Vector lowerBound = vectorRange.getLowerBound();
        Vector upperBound = vectorRange.getUpperBound();
        Vector currentVector = vectorRange.getCurrent();

        // prevent problems caused by vertical vector
        if (lowerBound.equals(currentVector) || upperBound.equals(currentVector))
            return false;

        if (lowerBound.compareTo(upperBound) < 0){
               for (Vector vector : vectorsOfObstacle.subSet(lowerBound, upperBound))
                    if (vector.extendsVector(current))
                        return false;
        }else{
            for (Vector vector : vectorsOfObstacle.tailSet(lowerBound))
                if (vector.extendsVector(current))
                    return false;
            for (Vector vector : vectorsOfObstacle.headSet(upperBound))
                if (vector.extendsVector(current))
                    return false;
        }
        return true;
    }
}

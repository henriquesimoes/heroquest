package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.*;

public class LimitedDistance extends Distance {
    private int limit;

    public LimitedDistance(Coordinate reference, int limit) {
        super(reference);
        this.limit = limit;
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return new Iterator<>() {
            private Queue<Pair<Coordinate, Integer>> queue = new LinkedList<>();
            private Set<Coordinate> visited = new HashSet<>();

            {
                // TODO: Check whether a nested class approach is more desired.
                queue.add(new Pair<>(reference, 0));
                visited.add(reference);
            }

            @Override
            public boolean hasNext() {
                Pair<Coordinate, Integer> pair = queue.peek();

                if (pair == null)
                    return false;

                return pair.getValue() <= limit;
            }

            @Override
            public Coordinate next() {
                Pair<Coordinate, Integer> current = queue.poll();

                for (Coordinate neighbor : current.getKey().getNeighborCoordinates())
                    if (!visited.contains(neighbor)) {
                        queue.add(new Pair<>(neighbor, current.getValue() + 1));
                        visited.add(neighbor);
                    }

                return current.getKey();
            }
        };
    }
}

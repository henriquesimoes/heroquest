package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.*;

public class LimitedRegion extends Region {
    private int limit;

    LimitedRegion(Coordinate reference, int limit) {
        super(reference);
        this.limit = limit;
    }

    LimitedRegion(Coordinate reference, WalkValidator walkValidator, int limit) {
        super(reference, walkValidator);
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
                return !queue.isEmpty() && queue.peek().getValue() <= limit;
            }

            @Override
            public Coordinate next() {
                Pair<Coordinate, Integer> current = queue.poll();

                for (Coordinate neighbor : current.getKey().getNeighborCoordinates())
                    if (!visited.contains(neighbor)
                            && (walkValidator == null || walkValidator.isAllowedToWalkOver(neighbor))) {
                        queue.add(new Pair<>(neighbor, current.getValue() + 1));
                        visited.add(neighbor);
                    }

                return current.getKey();
            }
        };
    }
}

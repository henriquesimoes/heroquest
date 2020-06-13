package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.ArrayList;
import java.util.Iterator;

class AdjacentDistance extends Distance {
    protected ArrayList<Pair<Integer, Integer>> delta = new ArrayList<>();

    AdjacentDistance(Coordinate reference) {
        super(reference);
    }

    AdjacentDistance(Coordinate reference, WalkValidator walkValidator) {
        super(reference, walkValidator);
    }

    protected void build() {
        final int[] deltaX = {0,  0, 1, 1,  1, -1, -1, -1};
        final int[] deltaY = {1, -1, 1, 0, -1,  1,  0, -1};

        for (int i = 0; i < deltaX.length; i++)
            delta.add(new Pair<>(deltaX[i], deltaY[i]));
    }

    @Override
    public Iterator<Coordinate> iterator() {
        if (delta.isEmpty())
            build();

        return new Iterator<>() {
            private int current = 0;

            private Coordinate get(int index) {
                return Coordinate.shift(reference, delta.get(index).getKey(), delta.get(index).getValue());
            }

            @Override
            public boolean hasNext() {
                if (walkValidator != null)
                    while (current < delta.size() && !walkValidator.isAllowedToWalkOver(get(current)))
                        current++;

                return current < delta.size();
            }

            @Override
            public Coordinate next() {
                return get(current++);
            }
        };
    }
}


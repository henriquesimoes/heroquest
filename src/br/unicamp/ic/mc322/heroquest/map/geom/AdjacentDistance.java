package br.unicamp.ic.mc322.heroquest.map.geom;

import java.util.Iterator;

class AdjacentDistance extends Distance {

    AdjacentDistance(Coordinate reference) {
        super(reference);
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return new Iterator<>() {
            private int[] deltaX = {0,  0, 1, 1,  1, -1, -1, -1};
            private int[] deltaY = {1, -1, 1, 0, -1,  1,  0, -1};
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < deltaX.length;
            }

            @Override
            public Coordinate next() {
                return Coordinate.shift(reference, deltaX[current], deltaY[current++]);
            }
        };
    }
}


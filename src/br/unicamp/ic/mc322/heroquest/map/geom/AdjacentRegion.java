package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

import java.util.ArrayList;
import java.util.Collection;

class AdjacentRegion extends Region {
    protected Collection<Pair<Integer, Integer>> deltas;

    AdjacentRegion(Coordinate reference) {
        super(reference);

        deltas = new ArrayList<>();
    }

    protected void buildDeltas() {
        final int[] deltaX = {0,  0, 1, 1,  1, -1, -1, -1};
        final int[] deltaY = {1, -1, 1, 0, -1,  1,  0, -1};

        for (int i = 0; i < deltaX.length; i++)
            deltas.add(new Pair<>(deltaX[i], deltaY[i]));
    }

    @Override
    protected void build() {
        buildDeltas();

        for (Pair<Integer, Integer> delta : deltas) {
            Coordinate coordinate = Coordinate.shift(reference, delta.getKey(), delta.getValue());

            if (isValid(coordinate))
                coordinates.add(coordinate);
        }
    }
}


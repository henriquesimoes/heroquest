package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

class CardinalRegion extends AdjacentRegion {

    CardinalRegion(Coordinate reference) {
        super(reference);
    }

    @Override
    protected void buildDeltas() {
        final int[] deltaX = {0, 0, 1, -1};
        final int[] deltaY = {1, -1, 0, 0};

        for (int i = 0; i < deltaX.length; i++)
            deltas.add(new Pair<>(deltaX[i], deltaY[i]));
    }
}

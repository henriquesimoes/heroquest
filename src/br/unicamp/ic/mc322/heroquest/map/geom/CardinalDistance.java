package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

class CardinalDistance extends AdjacentDistance {

    CardinalDistance(Coordinate reference) {
        super(reference);
    }

    public CardinalDistance(Coordinate reference, WalkValidator walkValidator) {
        super(reference, walkValidator);
    }

    @Override
    protected void build() {
        final int[] deltaX = {0, 0, 1, -1};
        final int[] deltaY = {1, -1, 0, 0};

        for (int i = 0; i < deltaX.length; i++)
            delta.add(new Pair<>(deltaX[i], deltaY[i]));
    }
}

package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;

public class Ruler {
    private Coordinate reference;
    private MapStructure structure;

    public Ruler(MapStructure structure) {
        this.structure = structure;
        this.fixAt(Coordinate.getOrigin());
    }

    public void fixAt(Coordinate coordinate) {
        this.reference = coordinate;
    }

    public Distance getAdjacentDistance() {
        return new AdjacentDistance(reference);
    }

    public Distance getAdjacentDistance(Coordinate reference) {
        return new AdjacentDistance(reference);
    }

    public Distance getCardinalDistance() {
        return new CardinalDistance(reference);
    }

    public Distance getCardinalDistance(Coordinate reference) {
        return new CardinalDistance(reference);
    }

    public Distance getLimitedDistance(int limit) {
        return new LimitedDistance(reference, limit);
    }

    public Distance getLimitedDistance(Coordinate reference, int limit) {
        return new LimitedDistance(reference, limit);
    }
}

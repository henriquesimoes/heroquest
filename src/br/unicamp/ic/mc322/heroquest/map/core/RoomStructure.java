package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

public class RoomStructure {
    private final Dimension dimension;
    private final Coordinate topLeftCoordinate;

    public RoomStructure(Dimension dimension, Coordinate topLeftCoordinate) {
        this.dimension = dimension;
        this.topLeftCoordinate = topLeftCoordinate;
    }

    public Coordinate getTopLeftCoordinate() {
        return topLeftCoordinate;
    }

    public Dimension getDimension() {
        return dimension;
    }
}

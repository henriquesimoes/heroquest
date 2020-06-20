package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

public class GridContainer {
    Pair<Integer, Integer> dimensions;
    Coordinate topLeftCornerCoordinate;

    public GridContainer(int dimensionX, int dimensionY, int coordinateX, int coordinateY) {
        this.dimensions = new Pair<>(dimensionX, dimensionY);
        this.topLeftCornerCoordinate = new Coordinate(coordinateX, coordinateY);
    }

    public int getDimensionX() {
        return dimensions.getKey();
    }

    public int getDimensionY() {
        return dimensions.getValue();
    }

    public Coordinate getTopLeftCornerCoordinate() {
        return topLeftCornerCoordinate;
    }

    @Override
    public String toString() {
        return ("Dimensions: " + dimensions.getKey() + " X " + dimensions.getValue() + "  -  Coords:" +
                topLeftCornerCoordinate.toString());
    }
}

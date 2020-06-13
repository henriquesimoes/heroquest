package br.unicamp.ic.mc322.heroquest.map.generator.gridgenerator;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;

public class GridContainer {
    Dimension dimensions;
    Coordinate topLeftCornerCoordinate;

    public GridContainer(int dimensionX, int dimensionY, int coordinateX, int coordinateY) {
        this.dimensions = new Dimension(dimensionX, dimensionY);
        this.topLeftCornerCoordinate = new Coordinate(coordinateX, coordinateY);
    }

    public int getDimensionX() {
        return dimensions.getWidth();
    }

    public int getDimensionY() {
        return dimensions.getHeight();
    }

    public Coordinate getTopLeftCornerCoordinate() {
        return topLeftCornerCoordinate;
    }

    @Override
    public String toString() {
        return ("Dimensions: " + dimensions.getWidth() + " X " + dimensions.getHeight() + "  -  Coords:" +
                topLeftCornerCoordinate.toString());
    }
}

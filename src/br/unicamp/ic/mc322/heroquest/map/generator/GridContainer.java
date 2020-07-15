package br.unicamp.ic.mc322.heroquest.map.generator;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

class GridContainer {
    private Dimension dimension;
    private Coordinate topLeftCoordinate;

    public GridContainer(int dimensionX, int dimensionY, int coordinateX, int coordinateY) {
        this.dimension = new Dimension(dimensionX, dimensionY);
        this.topLeftCoordinate = new Coordinate(coordinateX, coordinateY);
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public Coordinate getTopLeftCoordinate() {
        return topLeftCoordinate;
    }

    @Override
    public String toString() {
        return ("Dimensions: " + dimension.getWidth() + " X " + dimension.getHeight() + "  -  Coords:" +
                topLeftCoordinate.toString());
    }
}
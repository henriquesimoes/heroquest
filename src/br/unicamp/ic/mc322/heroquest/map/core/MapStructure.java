package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;

import java.util.*;
import java.util.Map;

public class MapStructure {
    private Map<Coordinate, MapObject> objects;
    private Dimension dimension;

    public MapStructure() {
        objects = new HashMap<>();
    }

    public MapStructure(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void add(MapObject object) {
        objects.put(object.getPosition(), object);
    }

    public MapObject get(Coordinate coordinate) {
        MapObject object =  objects.get(coordinate);
        return object;
    }

    /**
     * TODO: Add structure validation, in order to ensure that all valid position have a structural object
     */
}

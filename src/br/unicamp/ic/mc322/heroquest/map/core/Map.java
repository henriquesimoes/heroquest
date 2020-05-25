package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.object.Walker;

import java.util.HashMap;

public class Map {
    private MapStructure structure;
    private java.util.Map<Coordinate, FixedObject> objects;
    private java.util.Map<Coordinate, Walker> begins;

    public Map(MapStructure structure) {
        this.structure = structure;

        objects = new HashMap<>();
        begins = new HashMap<>();
    }

    public MapObject get(Coordinate coordinate) {   
        if (begins.containsKey(coordinate))
            return begins.get(coordinate);

        if (objects.containsKey(coordinate))
            return objects.get(coordinate);

        return structure.get(coordinate);
    }

    public Dimension getDimension() {
        return structure.getDimension();
    }
}

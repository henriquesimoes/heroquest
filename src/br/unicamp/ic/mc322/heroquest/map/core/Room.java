package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.HashMap;

public class Room {
    private java.util.Map<Coordinate, FixedObject> objects;
    private java.util.Map<Coordinate, Walker> beings;

    public Room() {
        objects = new HashMap<>();
        beings = new HashMap<>();
    }

    public FixedObject getFixedObject(Coordinate coordinate) {
        return objects.get(coordinate);
    }

    public Walker getWalker(Coordinate coordinate) {
        return beings.get(coordinate);
    }
}

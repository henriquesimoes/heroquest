package br.unicamp.ic.mc322.heroquest.map.core.room;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.HashMap;

public class Room {
    private java.util.Map<Coordinate, FixedObject> objects;
    private java.util.Map<Coordinate, Walker> begins;

    public Room() {
        objects = new HashMap<>();
        begins = new HashMap<>();
    }

    public FixedObject getFixedObject(Coordinate coordinate) {
        return objects.get(coordinate);
    }

    public Walker getWalker(Coordinate coordinate) {
        return begins.get(coordinate);
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.HashMap;

public class Room {
    private Pair<Integer, Integer> roomDimension;
    private Coordinate topLeftCoordinates;
    private java.util.Map<Coordinate, FixedObject> objects;
    private java.util.Map<Coordinate, Walker> beings;

    public Room(Pair<Integer, Integer> roomDimension, Coordinate topLeftCoordinates) {
        this.roomDimension = roomDimension;
        this.topLeftCoordinates = topLeftCoordinates;
        objects = new HashMap<>();
        beings = new HashMap<>();
    }

    public Pair<Integer, Integer> getRoomDimension() {
        return roomDimension;
    }

    public Coordinate getTopLeftCoordinates() {
        return topLeftCoordinates;
    }

    public FixedObject getFixedObject(Coordinate coordinate) {
        return objects.get(coordinate);
    }

    public Walker getWalker(Coordinate coordinate) {
        return beings.get(coordinate);
    }
}

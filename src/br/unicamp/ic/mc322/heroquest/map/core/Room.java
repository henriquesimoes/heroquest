package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.HashMap;

public class Room {
    private java.util.Map<Coordinate, FixedObject> objects;
    private java.util.Map<Coordinate, Walker> beings;
    private int id;

    public Room(int id) {
        this.id = id;
        objects = new HashMap<>();
        beings = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void add(FixedObject object) {
        objects.put(object.getPosition(), object);
    }

    public void add(Walker walker) {
        beings.put(walker.getPosition(), walker);
    }

    public boolean isOccupied(Coordinate coordinate) {
        return objects.containsKey(coordinate) || beings.containsKey(coordinate);
    }

    public FixedObject getFixedObject(Coordinate coordinate) {
        return objects.get(coordinate);
    }

    public Walker getWalker(Coordinate coordinate) {
        return beings.get(coordinate);
    }

    public boolean isAllowedToWalkOver(Coordinate coordinate) {
        MapObject object = getPreferentialObject(coordinate);

        if (object != null && !object.isAllowedToWalkOver())
            return false;

        return true;
    }

    public MapObject getPreferentialObject(Coordinate coordinate) {
        Walker walker = getWalker(coordinate);
        if (walker != null)
            return walker;

        return getFixedObject(coordinate);
    }

    public void remove(Walker walker) {
        beings.remove(walker.getPosition());
    }
}

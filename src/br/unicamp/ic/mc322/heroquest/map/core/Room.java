package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Room {
    private Set<MapUnit> units;

    public Room() {
        units = new HashSet<>();
    }

    protected void add(MapObject object) {
        MapUnit unit = getUnit(object.getPosition());

        object.goTo(unit);
    }

    protected void add(StructuralObject object) {
        units.add(new MapUnit(object));
    }

    public boolean isOccupied(Coordinate coordinate) {
        MapUnit unit = getUnit(coordinate);

        return !unit.isFree();
    }

    public boolean contains(Coordinate position) {
        return getUnit(position) != null;
    }

    public void placeObject(PlacementStrategy strategy, Coordinate coordinate, MapObject objectToPlace) {
        MapUnit unit = getUnit(coordinate);

        if (unit.accept(strategy, objectToPlace)) {
            objectToPlace.setPosition(coordinate);

            add(objectToPlace);
        }
    }

    public void move(MapObject object, Coordinate destination) {

    }

    public Collection<Coordinate> getCoordinates() {
        Collection<Coordinate> result = new ArrayList<>();

        for (MapUnit unit : units)
            result.add(unit.getCoordinate());

        return result;
    }

    public void accept(MapObjectVisitor visitor) {
        for (MapUnit unit : units)
            unit.accept(visitor);
    }

    private MapUnit getUnit(Coordinate coordinate) {
        for (MapUnit unit : units)
            if (unit.at(coordinate))
                return unit;

        return null;
    }
}

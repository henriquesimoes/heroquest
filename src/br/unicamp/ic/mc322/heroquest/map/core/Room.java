package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;
import java.util.Collection;

public class Room {
    private Collection<MapUnit> units;

    public Room() {
        units = new ArrayList<>();
    }

    protected void add(MapObject object, Coordinate coordinate) {
        MapUnit unit = getUnit(coordinate);

        object.goTo(unit);
    }

    protected void add(MapObject object) {
        ArrayList<MapUnit> available = new ArrayList<>();

        for (MapUnit unit : units)
            if (unit.isFree())
                available.add(unit);

        if (available.isEmpty())
            throw new IllegalStateException("Full room...");

        MapUnit destination = available.get(Randomizer.nextInt(available.size()));

        object.goTo(destination);
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
        MapUnit originUnit = getUnit(object.getPosition());
        MapUnit destinationUnit = getUnit(destination);

        originUnit.moveWalker(destinationUnit);
    }

    public Collection<Coordinate> getCoordinates() {
        Collection<Coordinate> result = new ArrayList<>();

        for (MapUnit unit : units)
            result.add(unit.getCoordinate());

        return result;
    }

    public StructuralObject getStructure(Coordinate coordinate) {
        MapUnit unit = getUnit(coordinate);

        return unit.getStructure();
    }

    public void accept(MapObjectVisitor visitor) {
        for (MapUnit unit : units)
            unit.accept(visitor);
    }

    public void accept(MapObjectVisitor visitor, Coordinate coordinate) {
        MapUnit unit = getUnit(coordinate);

        unit.accept(visitor);
    }

    public void accept(ConcreteMapObjectVisitor visitor) {
        for (MapUnit unit : units)
            unit.accept(visitor);
    }

    private MapUnit getUnit(Coordinate coordinate) {
        for (MapUnit unit : units)
            if (unit.at(coordinate))
                return unit;

        return null;
    }

    public void remove(Walker walker) {
        MapUnit unit = getUnit(walker.getPosition());

        unit.removeWalker();
    }
}

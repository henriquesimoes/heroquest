package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.loop.GameListener;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Map implements WalkValidator, GameListener {
    private java.util.Map<Coordinate, MapUnit> units;
    private Dimension dimension;

    Map(java.util.Map<Coordinate, MapUnit> units, Dimension dimension) {
        this.units = units;
        this.dimension = dimension;
    }

    public void add(Walker walker, Coordinate coordinate) {
        MapUnit unit = units.get(coordinate);
        unit.add(walker);
        walker.setMap(this);
    }

    public void add(Walker walker) {
        MapUnit unit;
        do {
            // TODO: generate random coordinate
           unit = units.get(dimension.getRandomInsideCoordinate());
        } while (!unit.isFree());

        unit.add(walker);
        walker.setMap(this);
    }

    public void move(Walker walker, Coordinate destination) {
        MapUnit originUnit = units.get(walker.getPosition());
        MapUnit destinationUnit = units.get(destination);

        originUnit.moveWalker(destinationUnit);
    }

    public RegionSelector getRegionSelector() {
        return new RegionSelector(this, this);
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    @Override
    public boolean isAllowedToWalkOver(Coordinate coordinate) {
        return units.get(coordinate).isFree();
    }

    public Coordinate getCoordinateCloserToObject(ArrayList<Coordinate> coordinates, ArrayList<MapObject> objects) {
        Queue<Pair<Coordinate, Coordinate>> queue = new LinkedList<>();
        Set<Coordinate> destination = new HashSet<>();
        Set<Coordinate> visited = new HashSet<>();

        if (objects.isEmpty())
            return null;

        for (MapObject target : objects)
            destination.add(target.getPosition());

        for (Coordinate source : coordinates) {
            queue.add(new Pair<>(source, source));
            visited.add(source);
        }

        while (!queue.isEmpty()) {
            Pair<Coordinate, Coordinate> current = queue.poll();
            Coordinate moveCoordinate = current.getFirst();
            Coordinate sourceCoordinate = current.getSecond();

            for (Coordinate neighbor : moveCoordinate.getNeighborCoordinates()) {
                if (destination.contains(neighbor))
                    return sourceCoordinate;

                if (!visited.contains(neighbor) && isAllowedToWalkOver(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new Pair<>(neighbor, sourceCoordinate));
                }
            }
        }

        return null;
    }

    public void accept(AbstractMapObjectVisitor visitor) {
        for (MapUnit unit : units.values())
            unit.accept(visitor);
    }

    public void accept(ConcreteMapObjectVisitor visitor) {
        for (MapUnit unit : units.values())
            unit.accept(visitor);
    }

    public void accept(AbstractMapObjectVisitor visitor, Region region) {
        for (Coordinate coordinate : region) {
            MapUnit unit = units.get(coordinate);
            unit.accept(visitor);
        }
    }

    @Override
    public void notifyWalkerDeath(Walker deadWalker) {
        remove(deadWalker);
    }

    @Override
    public void notifyWalkerDamage(Walker walker, int damage) {}

    private void remove(Walker walker) {
        MapUnit unit = units.get(walker.getPosition());
        unit.removeWalker();
    }
}

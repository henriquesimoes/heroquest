package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.loop.GameListener;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.VisionValidator;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.WalkableValidator;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Trap;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Map implements GameListener {
    private final java.util.Map<Coordinate, MapUnit> units;
    private final Dimension dimension;

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
        MapUnit unit = getRandomValidUnit(new WalkableValidator(this));
        unit.add(walker);
        walker.setMap(this);
    }

    public void add(Trap trap) {
        MapUnit unit = getRandomValidUnit(new WalkableValidator(this));
        unit.add(trap);
    }

    public void move(Walker walker, Coordinate destination) {
        MapUnit originUnit = units.get(walker.getPosition());
        MapUnit destinationUnit = units.get(destination);

        originUnit.moveWalker(destinationUnit);
    }

    public RegionSelector getRegionSelector() {
        return new RegionSelector(this);
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public Coordinate getCoordinateCloserToObject(ArrayList<Coordinate> coordinates, ArrayList<MapObject> objects) {
        Queue<Pair<Coordinate, Coordinate>> queue = new LinkedList<>();
        Set<Coordinate> destination = new HashSet<>();
        Set<Coordinate> visited = new HashSet<>();
        PositionValidator validator = new VisionValidator(this);

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

            if (validator.isExpandable(moveCoordinate)) {
                for (Coordinate neighbor : moveCoordinate.getCardinalNeighborCoordinates()) {
                    if (destination.contains(neighbor))
                        return sourceCoordinate;

                    if (!visited.contains(neighbor) && validator.isValid(neighbor)) {
                        visited.add(neighbor);
                        queue.add(new Pair<>(neighbor, sourceCoordinate));
                    }
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
        for (Coordinate coordinate : region)
            accept(visitor, coordinate);
    }

    public void accept(ConcreteMapObjectVisitor visitor, Region region) {
        for (Coordinate coordinate : region) {
            MapUnit unit = units.get(coordinate);
            unit.accept(visitor);
        }
    }

    public void accept(AbstractMapObjectVisitor visitor, Coordinate coordinate) {
        MapUnit unit = units.get(coordinate);
        unit.accept(visitor);
    }

    @Override
    public void notifyWalkerDeath(Walker deadWalker) {
        remove(deadWalker);
    }

    @Override
    public void notifyWalkerDamage(Walker walker, int damage) {
    }

    private void remove(Walker walker) {
        MapUnit unit = units.get(walker.getPosition());
        unit.removeWalker();
    }

    private MapUnit getRandomValidUnit(PositionValidator validator) {
        List<MapUnit> listUnits = new ArrayList<>(units.values());
        Collections.shuffle(listUnits);

        for (MapUnit unit : listUnits)
            if (validator.isValid(unit.getCoordinate()))
                return unit;

        throw new IllegalStateException("Map is full");
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.engine.GameListener;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.TrapPositionValidator;
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
    private java.util.Map<Coordinate, MapUnit> units;
    private Dimension dimension;

    Map(java.util.Map<Coordinate, MapUnit> units, Dimension dimension) {
        this.units = units;
        this.dimension = dimension;
    }

    /**
     * Adds a walker in a random position in the map.
     *
     * @param walker - walker to be added.
     */
    public void add(Walker walker) {
        Coordinate coordinate = walker.getPosition();
        MapUnit unit = coordinate == null ? getRandomValidUnit(new WalkableValidator(this)) : units.get(coordinate);
        unit.add(walker);
        walker.setMap(this);
    }

    /**
     * Adds a trap on a random position of the map.
     *
     * @param trap - new trap
     */
    public void add(Trap trap) {
        MapUnit unit = getRandomValidUnit(new TrapPositionValidator(this));
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

    /**
     * Finds the coordinate among the given ones which is can be used
     * to be closest to one of the given objects.
     *
     * @param coordinates - all coordinates to be
     * @param objects - target objects
     * @return best coordinate to be used to get closer to a target
     */
    public Coordinate getCoordinateCloserToObject(ArrayList<Coordinate> coordinates, ArrayList<MapObject> objects) {
        Queue<Pair<Coordinate, Coordinate>> queue = new LinkedList<>();
        Set<Coordinate> destination = new HashSet<>();
        Set<Coordinate> visited = new HashSet<>();
        PositionValidator validator = new VisionValidator(this);

        // check whether it is impossible to find such a coordinate
        if (objects.isEmpty())
            return null;

        // add all possible target destinations
        for (MapObject target : objects)
            destination.add(target.getPosition());

        // add all sources for the multi-source breadth first search (BFS)
        for (Coordinate source : coordinates) {
            queue.add(new Pair<>(source, source));
            visited.add(source);
        }

        while (!queue.isEmpty()) {
            Pair<Coordinate, Coordinate> current = queue.poll();
            Coordinate moveCoordinate = current.getFirst();
            Coordinate sourceCoordinate = current.getSecond();

            // check whether the neighbor coordinates are to be checked afterwards
            // i.e. whether they're visible.
            if (validator.isExpandable(moveCoordinate)) {
                for (Coordinate neighbor : moveCoordinate.getCardinalNeighborCoordinates()) {
                    /*
                     * Since the BFS gives the shortest distance in an unweighted graph,
                     * the first destination point reached gives the best source option.
                     */
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

    /**
     * Lets the `visitor` visit all objects in the map.
     *
     * If there are more objects in the same position, the object that
     * has the preference is visited. The preference is given in decreasing
     * order by:
     *
     * 1. Walker;
     * 2. Fixed object;
     * 3. Structural object;
     *
     * @param visitor - map visitor
     */
    public void accept(AbstractMapObjectVisitor visitor) {
        for (MapUnit unit : units.values())
            unit.accept(visitor);
    }

    /**
     * Lets the `visitor` visit all objects in the map.
     *
     * If there are more objects in the same position, the object that
     * has the preference is visited. The preference is given in decreasing
     * order by:
     *
     * 1. Walker;
     * 2. Fixed object;
     * 3. Structural object;
     *
     * @param visitor - map visitor
     */
    public void accept(ConcreteMapObjectVisitor visitor) {
        for (MapUnit unit : units.values())
            unit.accept(visitor);
    }

    /**
     * Lets the `visitor` visit all objects in the given region of the
     * map.
     *
     * If there are more objects in the same position, the object that
     * has the preference is visited. The preference is given in decreasing
     * order by:
     *
     * 1. Walker;
     * 2. Fixed object;
     * 3. Structural object;
     *
     * @param visitor - map visitor
     */
    public void accept(AbstractMapObjectVisitor visitor, Region region) {
        for (Coordinate coordinate : region)
            accept(visitor, coordinate);
    }

    /**
     * Lets the `visitor` visit all objects in the given region of the
     * map.
     *
     * If there are more objects in the same position, the object that
     * has the preference is visited. The preference is given in decreasing
     * order by:
     *
     * 1. Walker;
     * 2. Fixed object;
     * 3. Structural object;
     *
     * @param visitor - map visitor
     */
    public void accept(ConcreteMapObjectVisitor visitor, Region region) {
        for (Coordinate coordinate : region)
            accept(visitor, coordinate);
    }

    /**
     * Lets the `visitor` visit the object in the given coordinate.
     *
     * If there are more objects in the given position, the object that
     * has the preference is visited. The preference is given in decreasing
     * order by:
     *
     * 1. Walker;
     * 2. Fixed object;
     * 3. Structural object;
     *
     * @param visitor - map visitor
     */
    public void accept(AbstractMapObjectVisitor visitor, Coordinate coordinate) {
        MapUnit unit = units.get(coordinate);
        unit.accept(visitor);
    }

    /**
     * Lets the `visitor` visit the object in the given coordinate.
     *
     * If there are more objects in the given position, the object that
     * has the preference is visited. The preference is given in decreasing
     * order by:
     *
     * 1. Walker;
     * 2. Fixed object;
     * 3. Structural object;
     *
     * @param visitor - map visitor
     */
    public void accept(ConcreteMapObjectVisitor visitor, Coordinate coordinate) {
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
        unit.remove(walker);
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

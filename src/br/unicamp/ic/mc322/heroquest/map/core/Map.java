package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.loop.GameListener;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Door;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Map implements WalkValidator, GameListener {
    private Room[] rooms;
    private Door[] doors;
    private Dimension dimension;

    protected Map(Room[] rooms, Door[] doors, Dimension dimension) {
        this.rooms = rooms;
        this.doors = doors;
        this.dimension = dimension;
    }

    public void add(Walker walker, Coordinate coordinate) {
        Room room = getRoom(coordinate);

        room.add(walker, coordinate);
    }

    public void add(Walker walker) {
        Room room = rooms[Randomizer.nextInt(rooms.length)];

        room.add(walker);
    }

    public void move(Walker walker, Coordinate destination) {
        Room room = getRoom(destination);

        room.move(walker, destination);
    }

    private void remove(Walker walker) {
        Room room = getRoom(walker.getPosition());

        room.remove(walker);
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

    public Collection<Coordinate> getRoomCoordinates(Coordinate reference) {
        Room room = getRoom(reference);

        return room.getCoordinates();
    }

    @Override
    public boolean isAllowedToWalkOver(Coordinate coordinate) {
        try {
            Room room = getRoom(coordinate);
            return !room.isOccupied(coordinate);
        } catch (OutsideRoomException ex) {
            Door door = getDoor(coordinate);

            if (door != null)
                return door.isOpen();
        }

        return false;
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
            Coordinate moveCoordinate = current.getKey();
            Coordinate sourceCoordinate = current.getValue();

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

    public void accept(MapObjectVisitor visitor) {
        for (Room room : rooms)
            room.accept(visitor);
    }

    public void accept(ConcreteMapObjectVisitor visitor) {
        for (Room room : rooms)
            room.accept(visitor);
        for (Door door : doors)
            door.accept(visitor);
    }

    public void accept(MapObjectVisitor visitor, Region region) {
        for (Coordinate coordinate : region) {
            try {
                Room room = getRoom(coordinate);

                room.accept(visitor, coordinate);
            } catch (OutsideRoomException ex) {
                Door door = getDoor(coordinate);

                if (door != null)
                    door.accept(visitor);
            }
        }
    }

    private Room getRoom(Coordinate coordinate) throws OutsideRoomException {
        for (Room room : rooms)
            if (room.contains(coordinate))
                return room;

        throw new OutsideRoomException();
    }

    private Door getDoor(Coordinate coordinate) {
        for (Door door : doors)
            if (door.at(coordinate))
                return door;

        return null;
    }

    @Override
    public void notifyWalkerDeath(Walker deadWalker) {
        remove(deadWalker);
    }

    @Override
    public void notifyWalkerDamage(Walker walker, int damage) {}
}

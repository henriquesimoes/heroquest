package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.*;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Map implements WalkValidator {
    private Room[] rooms;
    private Dimension dimension;

    protected Map(Room[] rooms, Dimension dimension) {
        this.rooms = rooms;
        this.dimension = dimension;
    }

    public void add(Walker walker, Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);

        walker.setPosition(coordinate);

        room.add(walker);
    }

    public void move(Walker walker, Coordinate destination) {
        Room room = getRoom(destination);

        room.move(walker, destination);
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Region region) {
        ArrayList<Walker> walkers = new ArrayList<>();

        Room room = getRoom(region.getReference());

        for (Coordinate coordinate : region) {
            Walker walker = null; // room.getWalker(coordinate);

            if (walker != null)
                walkers.add(walker);
        }

        return walkers;
    }

    public RegionSelector getRegionSelector() {
        return new RegionSelector(this, this);
    }

    public ArrayList<Coordinate> getWalkablePositions(Region region) {
        return null;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public ArrayList<MapObject> getUnoccupiedPositions(Region region) {
        ArrayList<MapObject> objects = new ArrayList<>();

        for (Coordinate coordinate : region) {
            Room room = getRoom(coordinate);

            // if (!room.isOccupied(coordinate))
            //    objects.add(room.getStructuralObject(coordinate));
        }

        return objects;
    }

    public Collection<Coordinate> getRoomCoordinates(Coordinate reference) {
        Room room = getRoom(reference);

        return room.getCoordinates();
    }

    @Override
    public boolean isAllowedToWalkOver(Coordinate coordinate) {
        Room room = getRoom(coordinate);

        return room.isOccupied(coordinate);
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

            if (destination.contains(moveCoordinate)) {
                return sourceCoordinate;
            }

            for (Coordinate neighbor : moveCoordinate.getNeighborCoordinates()) {
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

    private Room getRoom(Coordinate coordinate) throws OutsideRoomException {
        for (Room room : rooms)
            if (room.contains(coordinate))
                return room;

        throw new OutsideRoomException();
    }
}

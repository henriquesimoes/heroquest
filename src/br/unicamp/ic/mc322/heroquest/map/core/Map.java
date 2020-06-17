package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.*;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Map implements WalkValidator {
    private MapStructure structure;
    private ArrayList<Room> rooms;

    public Map(MapStructure structure) {
        this.structure = structure;
        this.rooms = new ArrayList<>();

        build();
    }

    private void build() {
        structure.updateRooms();

        int numberOfRooms = structure.getNumberOfRooms();

        for (int i = 0; i < numberOfRooms; i++)
            rooms.add(new Room(i));
    }

    public void add(FixedObject object, Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);

        // TODO: Check whether an object can be placed only on walkable positions or not.
        if (structure.isAllowedToWalkOver(coordinate) && !room.isOccupied(coordinate)) {
            object.setPosition(coordinate);
            room.add(object);
        }
    }

    public void add(Walker walker, Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);

        // TODO: prevent duplicate insertion of a walker

        if (structure.isAllowedToWalkOver(coordinate) && !room.isOccupied(coordinate)) {
            walker.setPosition(coordinate);
            room.add(walker);
        }
    }

    public void remove(Walker walker, Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);
        room.remove(walker);
    }

    /**
     * Returns the object that has the highest priority for interaction. This ordering is the following
     *      - Walkers
     *      - Fixed map objects
     *      - Structural part of the map
     * @param coordinate Coordinate to get object from.
     * @return Preferential object
     */
    public MapObject getPreferentialObject(Coordinate coordinate) {
        MapObject structuralObject = structure.getObjectAt(coordinate);
        MapObject preferential = structuralObject;

        try {
            Room room = getRoom(coordinate);

            MapObject object = room.getPreferentialObject(coordinate);
            if (object != null)
                preferential = object;

        } catch (OutsideRoomException ex) {
            // TODO: Check how to better model this, in order to avoid empty catch block
        }

        return preferential;
    }

    public Dimension getDimension() {
        return structure.getDimension();
    }

    public RegionSelector getRegionSelector() {
        return new RegionSelector(structure, this);
    }

    public void moveObject(Walker walker, Coordinate destination) {
        if (isAllowedToWalkOver(destination)) {
            remove(walker, walker.getPosition());
            add(walker, destination);
        }
    }

    // TODO: consider removing this method (feature already provided by the distance object)
    public ArrayList<Coordinate> getWalkablePositions(Region region) {
        Iterator<Coordinate> iterator = region.iterator();
        ArrayList<Coordinate> positions = new ArrayList<>();

        while (iterator.hasNext()) {
            Coordinate coordinate = iterator.next();

            // TODO: restrict movement to the same room, except for doors
            if (isAllowedToWalkOver(coordinate))
                positions.add(coordinate);
        }

        return positions;
    }

    private Room getRoom(Coordinate coordinate) throws OutsideRoomException {
        int id = structure.getRoomIdAt(coordinate);

        return rooms.get(id);
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Region region) {
        ArrayList<Walker> walkers = new ArrayList<>();

        Room room = getRoom(region.getReference());

        for (Coordinate coordinate : region) {
            Walker walker = room.getWalker(coordinate);

            if (walker != null)
                walkers.add(walker);
        }

        return walkers;
    }

    public ArrayList<MapObject> getUnoccupiedPositions(Walker reference) {
        ArrayList<MapObject> objects = new ArrayList<>();

        RegionSelector regionSelector = getRegionSelector();

        regionSelector.useAsReference(reference.getPosition());
        Region region = regionSelector.getRoomRegion(true);

        for (Coordinate coordinate : region)
            objects.add(structure.getObjectAt(coordinate));

        return objects;
    }

    @Override
    public boolean isAllowedToWalkOver(Coordinate coordinate) {
        if (!structure.isAllowedToWalkOver(coordinate))
            return false;

        try {
            Room room = getRoom(coordinate);

            return room.isAllowedToWalkOver(coordinate);
        } catch (OutsideRoomException e) {
            return false;
        }
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
}

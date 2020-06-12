package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.geom.Ruler;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.*;

public class Map {
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

    public FixedObject getObject(Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);

        return room.getFixedObject(coordinate);
    }

    public Walker getWalker(Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);

        return room.getWalker(coordinate);
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

    public Ruler getRuler() {
        return new Ruler(structure);
    }

    public void moveObject(MapObject mapObject, Coordinate destination) {
        if (isAllowedToWalkOver(destination)) {
            mapObject.setPosition(destination);
        }
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(Distance distance) {
        Iterator<Coordinate> iterator = distance.iterator();
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

    public ArrayList<Walker> getAllWalkersWithinArea(Distance distance) {
        Iterator<Coordinate> iterator = distance.iterator();
        ArrayList<Walker> walkers = new ArrayList<>();

        Room room = getRoom(distance.getReference());

        while (iterator.hasNext()) {
            Coordinate coordinate = iterator.next();

            Walker walker = room.getWalker(coordinate);

            if (walker != null)
                walkers.add(walker);
        }

        return walkers;
    }

    public ArrayList<MapObject> getUnoccupiedPositions(Walker reference) {
        ArrayList<MapObject> objects = new ArrayList<>();

        Room room = getRoom(reference.getPosition());

        ArrayList<Coordinate> positions = structure.getRoomCoordinates(room.getId());

        for (Coordinate current : positions) {
            MapObject structuralObject = structure.getObjectAt(current);
            MapObject object = room.getPreferentialObject(current);

            if ((object == null || object.isAllowedToWalkOver()) && structuralObject.isAllowedToWalkOver())
                objects.add(object);
        }

        return objects;
    }

    private boolean isAllowedToWalkOver(Coordinate coordinate) {
        if (!structure.isAllowedToWalkOver(coordinate))
            return false;

        try {
            Room room = getRoom(coordinate);

            return room.isAllowedToWalkOver(coordinate);
        } catch (OutsideRoomException e) {
            return false;
        }
    }

    // TODO: Consider refactoring this method by moving to the Coordinate class
    public static Coordinate getCoordinateCloserToObject(ArrayList<Coordinate> coordinates, ArrayList<MapObject> objects) {
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
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new Pair<>(neighbor, sourceCoordinate));
                }
            }
        }

        return null;
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;
import java.util.Iterator;

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
        if (structure.isWalkOverable(coordinate) && !room.isOccupied(coordinate)) {
            room.add(object);
        }
    }

    public void add(Walker walker, Coordinate coordinate) throws OutsideRoomException {
        Room room = getRoom(coordinate);

        if (structure.isWalkOverable(coordinate) && !room.isOccupied(coordinate))
            room.add(walker);
    }

    public FixedObject getObject(Coordinate coordinate) {
        try {
            Room room = getRoom(coordinate);

            return room.getFixedObject(coordinate);
        } catch (OutsideRoomException ex) {
            return null;
        }
    }

    public Walker getWalker(Coordinate coordinate) {
        try {
            Room room = getRoom(coordinate);

            return room.getWalker(coordinate);
        } catch (OutsideRoomException ex) {
            return null;
        }
    }

    public ObjectView getStructureRepresentationAt(Coordinate coordinate) {
        return structure.get(coordinate).getRepresentation();
    }

    public Dimension getDimension() {
        return structure.getDimension();
    }

    public void moveObject(MapObject mapObject, Coordinate destination) {
        if (isWalkOverable(destination)) {
            mapObject.setPosition(destination);
        }
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(Walker reference, Distance distance) {
        Iterator<Coordinate> iterator = distance.getCoveredCoordinates(reference.getPosition());
        ArrayList<Coordinate> positions = new ArrayList<>();

        while (iterator.hasNext()) {
            Coordinate coordinate = iterator.next();

            // TODO: restrict movement to the same room, except for doors
            if (isWalkOverable(coordinate))
                positions.add(coordinate);
        }

        return positions;
    }

    private Room getRoom(Coordinate coordinate) throws OutsideRoomException {
        int id = structure.getRoomIdAt(coordinate);

        return rooms.get(id);
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Walker reference, Distance distance) {
        Iterator<Coordinate> iterator = distance.getCoveredCoordinates(reference.getPosition());
        ArrayList<Walker> walkers = new ArrayList<>();

        try {
            Room room = getRoom(reference.getPosition());

            while (iterator.hasNext()) {
                Coordinate coordinate = iterator.next();

                Walker walker = room.getWalker(coordinate);

                if (walker != null)
                    walkers.add(walker);
            }

            return walkers;
        } catch (OutsideRoomException ex) {
            return walkers;
        }
    }

    public ArrayList<MapObject> getUnoccupiedPositions(Walker reference) {
        ArrayList<MapObject> objects = new ArrayList<>();

        try {
            Room room = getRoom(reference.getPosition());

            ArrayList<Coordinate> positions = structure.getRoomCoordinates(room.getId());

            for (Coordinate current : positions) {
                MapObject structuralObject = structure.get(current);
                MapObject object = room.getPreferentialObject(current);

                if ((object == null || object.isWalkOverable()) && structuralObject.isWalkOverable())
                    objects.add(object);
            }

            return objects;
        } catch (OutsideRoomException ex) {
            return objects;
        }
    }

    private boolean isWalkOverable(Coordinate coordinate) {
        if (!structure.isWalkOverable(coordinate))
            return false;

        try {
            Room room = getRoom(coordinate);

            return room.isWalkOverable(coordinate);
        } catch (OutsideRoomException e) {
            return false;
        }
    }
}

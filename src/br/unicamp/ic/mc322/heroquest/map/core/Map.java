package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

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
            rooms.add(new Room());
    }

    public FixedObject getObject(Coordinate coordinate) {
        try {
            int id = structure.getRoomIdAt(coordinate);

            return rooms.get(id).getFixedObject(coordinate);
        } catch (OutsideRoomException ex) {
            return null;
        }
    }

    public Walker getWalker(Coordinate coordinate) {
        try {
            int id = structure.getRoomIdAt(coordinate);

            return rooms.get(id).getWalker(coordinate);
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

    public void moveObject(MapObject mapObject, Coordinate coordinateDestination) {
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(Walker reference, int maximumDistance) {
        return null;
    }

    public ArrayList<Walker> getWalkersOfTheFourDirections(Walker reference) {
        return null;
    }

    public ArrayList<Walker> getEnemiesInAdjacentPositions(Walker reference) {
        return null;
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Walker reference, int radius) {
        return null;
    }
}

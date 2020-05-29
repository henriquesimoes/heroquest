package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.room.Room;
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
        int id = structure.getRoomAt(coordinate);

        if (id == MapStructure.OUTSIDE_ROOM)
            return null;

        return rooms.get(id).getFixedObject(coordinate);
    }

    public Walker getWalker(Coordinate coordinate) {
        int id = structure.getRoomAt(coordinate);

        if (id == MapStructure.OUTSIDE_ROOM)
            return null;

        return rooms.get(id).getWalker(coordinate);
    }

    public ObjectView getStructureRepresentationAt(Coordinate coordinate) {
        return structure.get(coordinate).getRepresentation();
    }

    public Dimension getDimension() {
        return structure.getDimension();
    }

    public void moveObject(MapObject mapObject, Coordinate coordinateDestination){
    }

    public ArrayList<Coordinate> getPositionsWithDistanceUp(int limitPositionInMove) {
        return null;
    }
}

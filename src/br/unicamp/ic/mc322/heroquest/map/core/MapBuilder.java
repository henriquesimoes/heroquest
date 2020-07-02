package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Wall;

public class MapBuilder {
    private PlacementStrategy placementStrategy;
    private MapStructure structure;
    private Room[] rooms;
    private Door[] doors;
    private Map result;

    private boolean isStructureBuilt;

    public MapBuilder(PlacementStrategy placementStrategy) {
        this.placementStrategy = placementStrategy;
        reset();
    }

    public void add(Door door) {
        if (isStructureBuilt)
            throw new IllegalStateException("Structure already built...");

        structure.add(door);
    }

    public void add(Wall wall) {
        if (isStructureBuilt)
            throw new IllegalStateException("Structure already built...");

        structure.add(wall);
    }

    public void add(Floor floor) {
        if (isStructureBuilt)
            throw new IllegalStateException("Structure already built...");

        structure.add(floor);
    }

    public void add(FixedObject object, Coordinate position) {
        if (!isStructureBuilt)
            throw new IllegalStateException("Structure not built yet...");

        Room room = getRoom(position);

        room.placeObject(placementStrategy, position, object);
    }

    public void reset() {
        isStructureBuilt = false;
        structure = new MapStructure();
        rooms = null;
        doors = null;
        result = null;
    }

    public void buildStructure() {
        if (isStructureBuilt)
            throw new IllegalStateException("Structure already built...");

        structure.build();
        rooms = structure.getRooms();
        doors = structure.getDoors();
        isStructureBuilt = true;
    }

    public void buildMap() {
        if (!isStructureBuilt)
            throw new IllegalStateException("Structure must be built first...");

        result = new Map(rooms, doors, structure.getDimension());
    }

    public Map getResult() {
        return result;
    }

    private Room getRoom(Coordinate position) {
        for (Room room : rooms)
            if (room.contains(position))
                return room;

        throw new OutsideRoomException();
    }
}

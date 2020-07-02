package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class MapStructure {
    private Map<Coordinate, StructuralObject> objects;
    private Map<Coordinate, Integer> roomMapping;
    private Dimension dimension;
    private int numberOfRooms;
    private Room[] rooms;
    private Collection<Door> doors;

    private static final int UNDEFINED_ROOM = -1;
    private static final int OUTSIDE_ROOM = -2;

    public MapStructure() {
        objects = new HashMap<>();
        roomMapping = new HashMap<>();
        dimension = new Dimension(0, 0);
        doors = new ArrayList<>();

        numberOfRooms = 0;
    }

    public void add(StructuralObject object) {
        Coordinate position = object.getPosition();

        objects.put(position, object);
        roomMapping.put(position, object.belongsToARoom() ? UNDEFINED_ROOM : OUTSIDE_ROOM);

        if (!position.inside(dimension))
            // Update is done by creating a new reference, since the dimension must be immutable for other modules
            dimension = dimension.fit(position);
    }

    public void add(Door door) {
        doors.add(door);
        add((StructuralObject) door);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Door[] getDoors() {
        Door[] result = new Door[doors.size()];
        doors.toArray(result);
        return result;
    }

    public void build() {
        Coordinate origin = Coordinate.getOrigin();

        int id = 0;
        for (int dy = 0; dy < dimension.getHeight(); dy++)
            for (int dx = 0; dx < dimension.getWidth(); dx++) {
                Coordinate current = Coordinate.shift(origin, dx, dy);

                if (roomMapping.getOrDefault(current, OUTSIDE_ROOM) == UNDEFINED_ROOM)
                    fillRoom(current, id++);
            }

        numberOfRooms = id;

        buildRooms();
    }

    private void buildRooms() {
        rooms = new Room[numberOfRooms];

        for (int i = 0; i < rooms.length; i++)
            rooms[i] = new Room();

        Coordinate origin = Coordinate.getOrigin();

        for (int dy = 0; dy < dimension.getHeight(); dy++)
            for (int dx = 0; dx < dimension.getWidth(); dx++) {
                Coordinate current  = Coordinate.shift(origin, dx, dy);

                int id = roomMapping.getOrDefault(current, OUTSIDE_ROOM);
                if (id != OUTSIDE_ROOM)
                    rooms[id].add(objects.get(current));
            }
    }

    /**
     * Recursively fills the room undefined mapping starting from the given position.
     *
     * @param start Start point to fill the room
     * @param id Identification to assign to each position reachable by the starting point
     */
    private void fillRoom(Coordinate start, int id) {
        if (!roomMapping.containsKey(start)
                || roomMapping.get(start) == OUTSIDE_ROOM
                || roomMapping.get(start) != UNDEFINED_ROOM)
            return;

        roomMapping.put(start, id);

        for (Coordinate neighbor : start.getNeighborCoordinates()) {
            if (roomMapping.getOrDefault(neighbor, OUTSIDE_ROOM) == UNDEFINED_ROOM)
                fillRoom(neighbor, id);
        }
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.structural.StructuralObject;

import java.util.*;
import java.util.Map;

public class MapStructure {
    private Map<Coordinate, StructuralObject> objects;
    private Map<Coordinate, Integer> room;
    private Dimension dimension;
    private int numberOfRooms;

    private static final int UNDEFINED_ROOM = -1;
    public static final int OUTSIDE_ROOM = -2;

    public MapStructure() {
        objects = new HashMap<>();
        room = new HashMap<>();
        dimension = new Dimension(0,0);

        numberOfRooms = 0;
    }

    public void add(StructuralObject object) {
        Coordinate position = object.getPosition();

        objects.put(position, object);
        room.put(position, object.belongsToARoom() ? UNDEFINED_ROOM : OUTSIDE_ROOM);

        if (!position.inside(dimension))
            // Update is done by creating a new reference, since the dimension must be immutable for other modules
            dimension = dimension.fit(position);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public StructuralObject get(Coordinate coordinate) {
        return objects.get(coordinate);
    }

    public void updateRooms() {
        Coordinate origin = Coordinate.getOrigin();

        int id = 0;
        for (int dx = 0; dx < dimension.getHeight(); dx++)
            for (int dy = 0; dy < dimension.getWidth(); dy++) {
                Coordinate current = Coordinate.shift(origin, dx, dy);

                if (room.getOrDefault(room, OUTSIDE_ROOM) == UNDEFINED_ROOM)
                    fillRoom(current, id++);
            }

        numberOfRooms = id;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Returns the room ID when the a valid position is provided.
     * A given position may be valid but still not belong to a room.
     *
     * Use the `OUTSIDE_ROOM` constant to check whether that's the case.
     *
     * @param coordinate Position whose room ID is requested
     * @return room ID or OUTSIDE_ROOM constant otherwise
     */
    public int getRoomAt(Coordinate coordinate) {
        // TODO: Use exception to warn about outside position
        return room.getOrDefault(coordinate, OUTSIDE_ROOM);
    }

    public boolean isValid() {
        return true;
    }

    /**
     * Recursively fills the room undefined mapping starting from the given position.
     *
     * @param start Start point to fill the room
     * @param id Identification to assign to each position reachable by the starting point
     */
    private void fillRoom(Coordinate start, int id) {
        if (!room.containsKey(start)
                || room.get(start) == OUTSIDE_ROOM
                || room.get(start) != UNDEFINED_ROOM)
            return;

        room.put(start, id);

        for (Coordinate neighbor : start.getNeighborCoordinates()) {
            if (room.getOrDefault(neighbor, OUTSIDE_ROOM) == UNDEFINED_ROOM)
                fillRoom(start, id);
        }
    }
}

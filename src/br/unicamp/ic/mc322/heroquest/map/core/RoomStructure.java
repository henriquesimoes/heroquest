package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

public class RoomStructure {
    private Room room;
    private Dimension roomDimension;
    private Coordinate roomTopLeftCoordinates;
    private int id;

    public RoomStructure(Dimension roomDimension, Coordinate roomTopLeftCoordinates, int id) {
        this.room = new Room();
        this.roomDimension = roomDimension;
        this.roomTopLeftCoordinates = roomTopLeftCoordinates;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Coordinate getRoomTopLeftCoordinates() {
        return roomTopLeftCoordinates;
    }

    public Dimension getRoomDimension() {
        return roomDimension;
    }
}

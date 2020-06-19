package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

public class RoomStructure {
    private Room room;
    private Dimension roomDimension;
    private Coordinate roomTopLeftCoordinates;
    private Coordinate roomDoorCoordinates;
    private int id;

    public RoomStructure(Dimension roomDimension, Coordinate roomTopLeftCoordinates, Coordinate roomDoorCoordinates) {
        this.room = new Room();
        this.roomDimension = roomDimension;
        this.roomTopLeftCoordinates = roomTopLeftCoordinates;
    }


    public Coordinate getRoomTopLeftCoordinates() {
        return roomTopLeftCoordinates;
    }

    public Dimension getRoomDimension() {
        return roomDimension;
    }
}

package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;
import br.unicamp.ic.mc322.heroquest.map.core.OutsideRoomException;

import java.util.ArrayList;

public class Ruler {
    private Coordinate reference;
    private MapStructure structure;

    public Ruler(MapStructure structure) {
        this.structure = structure;
        this.fixAt(Coordinate.getOrigin());
    }

    public void fixAt(Coordinate coordinate) {
        this.reference = coordinate;
    }

    public AdjacentDistance getAdjacentDistance() {
        return new AdjacentDistance(reference);
    }

    public AdjacentDistance getAdjacentDistance(Coordinate reference) {
        return new AdjacentDistance(reference);
    }

    public CardinalDistance getCardinalDistance() {
        return new CardinalDistance(reference);
    }

    public CardinalDistance getCardinalDistance(Coordinate reference) {
        return new CardinalDistance(reference);
    }

    public LimitedDistance getLimitedDistance(int limit) {
        return new LimitedDistance(reference, limit);
    }

    public LimitedDistance getLimitedDistance(Coordinate reference, int limit) {
        return new LimitedDistance(reference, limit);
    }

    public RoomDistance getRoomDistance(Coordinate reference) throws OutsideRoomException {
        ArrayList<Coordinate> roomCoordinates = structure.getRoomCoordinates(reference);

        return new RoomDistance(reference, roomCoordinates);
    }

    public RoomDistance getRoomDistance() throws OutsideRoomException {
        return getRoomDistance(reference);
    }
}

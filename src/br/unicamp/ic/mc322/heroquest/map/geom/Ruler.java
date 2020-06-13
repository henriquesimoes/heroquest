package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;
import br.unicamp.ic.mc322.heroquest.map.core.OutsideRoomException;
import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

import java.util.ArrayList;

public class Ruler {
    private Coordinate reference;
    private MapStructure structure;
    private WalkValidator validator;

    public Ruler(MapStructure structure, WalkValidator validator) {
        this.structure = structure;
        this.validator = validator;

        this.fixAt(Coordinate.getOrigin());
    }

    public void fixAt(Coordinate coordinate) {
        this.reference = coordinate;
    }

    public Distance getAdjacentDistance(boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            return new AdjacentDistance(reference, validator);
        return new AdjacentDistance(reference);
    }

    public Distance getCardinalDistance(boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            return new CardinalDistance(reference, validator);
        return new CardinalDistance(reference);
    }

    public Distance getLimitedDistance(int limit, boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            return new LimitedDistance(reference, validator, limit);
        return new LimitedDistance(reference, limit);
    }

    public Distance getRoomDistance(Coordinate reference, boolean onlyWalkablePositions)
            throws OutsideRoomException {
        ArrayList<Coordinate> roomCoordinates = structure.getRoomCoordinates(reference);

        if (onlyWalkablePositions)
            return new RoomDistance(reference, validator, roomCoordinates);

        return new RoomDistance(reference, roomCoordinates);
    }

    public Distance getRoomDistance(boolean onlyWalkablePositions) throws OutsideRoomException {
        return getRoomDistance(reference, onlyWalkablePositions);
    }
}

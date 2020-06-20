package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;
import br.unicamp.ic.mc322.heroquest.map.core.OutsideRoomException;
import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

import java.util.ArrayList;

public class RegionSelector {
    private Coordinate reference;
    private MapStructure structure;
    private WalkValidator validator;

    public RegionSelector(MapStructure structure, WalkValidator validator) {
        this.structure = structure;
        this.validator = validator;

        this.useAsReference(Coordinate.getOrigin());
    }

    public void useAsReference(Coordinate coordinate) {
        this.reference = coordinate;
    }

    public void useAsReference(MapObject object) {
        useAsReference(object.getPosition());
    }

    public Region getAdjacentRegion(boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            return new AdjacentRegion(reference, validator);
        return new AdjacentRegion(reference);
    }

    public Region getCardinalRegion(boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            return new CardinalRegion(reference, validator);
        return new CardinalRegion(reference);
    }

    public Region getLimitedRegion(int limit, boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            return new LimitedRegion(reference, validator, limit);
        return new LimitedRegion(reference, limit);
    }

    public Region getRoomRegion(Coordinate reference, boolean onlyWalkablePositions)
            throws OutsideRoomException {
        ArrayList<Coordinate> roomCoordinates = structure.getRoomCoordinates(reference);

        if (onlyWalkablePositions)
            return new RoomRegion(reference, validator, roomCoordinates);

        return new RoomRegion(reference, roomCoordinates);
    }

    public Region getRoomRegion(boolean onlyWalkablePositions) throws OutsideRoomException {
        return getRoomRegion(reference, onlyWalkablePositions);
    }
}

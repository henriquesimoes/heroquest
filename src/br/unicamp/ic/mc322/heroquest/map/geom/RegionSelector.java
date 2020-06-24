package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapStructure;
import br.unicamp.ic.mc322.heroquest.map.core.OutsideRoomException;
import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

import java.util.Collection;

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
        return build(new AdjacentRegion(reference), onlyWalkablePositions);
    }

    public Region getCardinalRegion(boolean onlyWalkablePositions) {
        return build(new CardinalRegion(reference), onlyWalkablePositions);
    }

    public Region getLimitedRegion(int limit, boolean onlyWalkablePositions) {
        return build(new LimitedRegion(reference, limit), onlyWalkablePositions);
    }

    public Region getRoomRegion(Coordinate reference, boolean onlyWalkablePositions)
            throws OutsideRoomException {
        Collection<Coordinate> roomCoordinates = structure.getRoomCoordinates(reference);

        return build(new RoomRegion(reference, roomCoordinates), onlyWalkablePositions);
    }

    public Region getRoomRegion(boolean onlyWalkablePositions) throws OutsideRoomException {
        return getRoomRegion(reference, onlyWalkablePositions);
    }

    private Region build(Region region,  boolean onlyWalkablePositions) {
        if (onlyWalkablePositions)
            region.setWalkValidator(validator);

        region.build();

        return region;
    }
}

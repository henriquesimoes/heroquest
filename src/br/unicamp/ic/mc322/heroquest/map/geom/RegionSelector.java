package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.VisionValidator;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.WalkableValidator;

public class RegionSelector {
    private final Map map;
    private Coordinate reference;

    public RegionSelector(Map map) {
        this.map = map;

        this.useAsReference(Coordinate.getOrigin());
    }

    public static Region getPlaneRegion(Dimension dimension) {
        Region region = new PlaneRegion(Coordinate.getOrigin(), dimension.toCoordinate());

        region.build();

        return region;
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

    public Region getAdjacentRegion(Coordinate reference, boolean onlyWalkablePositions) {
        return build(new AdjacentRegion(reference), onlyWalkablePositions);
    }

    public Region getCardinalRegion(boolean onlyWalkablePositions) {
        return build(new CardinalRegion(reference), onlyWalkablePositions);
    }

    public Region getLimitedRegion(int limit, boolean onlyWalkablePositions) {
        return build(new LimitedRegion(reference, limit), onlyWalkablePositions);
    }

    public Region getVisibleRegion(Coordinate reference) {
        return build(new VisibleRegion(reference), false);
    }

    public Region getVisibleRegion() {
        return getVisibleRegion(reference);
    }

    private Region build(Region region, boolean onlyWalkablePositions) {
        region.setPositionValidator(onlyWalkablePositions ? new WalkableValidator(map) : new VisionValidator(map));

        region.build();

        return region;
    }
}

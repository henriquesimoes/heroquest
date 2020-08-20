package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.VisionValidator;
import br.unicamp.ic.mc322.heroquest.map.core.positionValidator.WalkableValidator;

/**
 * RegionSelector allows to select regions associated with a map.
 */
public class RegionSelector {
    private final Map map;
    private Coordinate reference;

    public RegionSelector(Map map) {
        this.map = map;

        this.useAsReference(Coordinate.getOrigin());
    }

    /**
     * Selects the region covered by the given dimension. In other words, the region
     * that starts on the origin and has the given dimension.
     *
     * @param dimension - desired region dimension
     * @return plane region on the origin with the given dimension
     */
    public static Region getPlaneRegion(Dimension dimension) {
        Region region = new PlaneRegion(Coordinate.getOrigin(), dimension.toCoordinate());

        region.build();

        return region;
    }

    /**
     * Configures the reference to be used in future region selections.
     *
     * @param coordinate - reference
     */
    public void useAsReference(Coordinate coordinate) {
        this.reference = coordinate;
    }

    /**
     * Configures the reference coordinate as the walker position.
     * <p>
     * When the walker moves, the region selection will use its updated
     * position without need to resetting it.
     *
     * @param object - reference object
     */
    public void useAsReference(MapObject object) {
        useAsReference(object.getPosition());
    }

    /**
     * Selects the region that covers all eight positions around the reference coordinate.
     *
     * @param onlyWalkablePositions - whether to exclude position not possible to walk over
     * @return adjacent region
     */
    public Region getAdjacentRegion(boolean onlyWalkablePositions) {
        return build(new AdjacentRegion(reference), onlyWalkablePositions);
    }

    /**
     * Selects the region that covers all eight positions around the reference coordinate.
     *
     * @param reference             - point of view
     * @param onlyWalkablePositions - whether to exclude position not possible to walk over
     * @return adjacent region
     */
    public Region getAdjacentRegion(Coordinate reference, boolean onlyWalkablePositions) {
        return build(new AdjacentRegion(reference), onlyWalkablePositions);
    }

    /**
     * Selects the region that covers the four cardinal (north, south, east and west) positions
     * around the reference coordinate.
     *
     * @param onlyWalkablePositions - whether to exclude position not possible to walk over
     * @return cardinal region
     */
    public Region getCardinalRegion(boolean onlyWalkablePositions) {
        return build(new CardinalRegion(reference), onlyWalkablePositions);
    }

    /**
     * Selects the region that includes all coordinates within a (Manhattan) distance
     * range.
     *
     * @param limit                 - Manhattan distance limit
     * @param onlyWalkablePositions - whether to consider only positions possible to walk to.
     * @return limited region
     */
    public Region getLimitedRegion(int limit, boolean onlyWalkablePositions) {
        return build(new LimitedRegion(reference, limit), onlyWalkablePositions);
    }

    /**
     * Selects the region that is visible on the `reference` point of view.
     *
     * @param reference - point of view
     * @return visible region
     */
    public Region getVisibleRegion(Coordinate reference) {
        return build(new VisibleRegion(reference), false);
    }

    /**
     * Selects the region that is visible on the configured reference point of view.
     *
     * @return visible region
     */
    public Region getVisibleRegion() {
        return getVisibleRegion(reference);
    }

    private Region build(Region region, boolean onlyWalkablePositions) {
        region.setPositionValidator(onlyWalkablePositions ? new WalkableValidator(map) : new VisionValidator(map));

        region.build();

        return region;
    }
}

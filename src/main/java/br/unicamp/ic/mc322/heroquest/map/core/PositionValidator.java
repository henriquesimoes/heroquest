package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

/**
 * Determines how a object in the map impacts on a map region definition.
 */
public interface PositionValidator {
    /**
     * Defines whether a object should be considered part of the map region.
     *
     * @param coordinate - object coordinate
     * @return `true` if it is valid and `false` otherwise
     */
    boolean isValid(Coordinate coordinate);

    /**
     * Defines whether the coordinate blocks a map region expansion,
     * i.e., whether the object impacts the original region area.
     * Note this region expansion impact does not mean the object
     * itself is part or not of the region; `isValid` should be
     * used to check that instead.
     *
     * @param coordinate - coordinate to be checked
     * @return `false` if it blocks the region, and `true` otherwise.
     */
    boolean isExpandable(Coordinate coordinate);
}

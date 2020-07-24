package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.PositionValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class Region implements Iterable<Coordinate> {
    protected Coordinate reference;
    protected Collection<Coordinate> coordinates;
    private PositionValidator positionValidator;

    /**
     * Creates a region based on the given reference. The reference is usually
     * the coordinate of a walker, which needs to look something up in the map.
     *
     * @param reference region base coordinate
     */
    Region(Coordinate reference) {
        this.reference = reference;
        this.coordinates = new ArrayList<>();
    }

    public ArrayList<Coordinate> toArrayList() {
        return new ArrayList<>(coordinates);
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return coordinates.iterator();
    }

    protected void setPositionValidator(PositionValidator validator) {
        positionValidator = validator;
    }

    /**
     * Defines whether a object should be considered part of the map region.
     *
     * @param coordinate object coordinate
     * @return `true` if it is valid and `false` otherwise
     */
    protected boolean isValid(Coordinate coordinate) {
        return positionValidator.isValid(coordinate);
    }

    /**
     * Defines whether the coordinate blocks a map region expansion,
     * i.e., whether the object impacts the original region area.
     * Note this region expansion impact does not mean the object
     * itself is part or not of the region; `isValid` should be
     * used to check that instead.
     *
     * @param coordinate coordinate to be checked
     * @return `false` if it blocks the region, and `true` otherwise.
     */
    protected boolean isExpandable(Coordinate coordinate) {
        return positionValidator.isExpandable(coordinate);
    }

    /**
     * Creates the region on the memory by adding all the region
     * coordinates into `coordinates` field.
     */
    protected abstract void build();
}

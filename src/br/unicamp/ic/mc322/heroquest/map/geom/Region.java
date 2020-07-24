package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.PositionValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class Region implements Iterable<Coordinate> {
    protected Coordinate reference;
    protected Collection<Coordinate> coordinates;
    private PositionValidator positionValidator;

    Region(Coordinate reference) {
        this.reference = reference;
        this.coordinates = new ArrayList<>();
    }

    public void setPositionValidator(PositionValidator validator) {
        positionValidator = validator;
    }

    public ArrayList<Coordinate> toArrayList() {
        return new ArrayList<>(coordinates);
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return coordinates.iterator();
    }

    protected boolean isValid(Coordinate coordinate) {
        return positionValidator.isValid(coordinate);
    }

    protected boolean isExpandable(Coordinate coordinate) {
        return positionValidator.isExpandable(coordinate);
    }

    protected abstract void build();
}

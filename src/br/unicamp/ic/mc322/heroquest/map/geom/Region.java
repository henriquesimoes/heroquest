package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class Region implements Iterable<Coordinate> {
    private WalkValidator walkValidator;
    protected Coordinate reference;
    protected Collection<Coordinate> coordinates;

    Region(Coordinate reference) {
        this.reference = reference;
        this.coordinates = new ArrayList<>();
        this.walkValidator = null;
    }

    public void setWalkValidator(WalkValidator validator) {
        walkValidator = validator;
    }

    public Coordinate getReference() {
        return reference;
    }

    protected boolean isValid(Coordinate coordinate) {
        return walkValidator == null || walkValidator.isAllowedToWalkOver(coordinate);
    }

    public ArrayList<Coordinate> toArrayList() {
        return new ArrayList<>(coordinates);
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return coordinates.iterator();
    }

    protected abstract void build();
}

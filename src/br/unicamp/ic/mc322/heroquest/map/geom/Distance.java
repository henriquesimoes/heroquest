package br.unicamp.ic.mc322.heroquest.map.geom;

import java.util.Iterator;

public abstract class Distance implements Iterable<Coordinate> {
    protected Coordinate reference;

    public void setReference(Coordinate reference) {
        this.reference = reference;
    }

    public Iterator<Coordinate> getCoveredCoordinates(Coordinate reference) {
        setReference(reference);
        return iterator();
    }
}

package br.unicamp.ic.mc322.heroquest.map.geom;

public abstract class Distance implements Iterable<Coordinate> {
    protected Coordinate reference;

    Distance(Coordinate reference) {
        this.reference = reference;
    }

    public Coordinate getReference() {
        return reference;
    }
}

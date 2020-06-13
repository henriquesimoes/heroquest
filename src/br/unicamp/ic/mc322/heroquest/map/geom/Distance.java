package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

public abstract class Distance implements Iterable<Coordinate> {
    protected Coordinate reference;
    protected WalkValidator walkValidator;

    Distance(Coordinate reference) {
        this.reference = reference;
        this.walkValidator = null;
    }

    Distance(Coordinate reference, WalkValidator walkValidator) {
        this(reference);

        this.walkValidator = walkValidator;
    }

    public Coordinate getReference() {
        return reference;
    }
}

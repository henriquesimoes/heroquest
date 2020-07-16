package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public abstract class PositionValidator {
    protected Map map;
    protected boolean lastVisitedIsValid;
    protected boolean lastVisitedIsExpandable;

    protected PositionValidator(Map map) {
        this.map = map;
    }

    public abstract boolean isValid(Coordinate coordinate);

    public abstract boolean isExpandable(Coordinate coordinate);
}

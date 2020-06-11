package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class MapObject {
    private Coordinate position;

    public MapObject() {

    }

    public MapObject(Coordinate coordinate) {
        this();
        setPosition(coordinate);
    }

    public Coordinate getPosition() {
        return position;
    }

    protected void setPosition(Coordinate position) {
        this.position = position;
    }

    public abstract boolean isAllowedToWalkOver();
    public abstract void interact(Walker agent);
    public abstract ObjectView getRepresentation();

    public abstract String getRepresentationOnMenu();
}

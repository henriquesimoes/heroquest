package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class MapObject {
    private Coordinate position;

    public MapObject() {
        position = new Coordinate(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public MapObject(Coordinate coordinate) {
        this();
        setPosition(coordinate);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public boolean at(Coordinate coordinate) {
        return position.equals(coordinate);
    }

    public Coordinate getPosition() {
        return position;
    }

    protected void setPosition(Coordinate position) {
        this.position.copyValue(position);
    }

    public abstract boolean isAllowedToWalkOver();
    public abstract void interact(Walker agent);

    public abstract String getRepresentationOnMenu();

    public abstract boolean accept(PlacementStrategy strategy, MapObject object);
    public abstract void accept(MapObjectVisitor visitor);
    public abstract void goTo(MapUnit unit);
}

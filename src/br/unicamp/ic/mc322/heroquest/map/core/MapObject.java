package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.placement.PlacementStrategy;
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

    public boolean isAt(Coordinate coordinate) {
        return position.equals(coordinate);
    }

    public Coordinate getPosition() {
        return position;
    }

    protected void setPosition(Coordinate position) {
        this.position.copyValue(position);
    }

    public abstract void interact(Walker agent);
    public abstract boolean isAllowedToWalkOver();
    public abstract String getRepresentationOnMenu();

    public abstract void goTo(MapUnit unit);
    public abstract boolean accept(PlacementStrategy strategy, MapObject object);
    public abstract void accept(AbstractMapObjectVisitor visitor);
    public abstract void accept(ConcreteMapObjectVisitor visitor);
}

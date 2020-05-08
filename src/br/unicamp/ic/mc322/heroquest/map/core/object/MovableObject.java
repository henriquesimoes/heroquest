package br.unicamp.ic.mc322.heroquest.map.core.object;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;

public abstract class MovableObject extends MapObject {
    public MovableObject(Coordinate position) {
        super(position);
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isWalkOverable() {
        return false;
    }

    public abstract void move();
}

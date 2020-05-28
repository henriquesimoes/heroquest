package br.unicamp.ic.mc322.heroquest.map.core.object;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;

public abstract class FixedObject extends MapObject {
    public FixedObject(Coordinate position) {
        super(position);
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}

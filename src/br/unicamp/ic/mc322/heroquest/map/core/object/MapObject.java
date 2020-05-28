package br.unicamp.ic.mc322.heroquest.map.core.object;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public abstract class MapObject {
    private Coordinate position;

    public MapObject(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public abstract boolean isMovable();
    public abstract boolean isWalkOverable();
    public abstract void interact(Walker agent);
    public abstract ObjectView getRepresentation();
}

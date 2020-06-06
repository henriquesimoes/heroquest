package br.unicamp.ic.mc322.heroquest.map.object;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class MapObject {
    private Coordinate position;

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public abstract boolean isAllowedToWalkOverPosition();
    public abstract void interact(Walker agent);
    public abstract ObjectView getRepresentation();
}

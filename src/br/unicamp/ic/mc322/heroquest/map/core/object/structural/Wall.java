package br.unicamp.ic.mc322.heroquest.map.core.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class Wall extends FixedObject {
    public Wall(Coordinate position) {
        super(position);
    }

    @Override
    public boolean isWalkOverable() {
        return false;
    }

    @Override
    public void interact() {

    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("#");
    }
}

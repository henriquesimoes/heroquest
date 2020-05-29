package br.unicamp.ic.mc322.heroquest.map.core.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Wall extends StructuralObject {
    public Wall(Coordinate position) {
        super(position);
    }

    @Override
    public boolean belongsToARoom() {
        return false;
    }

    @Override
    public boolean isWalkOverable() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
        return;
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("#");
    }
}

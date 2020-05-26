package br.unicamp.ic.mc322.heroquest.map.core.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.core.object.Walker;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class Floor extends FixedObject {

    public Floor(Coordinate position) {
        super(position);
    }

    @Override
    public boolean isWalkOverable() {
        return true;
    }

    @Override
    public void interact(Walker agent) {
        return;
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView(" ");
    }
}

package br.unicamp.ic.mc322.heroquest.map.core.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.core.object.Walker;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class Door extends FixedObject {
    private boolean opened;

    public Door(Coordinate position) {
        super(position);

        opened = false;
    }

    @Override
    public boolean isWalkOverable() {
        return opened;
    }

    @Override
    public void interact(Walker walker) {
        // TODO: restrict monsters ability to interact with doors

        opened = !opened;
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView(opened ? " " : "D");
    }
}

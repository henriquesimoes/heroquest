package br.unicamp.ic.mc322.heroquest.map.object.structural;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Door extends StructuralObject {
    private boolean opened;

    public Door(Coordinate position) {
        super(position);

        opened = false;
    }

    @Override
    public boolean belongsToARoom() {
        return false;
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

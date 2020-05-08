package br.unicamp.ic.mc322.heroquest.map.core.object.fixed;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class Chest extends FixedObject {
    private boolean opened;

    // TODO: Introduce storage item management

    public Chest(Coordinate position) {
        super(position);

        opened = false;
    }

    @Override
    public boolean isWalkOverable() {
        return false;
    }

    @Override
    public void interact() {
        /**
         * TODO: Implement opening interaction with chest
         */
    }

    @Override
    public ObjectView getRepresentation() {
        return null;
    }
}

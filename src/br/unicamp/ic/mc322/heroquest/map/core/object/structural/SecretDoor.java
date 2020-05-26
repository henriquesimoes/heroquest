package br.unicamp.ic.mc322.heroquest.map.core.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.object.Walker;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;

public class SecretDoor extends Door {
    private boolean discovered;

    public SecretDoor(Coordinate position) {
        super(position);
        discovered = false;
    }

    public void discover() {
        discovered = true;
    }

    @Override
    public void interact(Walker agent) {
        if (discovered) {
            super.interact(agent);
        }
    }

    @Override
    public ObjectView getRepresentation() {
        if (discovered)
            return super.getRepresentation();

        return new ObjectView("#");
    }
}

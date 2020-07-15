package br.unicamp.ic.mc322.heroquest.map.objects.structural;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

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
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
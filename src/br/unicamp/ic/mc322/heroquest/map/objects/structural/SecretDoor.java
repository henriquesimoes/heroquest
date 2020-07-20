package br.unicamp.ic.mc322.heroquest.map.objects.structural;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.HiddenObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class SecretDoor extends Door implements HiddenObject {
    private boolean discovered;

    public SecretDoor(Coordinate position) {
        super(position);
        discovered = false;
    }

    public void discover() {
        discovered = true;
    }

    @Override
    public boolean isDiscovered() {
        return discovered;
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

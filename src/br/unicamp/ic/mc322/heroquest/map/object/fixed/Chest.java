package br.unicamp.ic.mc322.heroquest.map.object.fixed;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Chest extends FixedObject {
    private final boolean opened;

    // TODO: Introduce storage item management

    public Chest() {
        opened = false;
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
        /**
         * TODO: Implement opening interaction with chest
         */
        return;
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Chest on " + getPosition();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

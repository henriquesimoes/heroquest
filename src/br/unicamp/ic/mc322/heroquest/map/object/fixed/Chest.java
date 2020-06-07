package br.unicamp.ic.mc322.heroquest.map.object.fixed;

import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Chest extends  FixedObject {
    private boolean opened;

    // TODO: Introduce storage item management

    public Chest() {
        opened = false;
    }

    @Override
    public boolean isWalkOverable() {
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
    public ObjectView getRepresentation() {
        return new ObjectView("c");
    }

    @Override
    public String getRepresentationOnMenu() {
        return null;
    }
}

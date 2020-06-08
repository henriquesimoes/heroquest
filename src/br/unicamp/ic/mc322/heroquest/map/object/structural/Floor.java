package br.unicamp.ic.mc322.heroquest.map.object.structural;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Floor extends StructuralObject {

    public Floor(Coordinate position) {
        super(position);
    }

    @Override
    public boolean belongsToARoom() {
        return true;
    }

    @Override
    public boolean isAllowedToWalkOverPosition() {
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

    @Override
    public String getRepresentationOnMenu() {
        return "Free space";
    }
}

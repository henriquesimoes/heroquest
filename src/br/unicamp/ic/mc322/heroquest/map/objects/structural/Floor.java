package br.unicamp.ic.mc322.heroquest.map.objects.structural;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
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
    public boolean isAllowedToWalkOver() {
        return true;
    }

    @Override
    public void interact(Walker agent) {
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Free space on " + getPosition();
    }

    @Override
    public boolean canPlaceWalkerOn() {
        return true;
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

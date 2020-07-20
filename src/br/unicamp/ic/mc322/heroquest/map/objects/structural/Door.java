package br.unicamp.ic.mc322.heroquest.map.objects.structural;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Door extends StructuralObject {
    private boolean opened;

    public Door(Coordinate position) {
        super(position);

        opened = false;
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return opened;
    }

    @Override
    public void interact(Walker walker) {
        opened = !opened;
    }

    public boolean isOpen() {
        return opened;
    }

    @Override
    public String getRepresentationOnMenu() {
        return (opened ? "Opened" : "Closed") + " door on " + getPosition();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

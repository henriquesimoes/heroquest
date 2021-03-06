package br.unicamp.ic.mc322.heroquest.map.objects.structural;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Wall extends StructuralObject {
    public Wall(Coordinate position) {
        super(position);
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Wall on " + getPosition();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public abstract class StructuralObject implements MapObject {
    private final Coordinate position;

    public StructuralObject(Coordinate coordinate) {
        this.position = coordinate;
    }

    public Coordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(Coordinate position) {
        this.position.copy(position);
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

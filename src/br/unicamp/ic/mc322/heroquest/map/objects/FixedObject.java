package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public abstract class FixedObject implements MapObject {
    private Coordinate position;

    public FixedObject() {
    }

    public FixedObject(Coordinate coordinate) {
        this.position = coordinate;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(Coordinate position) {
        if (this.position == null)
            this.position = new Coordinate(position.getX(), position.getY());
        else
            this.position.copy(position);
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

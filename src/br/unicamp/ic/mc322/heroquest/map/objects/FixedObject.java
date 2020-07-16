package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public abstract class FixedObject implements MapObject {
    private Coordinate position;

    public FixedObject() {
        position = new Coordinate();
    }

    public FixedObject(Coordinate coordinate) {
        this();
        setPosition(coordinate);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(Coordinate position) {
        this.position.copyValue(position);
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

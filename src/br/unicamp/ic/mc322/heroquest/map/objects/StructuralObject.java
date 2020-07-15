package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapUnit;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.placement.PlacementStrategy;

public abstract class StructuralObject implements MapObject {
    private Coordinate position;

    public StructuralObject(){
        position = new Coordinate();
    }
    public StructuralObject(Coordinate coordinate) {
        this();
        setPosition(coordinate);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position.copyValue(position);
    }

    @Override
    public void goTo(MapUnit unit) {
        return;
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }


    /**
     * Determines whether a given structural object belongs to a room.
     *
     * @return `true` if it belongs to a room, and `false` otherwise.
     */
    public abstract boolean belongsToARoom();
    public abstract boolean accept(PlacementStrategy strategy, MapObject object);
    public abstract boolean canPlaceWalkerOn();
}

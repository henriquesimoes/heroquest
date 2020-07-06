package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class MapUnit {
    private StructuralObject structure;
    private Walker walker;
    private FixedObject fixedObject;

    public MapUnit(StructuralObject object) {
        this.structure = object;
    }

    public void add(Walker walker) {
        if (structure.isAllowedToWalkOver() && (fixedObject == null || fixedObject.isAllowedToWalkOver())) {
            this.walker = walker;
            this.walker.setPosition(getCoordinate());
        }
        else
            throw new IllegalStateException("Not walkable unit...");
    }

    public void add(FixedObject object) {
        if (fixedObject == null) {
            fixedObject = object;
            fixedObject.setPosition(getCoordinate());
        }
        else
            throw new OccupiedUnitException();
    }

    public void moveWalker(MapUnit destination) {
        if (walker != null && destination.isFree()) {
            destination.add(walker);
            removeWalker();
        }
    }

    protected void removeWalker() {
        walker = null;
    }

    public boolean isFree() {
        return walker == null;
    }

    public Coordinate getCoordinate() {
        return structure.getPosition();
    }

    public StructuralObject getStructure() {
        return structure;
    }

    public boolean at(Coordinate coordinate) {
        return this.structure.at(coordinate);
    }

    public boolean accept(PlacementStrategy strategy, MapObject object) {
        return structure.accept(strategy, object);
    }

    public void accept(MapObjectVisitor visitor) {
        if (walker != null)
            walker.accept(visitor);
        else if (fixedObject != null)
            fixedObject.accept(visitor);
        else
            structure.accept(visitor);
    }

    public void accept(ConcreteMapObjectVisitor visitor) {
        if (walker != null)
            walker.accept(visitor);
        else if (fixedObject != null)
            fixedObject.accept(visitor);
        else
            structure.accept(visitor);
    }
}

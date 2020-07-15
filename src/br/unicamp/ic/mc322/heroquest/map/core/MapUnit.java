package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.map.placement.PlacementStrategy;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class MapUnit {
    private StructuralObject structure;
    private Walker walker;
    private FixedObject fixedObject;

    MapUnit(StructuralObject object) {
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

    void accept(ConcreteMapObjectVisitor visitor) {
        if (walker != null)
            walker.accept(visitor);
        else if (fixedObject != null)
            fixedObject.accept(visitor);
        else
            structure.accept(visitor);
    }

    void accept(AbstractMapObjectVisitor visitor) {
        if (walker != null)
            walker.accept(visitor);
        else if (fixedObject != null)
            fixedObject.accept(visitor);
        else
            structure.accept(visitor);
    }

    Coordinate getCoordinate() {
        return structure.getPosition();
    }

    boolean accept(PlacementStrategy strategy, MapObject object) {
        return structure.accept(strategy, object);
    }

    void moveWalker(MapUnit destination) {
        if (walker != null && destination.isFree()) {
            destination.add(walker);
            removeWalker();
        }
    }

    void removeWalker() {
        walker = null;
    }

    private boolean isFree() {
        return walker == null;
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

/**
 * Basic unit of a map. Each unit occupies a given position on the map, defined
 * by its structural object.
 */
public class MapUnit {
    private final StructuralObject structure;
    private Walker walker;
    private FixedObject fixedObject;

    MapUnit(StructuralObject object) {
        this.structure = object;
    }

    public void add(Walker walker) {
        if (structure.isAllowedToWalkOver() && (fixedObject == null || fixedObject.isAllowedToWalkOver())) {
            this.walker = walker;
            this.walker.setPosition(getCoordinate());
            if (fixedObject != null)
                fixedObject.interact(walker);
        } else
            throw new IllegalStateException("Not walkable unit...");
    }

    public void add(FixedObject object) {
        if (fixedObject == null) {
            fixedObject = object;
            fixedObject.setPosition(getCoordinate());
        } else
            throw new OccupiedUnitException();
    }

    void accept(ConcreteMapObjectVisitor visitor) {
        getPreferential().accept(visitor);
    }

    void accept(AbstractMapObjectVisitor visitor) {
        getPreferential().accept(visitor);
    }

    Coordinate getCoordinate() {
        return structure.getPosition();
    }

    void moveWalker(MapUnit destination) {
        if (walker != null && destination.isFree()) {
            destination.add(walker);
            remove(walker);
        }
    }

    void remove(Walker walker) {
        if (walker != null && walker.equals(this.walker))
            this.walker = null;
    }

    private boolean isFree() {
        return walker == null;
    }

    /**
     * Defines which object has the preference to be interacted
     * or visited by other modules.
     *
     * @return this unit preferential object
     */
    private MapObject getPreferential() {
        if (walker != null)
            return walker;
        if (fixedObject != null)
            return fixedObject;
        return structure;
    }

}

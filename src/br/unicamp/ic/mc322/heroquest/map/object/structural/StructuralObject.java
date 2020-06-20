package br.unicamp.ic.mc322.heroquest.map.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public abstract class StructuralObject extends MapObject {

    public StructuralObject(Coordinate coordinate) {
        super(coordinate);
    }

    /**
     * Determines whether a given structural object belongs to a room.
     *
     * @return `true` if it belongs to a room, and `false` otherwise.
     */
    public abstract boolean belongsToARoom();
}

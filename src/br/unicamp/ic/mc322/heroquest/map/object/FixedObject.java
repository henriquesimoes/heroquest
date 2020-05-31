package br.unicamp.ic.mc322.heroquest.map.object;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public abstract class FixedObject extends MapObject {
    public FixedObject(Coordinate coordinate) {
        setPosition(coordinate);
    }
}

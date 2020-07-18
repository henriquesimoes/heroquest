package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public interface PositionValidator {
    boolean isValid(Coordinate coordinate);

    boolean isExpandable(Coordinate coordinate);
}

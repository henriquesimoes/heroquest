package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public interface WalkValidator {

    boolean isAllowedToWalkOver(Coordinate coordinate);
}

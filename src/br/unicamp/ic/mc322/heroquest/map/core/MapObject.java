package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface MapObject {
    int getX();

    int getY();

    Coordinate getPosition();

    void setPosition(Coordinate position);

    void interact(Walker agent);

    boolean isAllowedToWalkOver();

    String getRepresentationOnMenu();

    void accept(AbstractMapObjectVisitor visitor);

    void accept(ConcreteMapObjectVisitor visitor);
}

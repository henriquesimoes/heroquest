package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface MapObject {
    /**
     * @return object position on the map
     */
    Coordinate getPosition();

    /**
     * Updates the map object's position.
     *
     * @param position - new position
     */
    void setPosition(Coordinate position);

    void interact(Walker agent);

    boolean isAllowedToWalkOver();

    String getRepresentationOnMenu();

    void accept(AbstractMapObjectVisitor visitor);

    void accept(ConcreteMapObjectVisitor visitor);
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface MapObject {
    int getX();

    int getY();

    /**
     * @return object position on the map
     */
    Coordinate getPosition();

    void setPosition(Coordinate position);

    /**
     * Defines how an object state changes due to an interaction.
     *
     * @param agent - walker that is interacting with the map object
     */
    void interact(Walker agent);

    /**
     * Defines whether the object can be co-exist with an walker in
     * the same position. In order word, it checks whether a walker
     * can walk over the object.
     *
     * @return `true` if it is possible to walk over,
     * and `false` otherwise.
     */
    boolean isAllowedToWalkOver();

    /**
     * @return the object representation on any menu
     */
    String getRepresentationOnMenu();

    void accept(AbstractMapObjectVisitor visitor);

    void accept(ConcreteMapObjectVisitor visitor);
}

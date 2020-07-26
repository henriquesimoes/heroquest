package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

/**
 * Defines the map display interface to modules.
 */
public interface MapViewer extends ConcreteMapObjectVisitor {

    /**
     * Displays the map based on the `reference` range of sight.
     *
     * @param reference - object whose point of view is considered in the map visibility.
     *                  Usually, the reference is the player character who is playing the current turn.
     */
    void display(Coordinate reference);
}

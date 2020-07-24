package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public interface MapViewer extends ConcreteMapObjectVisitor {

    /**
     * Displays the map based on the `reference` range of sight.
     *
     * @param reference Coordinate which point of view is considered to the map visibility.
     *                  Usually, the reference is the player character who is playing the current turn.
     */
    void display(Coordinate reference);
}

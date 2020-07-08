package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;

public interface Viewer extends ConcreteMapObjectVisitor {

    /**
     * Displays the map based on the `reference` range of sight.
     * @param reference Object which consider point of view is considered to the map visibility.
     *                  Usually, the reference is the player character who is playing the current turn.
     */
    void display(MapObject reference);
}

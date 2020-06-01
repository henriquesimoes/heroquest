package br.unicamp.ic.mc322.heroquest.map.view;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;

public interface Viewer {

    /**
     * Displays the map based on the `reference` range of sight.
     * @param map Map to be displayed, in which the reference is in.
     * @param reference Object which consider point of view is considered to the map visibility.
     *                  Usually, the reference is the player character who is playing the current turn.
     */
    void display(Map map, MapObject reference);
}

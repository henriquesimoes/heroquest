package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;

/**
 * Defines a common interface for hidden objects.
 */
public interface HiddenObject extends MapObject {
    /**
     * Triggers the discover effect on the object
     */
    void discover();

    /**
     * @return whether the object has already been discovered
     */
    boolean isDiscovered();
}

package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;

public interface HiddenObject extends MapObject {
    void discover();

    boolean isDiscovered();
}

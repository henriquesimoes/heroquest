package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

public interface HiddenObject {
    void discover();
    boolean isDiscovered();
    Coordinate getPosition();
    String getRepresentationOnMenu();
}

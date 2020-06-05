package br.unicamp.ic.mc322.heroquest.map.geom;

import java.util.Iterator;

public abstract class Distance {
    public abstract Iterator<Coordinate> getCoveredCoordinates(Coordinate reference);
}

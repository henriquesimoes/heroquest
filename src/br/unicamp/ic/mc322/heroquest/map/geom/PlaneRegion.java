package br.unicamp.ic.mc322.heroquest.map.geom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PlaneRegion extends Region {
    private Coordinate end;
    private Collection<Coordinate> coordinates;

    public PlaneRegion(Dimension dimension) {
        super(Coordinate.getOrigin());
        end = dimension.toCoordinate();
        coordinates = new ArrayList<>();
    }

    public PlaneRegion(Coordinate start, Coordinate end) {
        super(start);
        this.end = end;
        coordinates = new ArrayList<>();
    }

    @Override
    public Iterator<Coordinate> iterator() {
        for (int x = reference.getX(); x < end.getX(); x++)
            for (int y = reference.getY(); y < end.getY(); y++)
                coordinates.add(new Coordinate(x, y));

        return coordinates.iterator();
    }
}

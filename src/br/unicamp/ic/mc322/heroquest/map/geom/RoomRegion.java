package br.unicamp.ic.mc322.heroquest.map.geom;

import java.util.ArrayList;
import java.util.Collection;

public class RoomRegion extends Region {
    public RoomRegion(Coordinate reference, Collection<Coordinate> roomCoordinates) {
        super(reference);

        this.coordinates = roomCoordinates;
    }

    @Override
    protected void build() {
        Collection<Coordinate> walkable = new ArrayList<>();

        for (Coordinate coordinate : coordinates)
            if (isValid(coordinate))
                walkable.add(coordinate);

        this.coordinates = walkable;
    }
}

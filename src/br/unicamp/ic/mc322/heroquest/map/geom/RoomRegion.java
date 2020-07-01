package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RoomRegion extends Region {
    private Collection<Coordinate> coordinates;
    public RoomRegion(Coordinate reference, Collection<Coordinate> roomCoordinates) {
        super(reference);

        this.coordinates = roomCoordinates;
    }

    public RoomRegion(Coordinate reference, WalkValidator walkValidator, Collection<Coordinate> coordinates) {
        super(reference, walkValidator);

        ArrayList<Coordinate> walkable = new ArrayList<>();

        for (Coordinate coordinate : coordinates)
            if (walkValidator.isAllowedToWalkOver(coordinate))
                walkable.add(coordinate);

        this.coordinates = walkable;
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return coordinates.iterator();
    }
}

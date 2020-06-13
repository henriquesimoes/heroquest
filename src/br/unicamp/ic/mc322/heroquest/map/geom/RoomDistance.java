package br.unicamp.ic.mc322.heroquest.map.geom;

import br.unicamp.ic.mc322.heroquest.map.core.WalkValidator;

import java.util.ArrayList;
import java.util.Iterator;

public class RoomDistance extends Distance {
    private ArrayList<Coordinate> coordinates;
    public RoomDistance(Coordinate reference, ArrayList<Coordinate> roomCoordinates) {
        super(reference);

        this.coordinates = roomCoordinates;
    }

    public RoomDistance(Coordinate reference, WalkValidator walkValidator, ArrayList<Coordinate> coordinates) {
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

package br.unicamp.ic.mc322.heroquest.map.geom;

import java.util.ArrayList;
import java.util.Iterator;

public class RoomDistance extends Distance {
    private ArrayList<Coordinate> coordinates;
    public RoomDistance(Coordinate reference, ArrayList<Coordinate> roomCoordinates) {
        super(reference);

        this.coordinates = roomCoordinates;
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return coordinates.iterator();
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.geom.Ruler;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class VisibleMap {
    private Map map;
    private Walker walker;
    private Ruler ruler;

    public VisibleMap() {
        this.ruler = map.getRuler();

        ruler.fixAt(walker.getPosition());
    }

    public void moveWalker(Coordinate coordinate) {
        map.moveObject(walker, coordinate);
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(int maximumDistance) {
        return map.getWalkablePositions(ruler.getLimitedDistance(maximumDistance, true));
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Distance distance) {
        return map.getAllWalkersWithinArea(distance);
    }

    public ArrayList<MapObject>  getVisibleUnoccupiedPositions() {
        // TODO: implement visibility restriction
        return map.getUnoccupiedPositions(walker);
    }

    public Ruler getRuler() {
        return ruler;
    }

    public ArrayList<Walker> getVisibleWalkers() {
        // TODO
        return null;
    }

    public Coordinate getCoordinateCloserToObject(ArrayList<Coordinate> coordinates, ArrayList<MapObject> objects) {
        return map.getCoordinateCloserToObject(coordinates, objects);
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.geom.LimitedDistance;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class VisibleMap {
    Map map;
    Walker walker;

    public void moveWalker(Coordinate coordinate) {
        map.moveObject(walker, coordinate);
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(int maximumDistance) {
        Distance distance = new LimitedDistance(maximumDistance);

        return map.getCloseWalkablePositions(walker, distance);
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Walker reference, Distance distance) {
        return map.getAllWalkersWithinArea(reference, distance);
    }

    public ArrayList<MapObject>  getVisibleUnoccupiedPositions() {
        // TODO: implement visibility restriction
        return map.getUnoccupiedPositions(walker);
    }
}

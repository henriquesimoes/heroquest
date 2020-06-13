package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class VisibleMap {
    private Map map;
    private Walker walker;
    private RegionSelector regionSelector;

    public VisibleMap() {
        this.regionSelector = map.getRegionSelector();

        regionSelector.useAsReference(walker.getPosition());
    }

    public void moveWalker(Coordinate coordinate) {
        map.moveObject(walker, coordinate);
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(int maximumDistance) {
        return map.getWalkablePositions(regionSelector.getLimitedRegion(maximumDistance, true));
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Region region) {
        return map.getAllWalkersWithinArea(region);
    }

    public ArrayList<MapObject>  getVisibleUnoccupiedPositions() {
        // TODO: implement visibility restriction
        return map.getUnoccupiedPositions(walker);
    }

    public RegionSelector getRegionSelector() {
        return regionSelector;
    }

    public ArrayList<Walker> getVisibleWalkers() {
        // TODO
        return null;
    }

    public Coordinate getCoordinateCloserToObject(ArrayList<Coordinate> coordinates, ArrayList<MapObject> objects) {
        return map.getCoordinateCloserToObject(coordinates, objects);
    }
}

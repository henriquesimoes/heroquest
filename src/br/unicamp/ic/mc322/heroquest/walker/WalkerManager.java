package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;

import java.util.ArrayList;

public abstract class WalkerManager {
    protected Walker walker;
    protected Map map;
    protected RegionSelector regionSelector;

    public void moveWalker(Coordinate position) {
        map.move(walker, position);
    }

    public RegionSelector getRegionSelector() {
        return regionSelector;
    }

    public Coordinate getCoordinateCloserToWalkers(ArrayList<Coordinate> coordinates, ArrayList<Walker> walkers) {
        ArrayList<MapObject> objects = new ArrayList<>(walkers);

        return map.getCoordinateCloserToObject(coordinates, objects);
    }

    public Coordinate getWalkerPosition() {
        return walker.getPosition();
    }

    public Walker getWalker() {
        return walker;
    }

    void setWalker(Walker walker) {
        this.walker = walker;
    }

    public boolean isAlive() {
        return walker.isAlive();
    }

    public String getWalkerName() {
        return walker.getName();
    }

    public void accept(AbstractMapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }

    protected String getStatus() {
        return walker.getStatus();
    }

    protected void setMap(Map map) {
        changeMap(map);
    }

    protected void changeMap(Map map) {
        this.map = map;
        this.regionSelector = map.getRegionSelector();
        regionSelector.useAsReference(walker);
    }

    public abstract void playTurn();

    public abstract void showMessage(String message);

    public abstract MapObject chooseTarget(MapObject[] targets);
}

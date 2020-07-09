package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;
import br.unicamp.ic.mc322.heroquest.map.placement.PlacementStrategy;

public class MapBuilder {
    private PlacementStrategy placementStrategy;
    private MapCreator creator;
    private Map result;

    private boolean isBuilt;

    public MapBuilder(PlacementStrategy placementStrategy) {
        this.placementStrategy = placementStrategy;
        reset();
    }

    public void add(Door door) {
        if (isBuilt)
            throw new IllegalStateException("Structure already built...");

        creator.add(door);
    }

    public void add(Wall wall) {
        if (isBuilt)
            throw new IllegalStateException("Structure already built...");

        creator.add(wall);
    }

    public void add(Floor floor) {
        if (isBuilt)
            throw new IllegalStateException("Structure already built...");

        creator.add(floor);
    }

    public void add(FixedObject object, Coordinate position) {
        if (!isBuilt)
            throw new IllegalStateException("Structure not built yet...");

        creator.add(placementStrategy, object, position);
    }

    public void reset() {
        isBuilt = false;
        creator = new MapCreator();
        result = null;
    }

    public void buildStructure() {
        if (isBuilt)
            throw new IllegalStateException("Structure already built...");

        creator.create();
        isBuilt = true;
    }

    public void buildMap() {
        if (!isBuilt)
            throw new IllegalStateException("Structure must be built first...");

        result = new Map(creator.getRooms(), creator.getDoors(), creator.getDimension());
    }

    public Map getResult() {
        return result;
    }
}

package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MapConfiguration {
    private Set<MapObject> objects;
    private Dimension dimension;

    public MapConfiguration() {
        objects = new HashSet<>();
    }

    public MapConfiguration(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public boolean addObject(MapObject object) {
        return objects.add(object);
    }

    public boolean removeObject(MapObject object) {
        return objects.remove(object);
    }

    public ArrayList<MapObject> getAllObjects() {
        MapObject[] all = new MapObject[objects.size()];

        objects.toArray(all);

        return new ArrayList<>(Arrays.asList(all));
    }
}

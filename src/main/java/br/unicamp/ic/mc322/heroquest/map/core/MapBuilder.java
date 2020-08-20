package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Handles the creation of a map.
 * <p>
 * In order to build a map, first structural objects should be given to the map.
 * <p>
 * Afterward, the structure must be built through the `buildStructure` map to
 * continue the map construction. After doing so, the builder is prepared to
 * place fixed objects onto the structural objects.
 * <p>
 * Then, a map itself can be built through the `buildMap` method, and the resulting
 * map can be obtained through the `getResult` method.
 * <p>
 * Any attempt to perform an operation in an invalid state will throw an
 * `IllegalStateException`.
 */
public class MapBuilder {
    private Collection<StructuralObject> objects;
    private java.util.Map<Coordinate, MapUnit> units;
    private boolean isBuilt;
    private Dimension dimension;
    private Map result;

    public MapBuilder() {
        reset();
    }

    public void add(StructuralObject structuralObject) {
        if (isBuilt)
            throw new IllegalStateException("Structure already built...");

        Coordinate position = structuralObject.getPosition();

        objects.add(structuralObject);

        if (!position.isInside(dimension))
            // Update is done by creating a new reference, since the dimension must be immutable for other modules
            dimension = dimension.fit(position);
    }

    public void add(FixedObject object) {
        if (!isBuilt)
            throw new IllegalStateException("Structure not built yet...");

        MapUnit unit = units.get(object.getPosition());

        unit.add(object);
    }

    public void reset() {
        dimension = new Dimension(0, 0);
        objects = new ArrayList<>();
        units = new HashMap<>();
        isBuilt = false;
        result = null;
    }

    public void buildStructure() {
        if (isBuilt)
            throw new IllegalStateException("Structure already built...");

        for (StructuralObject object : objects)
            units.put(object.getPosition(), new MapUnit(object));
        isBuilt = true;
    }

    public void buildMap() {
        if (!isBuilt)
            throw new IllegalStateException("Structure must be built first...");

        result = new Map(units, dimension);
    }

    public Map getResult() {
        return result;
    }

}

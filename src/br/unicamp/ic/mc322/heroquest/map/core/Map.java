package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;

public class Map {
    private MapObject[][] map;
    private MapConfiguration config;

    public Map(MapConfiguration config) {
        this.config = config;

        build();
    }

    private void build() {
        Dimension dimension = config.getDimension();
        map = new MapObject[dimension.getWidth()][dimension.getHeight()];

        for (MapObject object : config.getAllObjects()) {
            Coordinate coor = object.getPosition();
            map[coor.getX()][coor.getY()] = object;
        }
    }

    public MapObject get(Coordinate coordinate) {
        return map[coordinate.getX()][coordinate.getY()];
    }

    public Dimension getDimension() {
        return config.getDimension();
    }
}

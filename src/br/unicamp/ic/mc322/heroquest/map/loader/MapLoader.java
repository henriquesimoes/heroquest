package br.unicamp.ic.mc322.heroquest.map.loader;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapConfiguration;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Dimension;
import br.unicamp.ic.mc322.heroquest.map.core.object.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.object.structural.Floor;

public class MapLoader {

    public Map load(String name) {
        /**
         * TODO: Include map loading from file.
         */

        // Create fake testing map
        MapConfiguration config = new MapConfiguration();

        config.setDimension(new Dimension(10, 10));

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                Coordinate coor = new Coordinate(j, i);
                MapObject obj = new Floor(coor);

                config.addObject(obj);
            }

        return new Map(config);
    }
}

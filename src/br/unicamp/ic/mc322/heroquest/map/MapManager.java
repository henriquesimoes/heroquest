package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;

import java.io.FileNotFoundException;

public class MapManager {
    private MapLoader loader;
    private MapGenerator generator;

    public MapManager() {
        loader = new MapLoader();
        generator = new MapGenerator();
    }

    public Map load(String name) throws FileNotFoundException, CorruptedConfigurationFileException {
        MapBuilder builder = loader.load(name);

        populate(builder);

        return builder.getResult();
    }

    public Map generate() {
        MapBuilder builder = generator.generate();

        populate(builder);

        return builder.getResult();
    }

    private void populate(MapBuilder builder) {
        // TODO: Add randomly generated monsters and objects to the map.

        builder.buildMap();
    }
}

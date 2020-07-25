package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;

import java.io.FileNotFoundException;

public class MapManager {
    private static final int NUMBER_OF_MONSTERS = 3;
    private static final int MINIMUM_MONSTER_PER_TYPE = 1;
    private static final int NUMBER_OF_TRAPS = 20;
    private final MapLoader loader;
    private final MapGenerator generator;

    public MapManager() {
        loader = new MapLoader();
        generator = new MapGenerator();
    }

    public Map load(String name) throws FileNotFoundException, CorruptedConfigurationFileException {
        return loader.load(name);
    }

    public Map generate() {
        return generator.generate();
    }

    public String[] getExistingMapNames() {
        return loader.getMapNames();
    }
}

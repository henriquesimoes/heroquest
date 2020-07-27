package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;

import java.io.FileNotFoundException;

public class MapManager {
    private final MapLoader loader;
    private final MapGenerator generator;

    public MapManager() {
        loader = new MapLoader();
        generator = new MapGenerator();
    }

    /**
     * Loads a file from the disk. The map name must be of one the existing
     * maps, which can be obtained through the `getExistingMapNames` method.
     *
     * @param name - map name
     * @return loaded map
     * @throws FileNotFoundException               given map name could not be found
     * @throws CorruptedConfigurationFileException map file is corrupted
     */
    public Map load(String name) throws FileNotFoundException, CorruptedConfigurationFileException {
        return loader.load(name);
    }

    /**
     * Generates a random map.
     *
     * @return generated map
     */
    public Map generate() {
        return generator.generate();
    }

    public String[] getExistingMapNames() {
        return loader.getMapNames();
    }
}

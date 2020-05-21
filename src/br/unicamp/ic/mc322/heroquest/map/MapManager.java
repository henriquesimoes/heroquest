package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;

public class MapManager {
    private MapLoader loader;
    private MapGenerator generator;

    public MapManager() {
        loader = new MapLoader();
        generator = new MapGenerator();
    }

    public Map load(String name) {
        return loader.load(name);
    }

    public Map generate() {
        return generator.generate();
    }
}

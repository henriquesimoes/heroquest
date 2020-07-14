package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapBuilder;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;
import br.unicamp.ic.mc322.heroquest.walker.MonsterGenerator;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.io.FileNotFoundException;

public class MapManager {
    private MapLoader loader;
    private MapGenerator generator;

    private static final int NUMBER_OF_MONSTERS = 20;
    private static final int MINIMUM_MONSTER_PER_TYPE = 1;

    public MapManager() {
        loader = new MapLoader();
        generator = new MapGenerator();
    }

    public Map load(String name) throws FileNotFoundException, CorruptedConfigurationFileException {
        MapBuilder builder = loader.load(name);

        return populate(builder);
    }

    public Map generate() {
        MapBuilder builder = generator.generate();

        return populate(builder);
    }

    public String[] getExistingMapNames() {
        return loader.getMapNames();
    }

    private Map populate(MapBuilder builder) {
        addFixedObjects(builder);

        builder.buildMap();
        Map map = builder.getResult();

        addWalkers(map);

        return map;
    }

    private void addFixedObjects(MapBuilder builder) {

    }

    private void addWalkers(Map map) {
        /*MonsterGenerator generator = new MonsterGenerator();

        generator.setMinimumPerType(MINIMUM_MONSTER_PER_TYPE);

        for (Walker walker : generator.generate(NUMBER_OF_MONSTERS))
            map.add(walker);*/
    }
}

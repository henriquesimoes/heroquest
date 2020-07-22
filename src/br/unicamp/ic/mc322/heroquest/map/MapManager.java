package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.loader.MapLoader;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Trap;
import br.unicamp.ic.mc322.heroquest.walker.MonsterGenerator;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.io.FileNotFoundException;

public class MapManager {
    private static final int NUMBER_OF_MONSTERS = 20;
    private static final int MINIMUM_MONSTER_PER_TYPE = 1;
    private static final int NUMBER_OF_TRAPS = 20;
    private final MapLoader loader;
    private final MapGenerator generator;

    public MapManager() {
        loader = new MapLoader();
        generator = new MapGenerator();
    }

    public Map load(String name) throws FileNotFoundException, CorruptedConfigurationFileException {
        Map map = loader.load(name);

        return populate(map);
    }

    public Map generate() {
        Map map = generator.generate();

        return populate(map);
    }

    public String[] getExistingMapNames() {
        return loader.getMapNames();
    }

    private Map populate(Map map) {
        addWalkers(map);
        addFixedObjects(map);

        return map;
    }

    private void addFixedObjects(Map map) {
        addTraps(map);
    }

    private void addTraps(Map map) {
        for (int i = 0; i < NUMBER_OF_TRAPS; i++)
            map.add(new Trap());
    }

    private void addWalkers(Map map) {
        MonsterGenerator generator = new MonsterGenerator();

        generator.setMinimumPerType(MINIMUM_MONSTER_PER_TYPE);

        for (Walker walker : generator.generate(NUMBER_OF_MONSTERS))
            map.add(walker);
    }
}

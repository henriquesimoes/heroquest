package br.unicamp.ic.mc322.heroquest.map;

import br.unicamp.ic.mc322.heroquest.engine.GameLevel;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.objects.fixed.Trap;
import br.unicamp.ic.mc322.heroquest.walker.MonsterGenerator;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class MapPopulator {
    private static final int MINIMUM_MONSTER_PER_TYPE = 1;
    private static final int NUMBER_OF_TRAPS = 20;
    private final GameLevel level;

    public MapPopulator(GameLevel level) {
        this.level = level;
    }

    public Map populate(Map map) {
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

        int numberOfMonsters = level.getNumberOfMonsters(map.getHeight() * map.getWidth());

        for (Walker walker : generator.generate(numberOfMonsters))
            map.add(walker);
    }
}

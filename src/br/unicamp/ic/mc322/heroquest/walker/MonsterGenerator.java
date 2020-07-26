package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.engine.GameMonitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.monsters.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monsters.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monsters.WizardSkeleton;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterGenerator {
    private int minimumPerType;
    private int numberOfTypes;

    public MonsterGenerator() {
        minimumPerType = 0;
        setNumberOfTypes();
    }

    public static Walker getRandomMonster() {
        Walker[] monsters = generateMonsters();

        return monsters[Randomizer.nextInt(monsters.length)];
    }

    private static Walker[] generateMonsters() {

        return new Walker[]{
                new CommonSkeleton(),
                new WizardSkeleton(),
                new Goblin()
        };
    }

    public static boolean appearMonsterClose(Walker reference, Coordinate source) {
        WalkerManager walkerManager = reference.getManager();

        RegionSelector regionSelector = walkerManager.getRegionSelector();
        Region region = regionSelector.getAdjacentRegion(source, true);
        ArrayList<Coordinate> possiblePositions = region.toArrayList();
        possiblePositions.remove(source);

        if (possiblePositions.isEmpty())
            return false;

        ArrayList<Walker> targets = new ArrayList<>(Arrays.asList(reference));
        Coordinate position = walkerManager.getCoordinateCloserToWalkers(possiblePositions, targets);

        Walker monster = getRandomMonster();
        monster.setPosition(position);

        GameMonitor gameMonitor = GameMonitor.getInstance();
        gameMonitor.notifyUpcoming(monster);

        return true;
    }

    public Walker[] generate(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Invalid non-positive number of monsters provided...");

        Walker[] monsters = new Walker[quantity];
        int generated = 0;

        for (int monsterType = 0; monsterType < numberOfTypes; monsterType++)
            for (int i = 0; i < minimumPerType && generated < quantity; i++) {
                monsters[generated] = generateMonsters()[monsterType];
                generated++;
            }

        for (int i = generated; i < quantity; i++)
            monsters[i] = getRandomMonster();

        return monsters;
    }

    public void setMinimumPerType(int minimumPerType) {
        this.minimumPerType = minimumPerType;
    }

    private void setNumberOfTypes() {
        Walker[] monsters = generateMonsters();

        numberOfTypes = monsters.length;
    }
}

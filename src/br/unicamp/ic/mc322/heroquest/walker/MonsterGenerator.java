package br.unicamp.ic.mc322.heroquest.walker;


import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.monsters.CommonSkeleton;
import br.unicamp.ic.mc322.heroquest.walker.monsters.Goblin;
import br.unicamp.ic.mc322.heroquest.walker.monsters.WizardSkeleton;

public class MonsterGenerator {
    private int minimumPerType;
    private int numberOfTypes;

    public MonsterGenerator() {
        minimumPerType = 0;
        setNumberOfTypes();
    }

    public Monster[] generate(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Invalid non-positive number of monsters provided...");

        Monster[] monsters = new Monster[quantity];
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

    public Monster getRandomMonster() {
        Monster[] monsters = generateMonsters();

        return monsters[Randomizer.nextInt(monsters.length)];
    }

    public void setMinimumPerType(int minimumPerType) {
        this.minimumPerType = minimumPerType;
    }

    private Monster[] generateMonsters() {

        return new Monster[]{
                new CommonSkeleton(),
                new WizardSkeleton(),
                new Goblin()
        };
    }

    private void setNumberOfTypes() {
        Monster[] monsters = generateMonsters();

        numberOfTypes = monsters.length;
    }
}

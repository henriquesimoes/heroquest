package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.Dagger;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Monster;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.attack.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.movement.Follower;

public class Goblin extends Monster {
    private final int initialNumberOfDaggers = 2;

    public Goblin() {
        super(new WalkerAI(new Follower(), new Bloodthirsty()), "Goblin");

        attackDice = 3;
        defenseDice = 1;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 2;

        Weapon currentWeapon = new Dagger();
        knapsack.put(currentWeapon);
        equipWeapon(currentWeapon);

        for (int i = 0; i < initialNumberOfDaggers - 1; i++)
            knapsack.put(new Dagger());
    }

    @Override
    public String getRepresentationOnMenu() {
        return name;
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

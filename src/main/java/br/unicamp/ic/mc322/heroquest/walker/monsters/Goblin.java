package br.unicamp.ic.mc322.heroquest.walker.monsters;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.Dagger;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.attack.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.movement.Follower;

public class Goblin extends Walker {
    private final int initialNumberOfDaggers = 2;

    public Goblin() {
        super(new WalkerAI(new Follower(), new Bloodthirsty()), "Goblin");

        team = Team.MORCAR;
        attackDice = 3;
        defenseDice = 1;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 2;
        durableItemsAbleToUse.add(DurableItemClass.WARRIOR);

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

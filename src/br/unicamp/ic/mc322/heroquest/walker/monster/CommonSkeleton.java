package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class CommonSkeleton extends Monster {
    public CommonSkeleton(WalkerManager walkerManager) {
        super(walkerManager, "Common Skeleton");
        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 2;
        mindPoints = 1;
        Weapon weapon = Weapon.getRandomWeapon();

        knapsack.put(weapon);
        equipWeapon(weapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Common Skeleton";
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

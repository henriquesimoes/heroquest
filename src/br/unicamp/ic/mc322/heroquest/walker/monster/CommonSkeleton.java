package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class CommonSkeleton extends Monster {
    CommonSkeleton(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
        this.walkerManager.setWalker(this);
        name = "Common Skeleton";
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints = 2;
        mindPoints = 1;
        Weapon weapon = Weapon.getRandomWeapon();

        knapsack.put(weapon);
        equipWeapon(weapon);
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("S");
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Common Skeleton";
    }
}

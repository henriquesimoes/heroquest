package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.Dagger;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Goblin extends Monster {
    private final int initialNumberOfDaggers = 2;

    public Goblin(WalkerManager walkerManager) {
        super(walkerManager, "Goblin");

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
    public ObjectView getRepresentation() {
        return new ObjectView("G");
    }

    @Override
    public String getRepresentationOnMenu() {
        return name;
    }
}

package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.LongSword;
import br.unicamp.ic.mc322.heroquest.map.view.ObjectView;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Barbarian extends Hero {
    public Barbarian(WalkerManager walkerManager, String name) {
        this.walkerManager = walkerManager;
        this.walkerManager.setWalker(this);

        this.name = name;
        attackDice = 3;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints = 8;
        mindPoints = 2;

        Weapon curWeapon = new LongSword();
        knapsack.put(curWeapon);

        equipWeapon(curWeapon);
    }

    @Override
    public ObjectView getRepresentation() {
        return new ObjectView("B");
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Barbarian " + getName();
    }
}

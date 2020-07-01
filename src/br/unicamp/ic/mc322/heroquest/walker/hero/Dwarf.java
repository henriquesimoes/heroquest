package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.map.core.MapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Dwarf extends Hero {
    public Dwarf(WalkerManager walkerManager, String name) {
        super(walkerManager, name);

        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 7;
        mindPoints = 3;

        Weapon currentWeapon = new ShortSword();
        knapsack.put(currentWeapon);

        equipWeapon(currentWeapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Dwarf " + getName();
    }

    @Override
    public void accept(MapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

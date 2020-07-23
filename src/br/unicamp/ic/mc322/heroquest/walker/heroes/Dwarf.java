package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Hero;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.ShortSword;

public class Dwarf extends Hero {
    public Dwarf(String name, IOInterface ioInterface) {
        super(name, ioInterface);

        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 30;
        mindPoints = 3;
        durableItemsAbleToUse.add(DurableItemClass.WARRIOR);

        Weapon currentWeapon = new ShortSword();
        knapsack.put(currentWeapon);

        equipWeapon(currentWeapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Dwarf " + getName();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Hero;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.LongSword;

public class Barbarian extends Hero {
    public Barbarian(String name, IOInterface ioInterface) {
        super(name, ioInterface);

        attackDice = 3;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 40;
        mindPoints = 2;
        durableItemsAbleToUse.add(DurableItemClass.WARRIOR);

        Weapon currentWeapon = new LongSword();
        knapsack.put(currentWeapon);

        equipWeapon(currentWeapon);
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Barbarian " + getName();
    }

    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

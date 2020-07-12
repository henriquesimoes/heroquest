package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.LongSword;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Hero;

public class Barbarian extends Hero {
    public Barbarian(String name) {
        super(name);

        attackDice = 3;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 8;
        mindPoints = 2;

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

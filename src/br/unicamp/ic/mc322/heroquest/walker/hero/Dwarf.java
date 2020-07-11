package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Hero;

public class Dwarf extends Hero {
    public Dwarf(String name) {
        super(name);

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
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

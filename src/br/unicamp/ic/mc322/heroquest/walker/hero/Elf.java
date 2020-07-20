package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.skills.magic.SimpleHeal;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Hero;

public class Elf extends Hero {
    public Elf(String name, IOInterface ioInterface) {
        super(name, ioInterface);

        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 20;
        mindPoints = 4;

        ableToLearnWaterSpell = ableToLearnEarthSpell = true;

        Weapon currentWeapon = new ShortSword();
        knapsack.put(currentWeapon);
        equipWeapon(currentWeapon);

        addSkill(new SimpleHeal());
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Elf: " + getName();
    }


    @Override
    public void accept(ConcreteMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}

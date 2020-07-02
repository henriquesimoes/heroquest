package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.SimpleHeal;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

public class Elf extends Hero {
    public Elf(WalkerManager walkerManager, String name) {
        super(walkerManager, name);

        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints =  6;
        mindPoints = 4;

        ableLearnWaterSpell = ableLearnEarthSpell = true;

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

package br.unicamp.ic.mc322.heroquest.walker.heroes;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.core.ConcreteMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.walker.Hero;
import br.unicamp.ic.mc322.heroquest.walker.items.ItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.items.cards.SpellElement;
import br.unicamp.ic.mc322.heroquest.walker.items.weapons.ShortSword;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.SimpleHeal;

public class Elf extends Hero {
    public Elf(String name, IOInterface ioInterface) {
        super(name, ioInterface);

        attackDice = 2;
        defenseDice = 2;
        maximumBodyPoints = currentBodyPoints = 20;
        mindPoints = 4;
        itemsAbleToUse.add(ItemClass.MAGICIAN);
        spellsAbleToLearn.add(SpellElement.EARTH);

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
